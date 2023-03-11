package contest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Cafe {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());
            int n = Integer.parseInt(bufferedReader.readLine());
            int[][] dinners = new int[n][n + 1];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n + 1; j++) {
                    dinners[i][j] = -1;
                }
            }
            ArrayDeque<Integer> dinnersCosts = new ArrayDeque<>(n);
            if (n > 0) {
                int firstDinnerCost = Integer.parseInt(bufferedReader.readLine());
                dinners[0][firstDinnerCost > 100 ? 1 : 0] = firstDinnerCost;
                dinnersCosts.addLast(firstDinnerCost);
            }
            for (int i = 1; i < n; i++) {
                int todayCost = Integer.parseInt(bufferedReader.readLine());
                dinnersCosts.addLast(todayCost);
                for (int j = 0; j < n; j++) {
                    int previousDayMoreCoupons = dinners[i - 1][j + 1] == -1 ? Integer.MAX_VALUE : dinners[i - 1][j + 1];
                    if (todayCost > 100) {
                        int previousDayLessCoupons = j == 0 ? Integer.MAX_VALUE : dinners[i - 1][j - 1] + todayCost;
                        if (j == 0 && dinners[i - 1][j + 1] == -1 || j != 0 && dinners[i - 1][j - 1] == -1) continue;
                        dinners[i][j] = Math.min(previousDayMoreCoupons, previousDayLessCoupons);
                    }
                    else {
                        int previousDayEqualsCoupons = dinners[i - 1][j] == -1 ? Integer.MAX_VALUE : dinners[i - 1][j] + todayCost;
                        if (dinners[i - 1][j] == -1 && dinners[i - 1][j + 1] == -1) break;
                        dinners[i][j] = Math.min(previousDayMoreCoupons, previousDayEqualsCoupons);
                    }
                }
            }
            int minTotalCost = n == 0 ? 0 : Integer.MAX_VALUE;
            int finalCouponsCount = 0;
            List<Integer> daysCouponsUsed = new ArrayList<>();
            for (int i = 0; i < n + 1; i++) {
                if (n == 0) break;
                if (minTotalCost >= dinners[n - 1][i] && dinners[n - 1][i] != -1) {
                    minTotalCost = dinners[n - 1][i];
                    finalCouponsCount = i;
                }
            }
            int lastCouponsCount = finalCouponsCount;
            for (int i = 0; i < n - 1; i++) {
                int currentSum = dinnersCosts.removeLast();
                if (currentSum > 100) {
                    if (lastCouponsCount > 0 && dinners[n - 1 - i][lastCouponsCount] - currentSum == dinners[n - 2 - i][lastCouponsCount - 1]) {
                        lastCouponsCount--;
                    }
                    else {
                        daysCouponsUsed.add(n - i);
                        lastCouponsCount++;
                    }
                }
                else {
                    if (dinners[n - 1 - i][lastCouponsCount] - currentSum != dinners[n - 2 - i][lastCouponsCount]) {
                        daysCouponsUsed.add(n - i);
                        lastCouponsCount++;
                    }
                }
            }
            stringJoiner.add(String.valueOf(minTotalCost));
            stringJoiner.add(finalCouponsCount + " " + daysCouponsUsed.size());
            daysCouponsUsed.stream().sorted().forEach(integer -> stringJoiner.add(String.valueOf(integer)));
            System.out.println(stringJoiner);
        } catch (IOException ignored) {}
    }
}
