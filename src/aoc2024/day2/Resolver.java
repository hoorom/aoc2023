package aoc2024.day2;

import java.util.Arrays;
import java.util.List;

public class Resolver {

    public static void main(String[] args) {
        String input = Input.input;

        List<String> list = input.lines().toList();
        int nbOk = 0;
        for (String s : list) {
            String[] split = s.split(" ");
            for (int i = 0; i < split.length; i++) {
                String[] removedTab = tabWithRemove(split, i);
                if(isOk(removedTab, true)) {
                    ++nbOk;
                    break;
                }
            }
        }

        System.out.println(nbOk);
    }

    private static boolean isOk(String[] split, boolean alreadyTried) {
        Boolean grow;
        int failIndex = 0;
        try {
            if(Integer.parseInt(split[1]) > Integer.parseInt(split[0])) {
                grow = true;
            } else {
                grow = false;
            }
            for (int i = 0; i < split.length; i++) {
                if (i >= 1) {
                    int currVal = Integer.parseInt(split[i]);
                    int lastVal = Integer.parseInt(split[i - 1]);
                    if((currVal > lastVal) != grow) {
                        failIndex = i;
                        throw new RuntimeException();
                    }

                    int diff = Math.abs(currVal - lastVal);
                    if( diff < 1 || diff > 3) {
                        failIndex = i;
                        throw new RuntimeException();
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private static String[] tabWithRemove(String[] split, int failIndex) {
        String[] newArray = new String[split.length - 1];

        int j = 0;
        for (int i = 0; i < split.length; i++) {
            if (i != failIndex) {
                newArray[j++] = split[i];
            }
        }
        return newArray;
    }
}
