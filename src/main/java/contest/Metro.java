package contest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Metro {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            int stationsCount = Integer.parseInt(bufferedReader.readLine());
            int linesCount = Integer.parseInt(bufferedReader.readLine());
            List<Set<Integer>> linesWithStations = new ArrayList<>(stationsCount);
            List<List<Integer>> stationConnections = new ArrayList<>();
            List<List<Integer>> stationsWithLines = new ArrayList<>(stationsCount + 1);
            for (int i = 0; i <= stationsCount; i++) {
                stationsWithLines.add(new ArrayList<>());
            }
            for (int i = 0; i < linesCount; i++) {
                StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
                linesWithStations.add(new HashSet<>());
                stationConnections.add(new ArrayList<>());
                int stationsInLine = Integer.parseInt(tokenizer.nextToken());
                for (int j = 0; j < stationsInLine; j++) {
                    int currentStationNumber = Integer.parseInt(tokenizer.nextToken());
                    linesWithStations.get(i).add(currentStationNumber);
                    stationsWithLines.get(currentStationNumber).add(i);
                    for (int k = 0; k < linesWithStations.size(); k++) {
                        if (k == i) continue;
                        if (linesWithStations.get(k).contains(currentStationNumber)) {
                            stationConnections.get(k).add(i);
                            stationConnections.get(i).add(k);
                        }
                    }
                }
            }
            String[] srcDstStations = bufferedReader.readLine().split(" ");
            int srcStation = Integer.parseInt(srcDstStations[0]);
            int dstStation = Integer.parseInt(srcDstStations[1]);
            Deque<Integer> lines = new ArrayDeque<>();
            boolean[] visited = new boolean[linesCount + 1];
            int[] connections = new int[linesCount + 1];
            stationsWithLines.get(srcStation).forEach(integer -> {
                lines.addLast(integer);
                visited[integer] = true;
            });
            while (!lines.isEmpty()) {
                int currentLine = lines.removeFirst();
                if (linesWithStations.get(currentLine).contains(dstStation)) {
                    System.out.println(connections[currentLine]);
                    return;
                }
                stationConnections.get(currentLine).forEach(integer -> {
                    if (!visited[integer]) {
                        connections[integer] = connections[currentLine] + 1;
                        lines.add(integer);
                        visited[integer] = true;
                    }
                });
            }
            System.out.println(-1);
        } catch (IOException ignored) {}
    }
}
