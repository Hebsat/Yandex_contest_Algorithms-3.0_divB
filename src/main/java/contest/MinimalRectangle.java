package contest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class MinimalRectangle {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            int dotCount = Integer.parseInt(bufferedReader.readLine());
            int xMin = Integer.MAX_VALUE;
            int xMax = Integer.MIN_VALUE;
            int yMin = Integer.MAX_VALUE;
            int yMax = Integer.MIN_VALUE;
            for (int i = 0; i < dotCount; i++) {
                List<Integer> currentCoordinates = Arrays.stream(bufferedReader.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
                if (xMin > currentCoordinates.get(0)) xMin = currentCoordinates.get(0);
                if (xMax < currentCoordinates.get(0)) xMax = currentCoordinates.get(0);
                if (yMin > currentCoordinates.get(1)) yMin = currentCoordinates.get(1);
                if (yMax < currentCoordinates.get(1)) yMax = currentCoordinates.get(1);
            }
            System.out.println(new StringJoiner(" ")
                    .add(String.valueOf(xMin)).add(String.valueOf(yMin))
                    .add(String.valueOf(xMax)).add(String.valueOf(yMax)));
        } catch (IOException ignored) {}
    }
}
