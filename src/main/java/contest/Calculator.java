package contest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Calculator {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            int n = Integer.parseInt(bufferedReader.readLine());
            boolean notFound = n != 1;
            int currentStep = 0;
            int[] values = new int[n + 1];
            List<Integer> valuesByStep = new ArrayList<>(List.of(1));
            while (notFound) {
                currentStep++;
                List<Integer> lastStepValues = new ArrayList<>(valuesByStep);
                valuesByStep = new ArrayList<>();
                for (int lastStepValue : lastStepValues) {
                    int variable = lastStepValue * 2;
                    if (variable <= n && values[variable] == 0) {
                        valuesByStep.add(variable);
                        values[variable] = lastStepValue;
                        if (variable == n) {
                            notFound = false;
                            break;
                        }
                    }
                    variable = lastStepValue * 3;
                    if (variable <= n && values[variable] == 0) {
                        valuesByStep.add(variable);
                        values[variable] = lastStepValue;
                        if (variable == n) {
                            notFound = false;
                            break;
                        }
                    }
                    variable = lastStepValue + 1;
                    if (variable <= n && values[variable] == 0) {
                        valuesByStep.add(variable);
                        values[variable] = lastStepValue;
                        if (variable == n) {
                            notFound = false;
                            break;
                        }
                    }
                }
            }
            List<Integer> finalSequence = new ArrayList<>(List.of(n));
            int lastValue = n;
            while (lastValue > 1) {
                finalSequence.add(values[lastValue]);
                lastValue = values[lastValue];
            }
            StringJoiner sequence = new StringJoiner(" ");
            System.out.println(currentStep);
            for (int i = finalSequence.size() - 1; i >= 0; i--) {
                sequence.add(String.valueOf(finalSequence.get(i)));
            }
            System.out.println(sequence);
        } catch (IOException ignored) {
        }
    }
}
