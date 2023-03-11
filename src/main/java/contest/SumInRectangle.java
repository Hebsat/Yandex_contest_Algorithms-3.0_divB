package contest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SumInRectangle {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            List<Integer> countsMNK = Arrays.stream(bufferedReader.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            List<List<Integer>> values = new ArrayList<>(countsMNK.get(0));
            for (int i = 0; i < countsMNK.get(0); i++) {
                List<Integer> current = (Arrays.stream(bufferedReader.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList()));
                int currentTotalSum = 0;
                int currentRowSum = 0;
                List<Integer> currentRow = new ArrayList<>(countsMNK.get(1));
                for (int j = 0; j < countsMNK.get(1); j++) {
                    if (i > 0) {
                        currentRowSum += current.get(j);
                        currentTotalSum = values.get(i - 1).get(j) + currentRowSum;
                    }
                    else currentTotalSum += current.get(j);
                    currentRow.add(currentTotalSum);
                }
                values.add(currentRow);
            }
            for (int i = 0; i < countsMNK.get(2); i++) {
                List<Integer> rectangle = Arrays.stream(bufferedReader.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
                int partOne = 0;
                if (rectangle.get(0) > 1) partOne = values.get(rectangle.get(0) - 2).get(rectangle.get(3) - 1);
                int partTwo = 0;
                if (rectangle.get(1) > 1) partTwo = values.get(rectangle.get(2) - 1).get(rectangle.get(1) - 2);
                int partThree = 0;
                if (rectangle.get(0) > 1 && rectangle.get(1) > 1) partThree = values.get(rectangle.get(0) - 2).get(rectangle.get(1) - 2);
                System.out.println(values.get(rectangle.get(2) - 1).get(rectangle.get(3) - 1) - partOne - partTwo + partThree);
            }
        } catch (IOException ignored) {}
    }
}
