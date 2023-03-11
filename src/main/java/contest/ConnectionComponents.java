package contest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ConnectionComponents {
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
            List<Set<Integer>> allComponents = new ArrayList<>();
            for (int currentVertex : visitedVertexes.keySet()) {
                if (!visitedVertexes.get(currentVertex)) {
                    Set<Integer> vertexesInComponent = new TreeSet<>();
                    dfs(graph, visitedVertexes, currentVertex, vertexesInComponent);
                    allComponents.add(vertexesInComponent);
                }
            }
            System.out.println(allComponents.size());
            allComponents.forEach(integers -> {
                System.out.println(integers.size());
                StringJoiner stringJoiner = new StringJoiner(" ");
                integers.forEach(integer -> stringJoiner.add(String.valueOf(integer)));
                System.out.println(stringJoiner);

            });
        } catch (IOException ignored) {}
    }

    private static void dfs(List<List<Integer>> graph, Map<Integer, Boolean> visitedVertexes, int currentVertex, Set<Integer> vertexesInComponent) {
        visitedVertexes.put(currentVertex, true);
        vertexesInComponent.add(currentVertex);
        for (int currentVertexConnection : graph.get(currentVertex)) {
            if (!visitedVertexes.get(currentVertexConnection)) {
                dfs(graph, visitedVertexes, currentVertexConnection, vertexesInComponent);
            }
        }
    }
}
