package aoc2024.day1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Resolver {

    public static void main(String[] args) {
        String input = Input.input;
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (String s : input.lines().toList()) {
            String[] s1 = s.split(" {3}");
            list.add(Integer.parseInt(s1[0]));
            list2.add(Integer.parseInt(s1[1]));
        }

        int similarity = 0;
        for (Integer i : list) {
            int frequency = Collections.frequency(list2, i);
            similarity += frequency * i;
        }
        System.out.println(similarity);

//        Collections.sort(list);
//        Collections.sort(list2);
//
//        int totalDiff = 0;
//        for (int i = 0; i < list.size(); i++) {
//            Integer a = list.get(i);
//            Integer b = list2.get(i);
//            int distance = Math.abs(a - b);
//            totalDiff += distance;
//        }
//        System.out.println(totalDiff);
    }
}
