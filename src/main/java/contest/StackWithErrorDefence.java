package contest;

import java.util.Scanner;
import java.util.Stack;

public class StackWithErrorDefence {

    static Stack<Integer> integers = new Stack<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.startsWith("push ")) {
                integers.push(Integer.valueOf(command.substring(5)));
                System.out.println("ok");
                continue;
            }
            switch (command) {
                case "pop" -> {
                    if (integers.isEmpty()) {
                        System.out.println("error");
                    } else {
                        System.out.println(integers.pop());
                    }
                }
                case "back" -> {
                    if (integers.isEmpty()) {
                        System.out.println("error");
                    } else {
                        System.out.println(integers.peek());
                    }
                }
                case "size" -> {
                    System.out.println(integers.size());
                }
                case "clear" -> {
                    integers.clear();
                    System.out.println("ok");
                }
                case "exit" -> {
                    System.out.println("bye");
                    return;
                }
                default -> System.out.println("error");
            }
        }
    }
}
