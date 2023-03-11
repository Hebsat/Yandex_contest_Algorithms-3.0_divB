package contest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;

public class QueueWithErrorDefence {
    public static void main(String[] args) {
        ArrayDeque<Integer> integers = new ArrayDeque<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            while (bufferedReader.ready()) {
                String command = bufferedReader.readLine();
                if (command.startsWith("push ")) {
                    integers.addLast(Integer.valueOf(command.substring(5)));
                    System.out.println("ok");
                    continue;
                }
                switch (command) {
                    case "pop" -> {
                        if (integers.isEmpty()) {
                            System.out.println("error");
                        } else {
                            System.out.println(integers.pollFirst());
                        }
                    }
                    case "front" -> {
                        if (integers.isEmpty()) {
                            System.out.println("error");
                        } else {
                            System.out.println(integers.getFirst());
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
