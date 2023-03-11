package contest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public class Fleas {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            String[] input = bufferedReader.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);
            int s = Integer.parseInt(input[2]);
            int t = Integer.parseInt(input[3]);
            int q = Integer.parseInt(input[4]);
            int[][] field = new int[n + 1][m + 1];
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= m; j++) {
                    field[i][j] = -1;
                }
            }
            field[s][t] = 0;
            boolean[][] visited = new boolean[n + 1][m + 1];
            int[][] fleaMoving = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
            Deque<int[]> fleaMoves = new ArrayDeque<>();
            fleaMoves.addLast(new int[]{s, t});
            visited[s][t] = true;
            while (!fleaMoves.isEmpty()) {
                int[] currentFleaCoordinates = fleaMoves.removeFirst();
                for (int[] moves : fleaMoving) {
                    int[] newJump = new int[]{currentFleaCoordinates[0] + moves[0], currentFleaCoordinates[1] + moves[1]};
                    if (newJump[0] < 1 || newJump[0] > n || newJump[1] < 1 || newJump[1] > m || visited[newJump[0]][newJump[1]])
                        continue;
                    field[newJump[0]][newJump[1]] = field[currentFleaCoordinates[0]][currentFleaCoordinates[1]] + 1;
                    fleaMoves.addLast(newJump);
                    visited[newJump[0]][newJump[1]] = true;
                }
            }
            int totalMoves = 0;
            for (int i = 0; i < q; i++) {
                String[] currentFleaCoordinatesString = bufferedReader.readLine().split(" ");
                int steps = field[Integer.parseInt(currentFleaCoordinatesString[0])][Integer.parseInt(currentFleaCoordinatesString[1])];
                if (steps == -1) {
                    totalMoves = steps;
                    break;
                }
                totalMoves += steps;
            }
            System.out.println(totalMoves);
        } catch (IOException ignored) {}
    }
}
