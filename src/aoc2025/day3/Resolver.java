package aoc2025.day3;

import java.util.stream.Stream;

public class Resolver {


    void main() {
        String input = Input.input;

        Stream<String> lines = input.lines();
        long res = 0;
        for (String battery : lines.toList()) {
//            int max = findMax(battery);
            long max = p2FindMax(battery, 11);
            System.out.println(battery + " -> " + max);

            res += max;
        }
        System.out.println(res);
    }

    private static long p2FindMax(String battery, int remainingNumbers) {
//        System.out.println(battery + " -> " + remainingNumbers);
        char[] charArray = battery.toCharArray();

        int maxPower = 0;
        int posMaxPower = 0;

        for (int i = 0; i < charArray.length - remainingNumbers; i++) {
            char power = charArray[i];
//            if('9' == power) {
//                return Long.parseLong("9" + p2FindMax(battery.substring(i + 1), remainingNumbers - 1));
//            } else {
                int currPower = Integer.parseInt(power + "");
                if(currPower > maxPower) {
                    maxPower = currPower;
                    posMaxPower = i;
                }
//            }
        }

        if(remainingNumbers == 0) {
            return Long.parseLong(maxPower + "" );
        } else {
            return Long.parseLong(maxPower + "" + p2FindMax(battery.substring(posMaxPower + 1), remainingNumbers - 1));
        }

    }

    private static int findMax(String battery) {
        char[] charArray = battery.toCharArray();

        int maxPower = 0;
        int posMaxPower = 0;

        for (int i = 0; i < charArray.length - 1; i++) {
            char power = charArray[i];
            if('9' == power) {
                return Integer.parseInt("9" + findHigherVal(battery.substring(i + 1)));
            } else {
                int currPower = Integer.parseInt(power + "");
                if(currPower > maxPower) {
                    maxPower = currPower;
                    posMaxPower = i;
                }
            }
        }

        return Integer.parseInt(maxPower + "" + findHigherVal(battery.substring(posMaxPower + 1)));
    }

    private static int findHigherVal(String substring) {
        char[] charArray = substring.toCharArray();
        int maxPower = 0;
        for (char power : charArray) {
            if(power == '9') {
                return 9;
            }

            int currPower = Integer.parseInt(power + "");
            if(currPower > maxPower) {
                maxPower = currPower;
            }
        }
        return maxPower;
    }
}
