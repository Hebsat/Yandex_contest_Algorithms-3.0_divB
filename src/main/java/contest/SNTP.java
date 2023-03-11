package contest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class SNTP {
    public static void main(String[] args) {
        int day = 24 * 60 * 60;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            int timestampA = getTimestamp(Arrays.stream(bufferedReader.readLine().split(":")).map(Integer::parseInt).collect(Collectors.toList()));
            int timestampB = getTimestamp(Arrays.stream(bufferedReader.readLine().split(":")).map(Integer::parseInt).collect(Collectors.toList()));
            int timestampC = getTimestamp(Arrays.stream(bufferedReader.readLine().split(":")).map(Integer::parseInt).collect(Collectors.toList()));
            int difference = timestampA < timestampC ? timestampC - timestampA : day - timestampA + timestampC;
            timestampB += difference % 2 == 0 ? difference /2 : difference / 2 +1;
            if (timestampB > day) timestampB -= day;
            int hours = timestampB / 3600;
            int minutes = (timestampB - hours * 3600) / 60;
            int seconds = timestampB - (hours * 60 + minutes) * 60;
            System.out.println(new StringJoiner(":").add(getTimeString(hours)).add(getTimeString(minutes)).add(getTimeString(seconds)));
        } catch (IOException ignored) {}
    }

    private static int getTimestamp(List<Integer> time) {
        return time.get(0) * 3600 + time.get(1) * 60 + time.get(2);
    }

    private static String getTimeString(int value) {
        return value > 9 ? String.valueOf(value) : "0" + value;
    }
}
