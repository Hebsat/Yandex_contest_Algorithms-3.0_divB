package contest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CycleFinding {
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
            boolean[] visitedVertexes = new boolean[n + 1];
            Deque<Integer> cycleVertexes = new ArrayDeque<>(n);
            for (int i = 1; i < visitedVertexes.length; i++) {
                if (visitedVertexes[i]) continue;
                if (!dfs(graph, visitedVertexes, i, 0, cycleVertexes)) {
                    System.out.println("YES");
                    System.out.println(cycleVertexes.size());
                    StringJoiner stringJoiner = new StringJoiner(" ");
                    while (!cycleVertexes.isEmpty()) stringJoiner.add(String.valueOf(cycleVertexes.removeLast()));
                    System.out.println(stringJoiner);
                    return;
                }
            }
            System.out.println("NO");
        } catch (IOException ignored) {}
    }

    private static boolean dfs(int[][] graph, boolean[] visitedVertexes, int currentVertex, int lastVertex, Deque<Integer> cycleVertexes) {
        visitedVertexes[currentVertex] = true;
        cycleVertexes.addLast(currentVertex);
        for (int i = 1; i < graph.length; i++) {
            if (graph[currentVertex][i] == 0 || i == lastVertex) continue;
            if (visitedVertexes[i]) {
                while (cycleVertexes.getFirst() != i) cycleVertexes.removeFirst();
                return false;
            }
            if (!dfs(graph, visitedVertexes, i, currentVertex, cycleVertexes)) return false;
        }
        cycleVertexes.removeLast();
        return true;
    }
}
