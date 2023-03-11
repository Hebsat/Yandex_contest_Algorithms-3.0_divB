package contest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;

public class DeckWithErrorDefence {
    public static void main(String[] args) {
        ArrayDeque<Integer> integers = new ArrayDeque<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            while (bufferedReader.ready()){
                String command = bufferedReader.readLine();
                if (command.startsWith("push_front ")) {
                    integers.addFirst(Integer.valueOf(command.substring(11)));
                    System.out.println("ok");
                    continue;
                }
                if (command.startsWith("push_back ")) {
                    integers.addLast(Integer.valueOf(command.substring(10)));
                    System.out.println("ok");
                    continue;
                }
                switch (command) {
                    case "pop_front" -> {
                        if (integers.isEmpty()) {
                            System.out.println("error");
                        } else {
                            System.out.println(integers.pollFirst());
                        }
                    }
                    case "pop_back" -> {
                        if (integers.isEmpty()) {
                            System.out.println("error");
                        } else {
                            System.out.println(integers.pollLast());
                        }
                    }
                    case "front" -> {
                        if (integers.isEmpty()) {
                            System.out.println("error");
                        } else {
                            System.out.println(integers.getFirst());
                        }
                    }
                    case "back" -> {
                        if (integers.isEmpty()) {
                            System.out.println("error");
                        } else {
                            System.out.println(integers.getLast());
                        }
                    }
                    case "size" -> System.out.println(integers.size());
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
        } catch (IOException ignored) {}
    }
}
