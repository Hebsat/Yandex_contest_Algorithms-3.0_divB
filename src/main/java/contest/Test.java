package contest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            int students = Integer.parseInt(bufferedReader.readLine());
            int variants = Integer.parseInt(bufferedReader.readLine());
            int row = Integer.parseInt(bufferedReader.readLine());
            int seat = Integer.parseInt(bufferedReader.readLine());

            int currentSeat = (row - 1) * 2 + seat;
            if (variants % 2 == 0) {
                System.out.println((currentSeat + variants) <= students ?
                        ((row + variants / 2) + " " + seat) :
                        ((currentSeat - variants) > 0 ? (row - variants / 2) + " " + seat : "-1"));
            }
            else if (seat % 2 == 0){
                System.out.println((currentSeat - variants) > 0 ? (row - variants / 2) + " 1" :
                        (currentSeat + variants) > students ? "-1" : (row + 1 + variants / 2) + " 1");
            }
            else {
                System.out.println((currentSeat + variants) <= students ? (row + variants / 2) + " 2" :
                        (currentSeat - variants) > 0 ? (row - 1 - variants / 2) + " 2" : "-1");
            }
        } catch (IOException ignored) {}
    }
}
