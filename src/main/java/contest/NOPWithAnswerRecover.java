package contest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class NOPWithAnswerRecover {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            int n = Integer.parseInt(bufferedReader.readLine().trim());
            int[] firstSequence = new int[n];
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int i = 0; i < n; i++) {
                firstSequence[i] = Integer.parseInt(tokenizer.nextToken());
            }
            int m = Integer.parseInt(bufferedReader.readLine().trim());
            int[] secondSequence = new int[m];
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int i = 0; i < m; i++) {
                secondSequence[i] = Integer.parseInt(tokenizer.nextToken());
            }
            int[][] sequenceValues = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (firstSequence[i] == secondSequence[j] && (j == 0 ? 0 : sequenceValues[i][j - 1]) == (i == 0 ? 0 : sequenceValues[i - 1][j == 0 ? 0 : j - 1])) {
                        sequenceValues[i][j] = sequenceValues[i][j == 0 ? 0 : j - 1] + 1;
                    }
                    else {
                        sequenceValues[i][j] = Math.max(sequenceValues[i][j == 0 ? 0 : j - 1], sequenceValues[i == 0 ? 0 : i - 1][j]);
                    }
                }
            }
            Deque<Integer> subsequence = new ArrayDeque<>(sequenceValues[n -1][m - 1]);
            int currentSequenceLength = sequenceValues[n -1][m - 1];
            int currentN = n -1;
            int currentM = m -1;
            while (currentSequenceLength > 0) {
                if ((currentM == 0 ? 0 : sequenceValues[currentN][currentM - 1]) == (currentN == 0 ? 0 : sequenceValues[currentN - 1][currentM]) &&
                        (currentM == 0 ? 0 : sequenceValues[currentN][currentM - 1]) < sequenceValues[currentN][currentM]) {
                    subsequence.addLast(firstSequence[currentN]);
                    currentSequenceLength--;
                    currentN--;
                    currentM--;
                } else if ((currentM == 0 ? 0 : sequenceValues[currentN][currentM - 1]) > (currentN == 0 ? 0 : sequenceValues[currentN - 1][currentM])) {
                    currentM--;
                }
                else {
                    currentN--;
                }
            }
            StringJoiner stringJoiner = new StringJoiner(" ");
            while (!subsequence.isEmpty()) {
                stringJoiner.add(String.valueOf(subsequence.removeLast()));
            }
            System.out.println(stringJoiner);

        } catch (IOException ignored) {}
    }
}
