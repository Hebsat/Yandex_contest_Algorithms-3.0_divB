package contest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Diego {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());
            bufferedReader.readLine();
            List<Integer> numbers = new ArrayList<>(Arrays.stream(bufferedReader.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toSet()));
            Collections.sort(numbers);
            bufferedReader.readLine();
            Arrays.stream(bufferedReader.readLine().split(" ")).map(Integer::parseInt).forEach(integer -> {
                int index = Collections.binarySearch(numbers, integer);
                if (index > 0) {
                    stringJoiner.add(String.valueOf(index));
                }
                else if (index < -1) {
                    stringJoiner.add(String.valueOf(index + 1).substring(1));
                }
                else stringJoiner.add(String.valueOf(0));
                });
            System.out.println(stringJoiner);
        } catch (IOException ignored) {}
    }
}
