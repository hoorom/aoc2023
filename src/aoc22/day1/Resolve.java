package aoc22.day1;

import java.util.Arrays;
import java.util.stream.Stream;

public class Resolve {

    public static void main(String[] args) {
        long x = System.currentTimeMillis();
        Stream<String> lines = Input.input.lines();
        int currentCal = 0;
        int[] values = new int[3];
        Arrays.fill(values, 0);
        for (String line : lines.toList()) {
            if(line.isEmpty()) {
                int minCal = Integer.MAX_VALUE;
                int minCalIndex = 0;
                for (int i = 0; i < 3; i++) {
                    if(values[i] < minCal) {
                        minCalIndex = i;
                        minCal = values[i];
                    }
                }

                if(minCal < currentCal) {
                    values[minCalIndex] = currentCal;
                }

                currentCal = 0;
            } else {
                currentCal += Integer.parseInt(line);
            }
        }

        System.out.println(values[0] + values[1] + values[2]);
        System.out.println(System.currentTimeMillis() - x);
    }
}
