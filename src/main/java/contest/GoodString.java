package contest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GoodString {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(bufferedReader.readLine());
            List<Integer> chars = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                chars.add(Integer.parseInt(bufferedReader.readLine()));
            }
            long beautyValue = 0;
            int lastCharValue = chars.get(0);
            for (int i = 1; i < n; i++) {
                int currentCharValue = chars.get(i);
                beautyValue += Math.min(lastCharValue, currentCharValue);
                lastCharValue = currentCharValue;
            }
            System.out.println(beautyValue);
        } catch (IOException ignored) {}
    }
}
