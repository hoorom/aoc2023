package aoc21.day1;

import java.util.Arrays;
import java.util.List;

public class Resolve {

    public static void main(String[] args) {
        String input = Input.input;
        List<Integer> list = input.lines().map(Integer::parseInt).toList();
//        int[] values = new int[3];
//        Arrays.fill(values, Integer.MIN_VALUE);
//        int nbIncrease = 0;
//        int count = 0;
//        for (String s : list) {
//            ++count;
//            int currMeasure = Integer.parseInt(s);
//
//            int lastDepth = values[0] + values[1] + values[2];
//            int currDepth = currMeasure + values[1] + values[2];
//
//            if(count >= 4 && currDepth > lastDepth) {
//                ++nbIncrease;
//            }
//            values[2] = values[1];
//            values[1] = values[0];
//            values[0] = currMeasure;
//
//            System.out.println(values);
//        }
//        System.out.println(nbIncrease);

        int nbIncrease = 0;
        for (int i = 3; i < list.size(); i++) {
            int curr = list.get(i) + list.get(i - 1) + list.get(i - 2);
            if(curr > list.get(i - 1) + list.get(i - 2) + list.get(i - 3)) {
                ++nbIncrease;
            }
        }
        System.out.println(nbIncrease);
    }

}
