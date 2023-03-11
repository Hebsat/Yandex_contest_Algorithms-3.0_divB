package contest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class BeautyString {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            int k = Integer.parseInt(bufferedReader.readLine());
            String s = bufferedReader.readLine();
            int maxCount = 0;
            Set<Character> characters = new HashSet<>();
            for (int i = 0; i < s.length(); i++) {
                char currentChar = s.charAt(i);
                if (characters.contains(currentChar)) {
                    continue;
                }
                characters.add(currentChar);
                int startPeriod = Math.max(i - k, -1);
                int mana = Math.max(k - i, 0);
                int periodLength = 1 + k - mana;
                for (int j = i + 1; j < s.length(); j++) {
                    if (s.charAt(j) == currentChar) {
                        periodLength++;
                    }
                    else {
                       if (mana == 0) {
                           while (startPeriod < j) {
                               startPeriod++;
                               periodLength--;
                               if (s.charAt(startPeriod) != currentChar) {
                                   mana++;
                                   break;
                               }
                           }
                       }
                        periodLength++;
                        mana--;
                    }
                    if (maxCount < periodLength) maxCount = periodLength;
                }
            }
            System.out.println(maxCount);
        } catch (IOException ignored) {}
    }
}
