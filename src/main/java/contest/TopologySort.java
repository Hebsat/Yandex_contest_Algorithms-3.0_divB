package contest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TopologySort {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            String[] input = bufferedReader.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);
            List<List<Integer>> graph = new ArrayList<>(n + 1);
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }
            int[] visitedVertexes = new int[n + 1];
            for (int i = 1; i <= m; i++) {
                String[] edge = bufferedReader.readLine().split(" ");
                int firstVertex = Integer.parseInt(edge[0]);
                int secondVertex = Integer.parseInt(edge[1]);
                graph.get(firstVertex).add(secondVertex);
            }
            Deque<Integer> sortedVertexes = new ArrayDeque<>(n);
            for (int i = 1; i <= n; i++) {
                if (visitedVertexes[i] == 0) {
                    if (!dfs(graph, visitedVertexes, i, sortedVertexes)) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
            StringJoiner stringJoiner = new StringJoiner(" ");
            while (!sortedVertexes.isEmpty()) {
                stringJoiner.add(String.valueOf(sortedVertexes.removeLast()));
            }
            System.out.println(stringJoiner);
        } catch (IOException ignored) {}
    }

    private static boolean dfs(List<List<Integer>> graph, int[] visitedVertexes, int currentVertex, Deque<Integer> sortedVertexes) {
        visitedVertexes[currentVertex] = 1;
        for (int currentVertexConnection : graph.get(currentVertex)) {
            if (visitedVertexes[currentVertexConnection] == 1) return false;
            if (visitedVertexes[currentVertexConnection] == 0) {
                if (!dfs(graph, visitedVertexes, currentVertexConnection, sortedVertexes)) return false;
            }
        }
        visitedVertexes[currentVertex] = 2;
        sortedVertexes.addLast(currentVertex);
        return true;
    }
}
