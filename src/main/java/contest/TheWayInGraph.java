package contest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class TheWayInGraph {
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
            String[] routeString = bufferedReader.readLine().split(" ");
            int startVertex = Integer.parseInt(routeString[0]);
            int destinationVertex = Integer.parseInt(routeString[1]);
            int[] lengthsFromStart = new int[n + 1];
            boolean[] visitedVertexes = new boolean[n + 1];
            Deque<Integer> vertexesByStartRoute = new ArrayDeque<>();
            vertexesByStartRoute.addLast(startVertex);
            visitedVertexes[startVertex] = true;
            while (!vertexesByStartRoute.isEmpty()) {
                int currentVertex = vertexesByStartRoute.removeFirst();
                if (currentVertex == destinationVertex) {
                    System.out.println(lengthsFromStart[currentVertex]);
                    if (lengthsFromStart[currentVertex] > 0) {
                        int currentLength = lengthsFromStart[currentVertex];
                        Deque<Integer> route = new ArrayDeque<>();
                        route.addLast(currentVertex);
                        while (currentLength > 0) {
                            for (int i = 1; i <= n; i++) {
                                if (graph[currentVertex][i] == 0) continue;
                                if (lengthsFromStart[i] == currentLength - 1) {
                                    currentVertex = i;
                                    currentLength--;
                                    route.addLast(i);
                                }

                            }
                        }
                        StringJoiner stringJoiner = new StringJoiner(" ");
                        while (!route.isEmpty()) stringJoiner.add(String.valueOf(route.removeLast()));
                        System.out.println(stringJoiner);
                    }
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
