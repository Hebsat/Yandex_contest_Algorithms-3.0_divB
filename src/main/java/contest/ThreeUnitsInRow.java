package contest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ThreeUnitsInRow {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            int n = Integer.parseInt(bufferedReader.readLine());
            long[] badValues = new long[Math.max(n, 4)];
            badValues[0] = badValues[1] = 0;
            badValues[2] = 1;
            badValues[3] = 3;
            for (int i = 4; i < n; i++) {
                badValues[i] = badValues[i - 1] * 2 + ((long) Math.pow(2, i - 3) - badValues[i - 4]);
            }
            System.out.println((long) Math.pow(2, n) - badValues[n - 1]);
        } catch (IOException ignored) {
        }
    }
}
