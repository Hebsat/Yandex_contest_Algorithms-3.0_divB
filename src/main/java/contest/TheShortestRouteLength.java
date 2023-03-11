package contest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TheShortestRouteLength {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            int n = Integer.parseInt(bufferedReader.readLine());
            int[][] graph = new int[n + 1][n + 1];
            for (int i = 1; i <= n; i++) {
                StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
                for (int j = 1; j <= n; j++) {
                    graph[i][j] = Integer.parseInt(tokenizer.nextToken());
                }
            }
            String[] route = bufferedReader.readLine().split(" ");
            int startVertex = Integer.parseInt(route[0]);
            int destinationVertex = Integer.parseInt(route[1]);
            int[] lengthsFromStart = new int[n + 1];
            boolean[] visitedVertexes = new boolean[n + 1];
            Deque<Integer> vertexesByStartRoute = new ArrayDeque<>();
            vertexesByStartRoute.addLast(startVertex);
            while (!vertexesByStartRoute.isEmpty()) {
                int currentVertex = vertexesByStartRoute.removeFirst();
                if (currentVertex == destinationVertex) {
                    System.out.println(lengthsFromStart[currentVertex]);
                    return;
                }
                for (int i = 1; i <= n; i++) {
                    if (graph[currentVertex][i] == 0 || visitedVertexes[i]) continue;
                    visitedVertexes[i] = true;
                    lengthsFromStart[i] = lengthsFromStart[currentVertex] + 1;
                    vertexesByStartRoute.addLast(i);
                }

            }
            System.out.println(-1);
        } catch (IOException ignored) {}
    }
}
