package contest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BoringLecture {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            String word = bufferedReader.readLine();
            Map<Character, Long> chars = new TreeMap<>();
            int wordLength = word.length();
            boolean oddLength = word.length() % 2 != 0;
            if (oddLength) wordLength++;
            for (int i = 0; i < wordLength / 2; i++) {
                List<Character> currentChars = new ArrayList<>(List.of(word.charAt(i), word.charAt(word.length() - 1 - i)));
                if (oddLength && i == wordLength / 2 - 1) {
                    currentChars.remove(1);
                }
                for (char character : currentChars) {
                    long count = chars.getOrDefault(character, 0L) + (long) (i + 1) * (word.length() - i);
                    chars.put(character, count);
                }
            }
            chars.forEach((c, i) -> System.out.println(c + ": " + i));
        } catch (IOException ignored) {}
    }
}
