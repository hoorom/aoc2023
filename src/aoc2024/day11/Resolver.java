package aoc2024.day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Resolver {

    public static void main(String[] args) {
        String input = Input.input;

        String[] split = input.split(" ");

        List<String> list = Arrays.stream(split).toList();

        long nbStone = 0;
        for (String s : list) {
            System.out.println(1);
            nbStone += blink(s,0 );
        }

        System.out.println(nbStone);
    }

    private static long blink(String s, int depth) {

        if(depth == 75) {
//            System.out.print(s+ " ");
            return 1;
        }

        List<String> list = new ArrayList<>();
        long l = Long.parseLong(s);
        s = String.valueOf(l);
        if(l == 0) {
            list.add("1");
        } else if(String.valueOf(l).length() % 2 == 0) {
            list.add(String.valueOf(Long.parseLong(s.substring(0, s.length() / 2))));
            list.add(String.valueOf(Long.parseLong(s.substring(s.length() / 2))));
        } else {
            list.add(String.valueOf(l * 2024));
        }

        long sumStone = 0;
        for (String newStones : list) {
            sumStone += blink(newStones, depth + 1);
        }
        return sumStone;
    }
}
