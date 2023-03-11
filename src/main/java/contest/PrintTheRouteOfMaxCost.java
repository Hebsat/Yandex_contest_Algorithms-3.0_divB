package contest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PrintTheRouteOfMaxCost {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            String[] firstInput = bufferedReader.readLine().split(" ");
            int n = Integer.parseInt(firstInput[0]);
            int m = Integer.parseInt(firstInput[1]);
            int[][] costs = new int[n][m];
            int[][] routes = new int[n][m];
            for (int i = 0; i < n; i++) {
                StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
                for (int j = 0; j < m; j++) {
                    int currentCost = Integer.parseInt(tokenizer.nextToken());
                    int leftFieldCost = j > 0 ? costs[i][j - 1] : 0;
                    int upFieldCost = i > 0 ? costs[i - 1][j] : 0;
                    if (leftFieldCost > upFieldCost) {
                        costs[i][j] = leftFieldCost + currentCost;
                        routes[i][j] = 1;
                    }
                    else {
                        costs[i][j] = upFieldCost + currentCost;
                    }
                }
            }
            System.out.println(costs[n - 1][m - 1]);
            StringJoiner stringJoiner = new StringJoiner(" ");
            List<String> way = new ArrayList<>(n + m -2);
            while (n + m > 2) {
                if (routes[n - 1][m - 1] > 0) {
                    way.add("R");
                    m--;
                }
                else {
                    way.add("D");
                    n--;
                }
            }
            for (int i = way.size(); i > 0; i--) {
                stringJoiner.add(way.get(i - 1));
            }
            System.out.println(stringJoiner);
        } catch (IOException ignored) {}
    }
}
