package contest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class TicketsBuying {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            int n = Integer.parseInt(bufferedReader.readLine());
            int[][] peopleInQueue = new int[n][3];
            for (int i = 0; i < n; i++) {
                StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
                int[] row = new int[3];
                for (int j = 0; j < 3; j++) {
                    row[j] = Integer.parseInt(tokenizer.nextToken());
                }
                peopleInQueue[i] = row;
            }
            int[] timeOptimumsForEachMan = new int[n];
            timeOptimumsForEachMan[0] = peopleInQueue[0][0];
            if (n > 1) timeOptimumsForEachMan[1] = Math.min(timeOptimumsForEachMan[0] + peopleInQueue[1][0], peopleInQueue[0][1]);
            if (n > 2)timeOptimumsForEachMan[2] = Math.min(timeOptimumsForEachMan[1] + peopleInQueue[2][0],
                    Math.min(timeOptimumsForEachMan[0] + peopleInQueue[1][1], peopleInQueue[0][2]));
            for (int i = 3; i < n; i++) {
                timeOptimumsForEachMan[i] = Math.min(timeOptimumsForEachMan[i - 1] + peopleInQueue[i][0],
                        Math.min(timeOptimumsForEachMan[i - 2] + peopleInQueue[i - 1][1],
                                timeOptimumsForEachMan[i - 3] + peopleInQueue[i - 2][2]));
            }
            System.out.println(timeOptimumsForEachMan[n - 1]);
        } catch (IOException ignored) {}
    }
}
