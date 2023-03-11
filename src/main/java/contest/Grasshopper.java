package contest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Grasshopper {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            String[] input = bufferedReader.readLine().split(" ");
            int fields = Integer.parseInt(input[0]);
            int jumpLength = Integer.parseInt(input[1]);
            int[] variants = new int[Math.max(fields, 2)];
            variants[0] = variants[1] = 1;
            for (int i = 2; i < fields; i++) {
                variants[i] = i < jumpLength + 1 ? 1 : 0;
                for (int j = i - 1; j >= Math.max(1, i - jumpLength); j--) {
                    variants[i] += variants[j];
                }
            }
            System.out.println(variants[fields - 1]);
        } catch (IOException ignored) {
        }
    }
}
