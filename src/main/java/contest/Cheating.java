package contest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Cheating {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            String[] input = bufferedReader.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);
            List<List<Integer>> graph = new ArrayList<>(n + 1);
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }
            Map<Integer, Boolean> visitedVertexes = new HashMap<>();
            int[] coloredVertexes = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                visitedVertexes.put(i, false);
            }
            for (int i = 1; i <= m; i++) {
                String[] edge = bufferedReader.readLine().split(" ");
                int firstVertex = Integer.parseInt(edge[0]);
                int secondVertex = Integer.parseInt(edge[1]);
                graph.get(firstVertex).add(secondVertex);
                graph.get(secondVertex).add(firstVertex);
            }
            for (int currentVertex : visitedVertexes.keySet()) {
                if (!visitedVertexes.get(currentVertex)) {
                    coloredVertexes[currentVertex] = 1;
                    if (!dfs(graph, visitedVertexes, currentVertex, coloredVertexes)) {
                        System.out.println("NO");
                        return;
                    }
                }
            }
            System.out.println("YES");
        } catch (IOException ignored) {}
    }

    private static boolean dfs(List<List<Integer>> graph, Map<Integer, Boolean> visitedVertexes, int currentVertex, int[] coloredVertexes) {
        visitedVertexes.put(currentVertex, true);
        int currentColor = coloredVertexes[currentVertex];
        for (int currentVertexConnection : graph.get(currentVertex)) {
            if (coloredVertexes[currentVertexConnection] == currentColor) return false;
            if (!visitedVertexes.get(currentVertexConnection)) {
                coloredVertexes[currentVertexConnection] = 3 - currentColor;
                if (!dfs(graph, visitedVertexes, currentVertexConnection, coloredVertexes)) return false;
            }
        }
        return true;
    }
}
