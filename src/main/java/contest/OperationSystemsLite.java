package contest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class OperationSystemsLite {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            String sectors = bufferedReader.readLine();
            int partitions = Integer.parseInt(bufferedReader.readLine());
            Map<Integer, Integer> installed = new TreeMap<>();
            for (int i = 0; i < partitions; i++) {
               List<Integer> range = Arrays.stream(bufferedReader.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
               if (installed.isEmpty()) {
                   installed.put(range.get(0), range.get(1));
                   continue;
               }
               List<Integer> keysToRemove = new ArrayList<>();
               for (int j : installed.keySet()) {
                   if (j < range.get(0)) {
                       if (installed.get(j) >= range.get(0)) {
                           keysToRemove.add(j);
                       }
                   }
                   else {
                       if (j <= range.get(1)) {
                           keysToRemove.add(j);
                       }
                       else {
                           break;
                       }
                   }
               }
               keysToRemove.forEach(installed::remove);
               installed.put(range.get(0), range.get(1));
            }
            System.out.println(installed.size());
        } catch (IOException ignored) {}
    }
}
