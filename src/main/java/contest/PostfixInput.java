package contest;

import java.util.Scanner;
import java.util.Stack;

public class PostfixInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] postfixString = scanner.nextLine().trim().split(" ");
        Stack<Integer> operands = new Stack<>();
        for (String operand : postfixString) {
            if (Character.isDigit(operand.charAt(0))) {
                operands.push(Integer.parseInt(operand));
            } else {
                int second = operands.pop();
                int first = operands.pop();
                switch (operand) {
                    case "+" -> operands.push(first + second);
                    case "-" -> operands.push(first - second);
                    case "*" -> operands.push(first * second);
                }
            }
        }
        System.out.println(operands.pop());
    }
}
