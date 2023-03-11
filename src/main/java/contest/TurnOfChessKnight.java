package contest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TurnOfChessKnight {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            String[] input = bufferedReader.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);
            int[][] desk = new int[n][m];
            desk[0][0] = 1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (i + 2 < n && j + 1 < m) {
                        desk[i + 2][j + 1] += desk[i][j];
                    }
                    if (i + 1 < n && j + 2 < m) {
                        desk[i + 1][j + 2] += desk[i][j];
                    }
                }
            }
            System.out.println(desk[n -1][m - 1]);
        } catch (IOException ignored) {
        }
    }
}
