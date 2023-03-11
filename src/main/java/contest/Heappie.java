package contest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Heappie {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            List<Integer> heap = new ArrayList<>();
            StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());
            int n = Integer.parseInt(bufferedReader.readLine());
            for (int i = 0; i < n; i++) {
                String command = bufferedReader.readLine();
                if (command.startsWith("0")) {
                    int value = Integer.parseInt(command.substring(2));
                    heap.add(value);
                    int number = heap.size() - 1;
                    while (number > 0) {
                        int parentNumber = (number - 1) / 2;
                        int parent = heap.get(parentNumber);
                        if (parent >= value) break;
                        heap.set(parentNumber, value);
                        heap.set(number, parent);
                        number = parentNumber;
                    }
                } else if (command.equals("1")) {
                    stringJoiner.add(String.valueOf(heap.get(0)));
                    int buffer = heap.get(heap.size() - 1);
                    int number = 0;
                    heap.set(0, buffer);
                    heap.remove(heap.size() - 1);
                    int sonNumber = Math.min(1, heap.size());
                    while (sonNumber < heap.size()) {
                        int sonValue = heap.get(sonNumber);
                        if (heap.size() > sonNumber + 1) {
                            int rightSon = heap.get(sonNumber + 1);
                            if (rightSon > sonValue) {
                                sonValue = rightSon;
                                sonNumber++;
                            }
                        }
                        if (buffer >= sonValue) break;
                        heap.set(number, sonValue);
                        heap.set(sonNumber, buffer);
                        number = sonNumber;
                        sonNumber = number * 2 + 1;
                    }

                }
            }
            System.out.println(stringJoiner);
        } catch (IOException ignored) {
        }
    }
}
