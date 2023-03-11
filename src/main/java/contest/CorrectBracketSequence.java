package contest;

import java.util.Scanner;
import java.util.Stack;

public class CorrectBracketSequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sequence = scanner.nextLine();
        Stack<Character> brackets = new Stack<>();
        for (int i = 0; i < sequence.length(); i++) {
            char bracket = sequence.charAt(i);
            if (bracket == '(' || bracket == '[' || bracket == '{') {
                brackets.push(bracket);
            }
            if (brackets.isEmpty()) {
                System.out.println("no");
                return;
            }
            else switch (bracket) {
                case ')' -> {
                    if (brackets.peek().equals('(')) {
                        brackets.pop();
                    } else {
                        System.out.println("no");
                        return;
                    }
                }
                case ']' -> {
                    if (brackets.peek().equals('[')) {
                        brackets.pop();
                    } else {
                        System.out.println("no");
                        return;
                    }
                }
                case '}' -> {
                    if (brackets.peek().equals('{')) {
                        brackets.pop();
                    } else {
                        System.out.println("no");
                        return;
                    }
                }
            }
        }
        if (brackets.isEmpty()) {
            System.out.println("yes");
        }
        else System.out.println("no");
    }
}
