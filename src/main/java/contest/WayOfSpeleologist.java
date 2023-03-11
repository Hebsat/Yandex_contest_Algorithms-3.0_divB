package contest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WayOfSpeleologist {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            int n = Integer.parseInt(bufferedReader.readLine());
            Map<Integer, List<Integer>> cave = new HashMap<>();
            int speleologist = -1;
            for (int i = 0; i < n; i++) {
                bufferedReader.readLine();
                for (int j = 0; j < n; j++) {
                    String input = bufferedReader.readLine();
                    for (int k = 1; k <= n; k++) {
                        char currentBlock = input.charAt(k - 1);
                        if (currentBlock == '#') continue;
                        int vertex = k + n * j + n * n * i;
                        cave.put(vertex, new ArrayList<>());
                        if (cave.containsKey(vertex - n * n)) {
                            cave.get(vertex).add(vertex - n * n);
                            cave.get(vertex - n * n).add(vertex);
                        }
                        if (k > 1 && cave.containsKey(vertex - 1)) {
                            cave.get(vertex).add(vertex - 1);
                            cave.get(vertex - 1).add(vertex);
                        }
                        if (j > 0 && cave.containsKey(vertex - n)) {
                            cave.get(vertex).add(vertex - n);
                            cave.get(vertex - n).add(vertex);
                        }
                        if (currentBlock == 'S') speleologist = vertex;
                    }
                }
            }
            Deque<int[]> routes = new ArrayDeque<>();
            Set<Integer> visited = new HashSet<>();
            routes.add(new int[]{speleologist, 0});
            visited.add(speleologist);
            while (!routes.isEmpty()) {
                int[] current = routes.removeFirst();
                if (current[0] <= n * n) {
                    System.out.println(current[1]);
                    return;
                }
                cave.get(current[0]).forEach(integer -> {
                    if (!visited.contains(integer)) {
                        routes.addLast(new int[]{integer, current[1] + 1});
                        visited.add(integer);
                    }
                });
            }
        } catch (IOException ignored) {}
    }
}
