package contest;

import java.io.InputStreamReader;
import java.util.*;

public class GreatLinelandMoving {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        int total = scanner.nextInt();
        ArrayDeque<int[]> costs = new ArrayDeque<>(total);
        List<String> resultList = new ArrayList<>(total);
        for (int i = 0; i < total; i++) {
            int current = scanner.nextInt();
            while (!costs.isEmpty() && costs.getLast()[0] > current) {
                resultList.set(costs.pollLast()[1], String.valueOf(i));
            }
            costs.addLast(new int[]{current, i});
            resultList.add("-1");
        }
        StringJoiner stringJoiner = new StringJoiner(" ");
        for (int i = 0; i < total; i++) {
            stringJoiner.add(resultList.get(i));
        }
        System.out.println(stringJoiner);
    }
}
