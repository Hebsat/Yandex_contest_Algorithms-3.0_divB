package contest;

import java.util.*;

public class Histogram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (scanner.hasNextLine()) {
            input = input + scanner.nextLine();
        }

        Map<Character, Integer> symbols = new TreeMap<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == ' ' || c == '\n') {
                continue;
            }
            int count = symbols.getOrDefault(c, 0) + 1;
            symbols.put(c, count);
        }
        int maxCount = symbols.values().stream().max(Integer::compareTo).get();
        List<String> printStrings = new ArrayList<>(maxCount);
        for (int a = 0; a < maxCount; a++) {
            printStrings.add("");
        }
        symbols.forEach((character, integer) -> {
            for (int s = 0; s < maxCount; s++) {
                String currentString = printStrings.get(s);
                currentString += s < integer ? '#' : ' ';
                printStrings.remove(s);
                printStrings.add(s, currentString);
            }
        });
        for (int p = printStrings.size() - 1; p >= 0; p--) {
            System.out.println(printStrings.get(p));
        }
        StringBuilder result = new StringBuilder();
        symbols.keySet().forEach(result::append);
        System.out.println(result);
    }
}