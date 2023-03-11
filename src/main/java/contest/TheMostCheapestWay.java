package contest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class TheMostCheapestWay {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            String[] firstInput = bufferedReader.readLine().split(" ");
            int n = Integer.parseInt(firstInput[0]);
            int m = Integer.parseInt(firstInput[1]);
            int[][] costs = new int[n][m];
            for (int i = 0; i < n; i++) {
                StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
                for (int j = 0; j < m; j++) {
                    int currentCost = Integer.parseInt(tokenizer.nextToken());
                    int costWithLastValue = currentCost;
                    if (j > 0) costWithLastValue += costs[i][j - 1];
                    if (j == 0 && i > 0) costWithLastValue += costs[i - 1][j];
                    if (i > 0) costWithLastValue = Math.min(costs[i - 1][j] + currentCost, costWithLastValue);
                    costs[i][j] = costWithLastValue;
                }
            }
            System.out.println(costs[n - 1][m - 1]);
        } catch (IOException ignored) {}
    }
}
