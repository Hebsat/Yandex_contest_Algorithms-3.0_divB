package contest;

import java.util.ArrayDeque;
import java.util.Scanner;

public class GameInDrunk {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayDeque<Integer> firstPlayerHand = new ArrayDeque<>(10);
        ArrayDeque<Integer> secondPlayerHand = new ArrayDeque<>(10);
        for (int i = 0; i < 5; i++) firstPlayerHand.addFirst(scanner.nextInt());
        for (int i = 0; i < 5; i++) secondPlayerHand.addFirst(scanner.nextInt());
        int rounds = 0;
        while (!firstPlayerHand.isEmpty() && !secondPlayerHand.isEmpty()) {
            if (rounds == 1000000) {
                System.out.println("botva");
                return;
            }
            rounds++;
            int first = firstPlayerHand.removeLast();
            int second = secondPlayerHand.removeLast();
            if ((first > second && !(first == 9 && second == 0)) || (first == 0 && second == 9)) {
                firstPlayerHand.addFirst(first);
                firstPlayerHand.addFirst(second);
            }
            else {
                secondPlayerHand.addFirst(first);
                secondPlayerHand.addFirst(second);
            }
        }
        System.out.println(firstPlayerHand.isEmpty() ? ("second " + rounds) : ("first " + rounds));
    }
}
