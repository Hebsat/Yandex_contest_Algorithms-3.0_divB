package contest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class HeapSort {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            StringJoiner stringJoiner = new StringJoiner(" ");
            int n = Integer.parseInt(bufferedReader.readLine());
            List<Integer> integers = new ArrayList<>(Arrays.stream(bufferedReader.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList()));
            for (int i = n / 2 - 1; i >= 0; i--) {
                heapify(integers, n, i);
            }
            for (int i = n - 1; i >= 0; i--) {
                int buffer = integers.get(0);
                integers.set(0, integers.get(i));
                integers.set(i, buffer);
                heapify(integers, i, 0);
            }
            integers.forEach(integer -> stringJoiner.add(String.valueOf(integer)));
            System.out.println(stringJoiner);
        } catch (IOException ignored) {
        }
    }

    public static void heapify(List<Integer> integers, int currentLength, int currentIndex) {
        int leftChildIndex = 2 * currentIndex + 1;
        int rightChildIndex = 2 * currentIndex + 2;
        int largestIndex = currentIndex;
        if (leftChildIndex < currentLength && integers.get(leftChildIndex) > integers.get(largestIndex)) {
            largestIndex = leftChildIndex;
        }
        if (rightChildIndex < currentLength && integers.get(rightChildIndex) > integers.get(largestIndex)) {
            largestIndex = rightChildIndex;
        }
        if (largestIndex != currentIndex) {
            int buffer = integers.get(currentIndex);
            integers.set(currentIndex, integers.get(largestIndex));
            integers.set(largestIndex, buffer);
            heapify(integers, currentLength, largestIndex);
        }
    }
}
