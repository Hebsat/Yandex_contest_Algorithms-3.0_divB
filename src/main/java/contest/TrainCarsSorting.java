package contest;

import java.util.Scanner;
import java.util.Stack;

public class TrainCarsSorting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int total = Integer.parseInt(scanner.nextLine());
        String[] sequence = scanner.nextLine().split(" ");
        Stack<Integer> railway = new Stack<>();
        int count = 1;
        for (String s : sequence) {
            int current = Integer.parseInt(s);
            if (current == count) {
                count++;
                while (!railway.isEmpty() && railway.peek() == count) {
                    railway.pop();
                    count++;
                }
            } else if (!railway.isEmpty() && railway.peek() < current) {
                System.out.println("NO");
                return;
            } else railway.push(current);
        }
        System.out.println("YES");
    }
}
