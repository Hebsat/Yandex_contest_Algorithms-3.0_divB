package contest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Nails {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            int n = Integer.parseInt(bufferedReader.readLine());
            int[] nails = new int[n];
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int i = 0; i < n; i++) {
                nails[i] = Integer.parseInt(tokenizer.nextToken());
            }
            Arrays.sort(nails);
            int[] minLength = new int[n-1];
            minLength[0] = nails[1] - nails[0];
            if (n > 2) minLength[1] = nails[2] - nails[0];
            for (int i = 2; i < n - 1; i++) {
                int lastLength = nails[i + 1] - nails[i];
                minLength[i] = Math.min(minLength[i - 2] + lastLength, minLength[i - 1] + lastLength);
            }
            System.out.println(minLength[n - 2]);
        } catch (IOException ignored) {}
    }
}
