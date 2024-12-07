package aoc2024.day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Resolver {

    static Map<Integer, List<Integer>> map = new HashMap<>();
    private static int ok;
    private static int ok2;

    public static void main(String[] args) {

        String rules = Input.rules;
        String rulesTest = """
                47|53
                97|13
                97|61
                97|47
                75|29
                61|13
                75|53
                29|13
                97|29
                53|29
                61|53
                97|53
                61|29
                47|13
                75|47
                97|75
                47|61
                75|61
                47|29
                75|13
                53|13""";
        for (String s : rules.lines().toList()) {
            String[] split = s.split("\\|");

            int val = Integer.parseInt(split[0]);
            int key = Integer.parseInt(split[1]);
            List<Integer> integers = map.get(key);
            if(integers == null) {
                integers = new ArrayList<>();
                map.put(key, integers);
            }
            map.get(key).add(val);

        }

        String input = Input.input;
        String inputTest = """
                75,47,61,53,29
                97,61,53,29,13
                75,29,13
                75,97,47,61,53
                61,13,29
                97,13,75,29,47""";
        List<String> list = input.lines().toList();

        List<String> wrongs = new ArrayList<>();
        for (String up : list) {
            String[] split = up.split(",");
            List<Integer> numbers = Arrays.stream(split).toList().stream().map(Integer::parseInt).toList();
            boolean cok = false;

            for (int i = 0; i < numbers.size(); i++) {
                List<Integer> others = getOthers(i, numbers);
                Integer key = numbers.get(i);
                List<Integer> targets = map.get(key);
                if(targets == null) {
                    cok = true;
                    continue;
                }
                cok = checkDepth(targets, others);
                if(!cok) {
                    break;
                }
            }

            if(cok) {
                //                System.out.println(numbers.toString());
//                System.out.println("Adding : " + numbers.get(numbers.size() / 2));
                ok += numbers.get(numbers.size() / 2);
            } else {
                wrongs.add(up);
            }

        }

        for (String wrong : wrongs) {
            fixWrong(wrong);

        }

        System.out.println(ok2);
    }

    private static void fixWrong(String wrong) {
        boolean fixed = false;

        while(!fixed) {
            fixed = true;
            String[] split = wrong.split(",");
            List<Integer> numbers = Arrays.stream(split).toList().stream().map(Integer::parseInt).toList();
            for (int i = 0; i < numbers.size(); i++) {
                List<Integer> others = getOthers(i, numbers);
                Integer key = numbers.get(i);
                List<Integer> targets = map.get(key);
                if(targets == null) {
                    continue;
                }

                Integer swap = checkWrong(targets, others);
                if(swap == null) {
                    continue;
                }
                List<Integer> integers = swapElementsInImmutableList(numbers, i, swap + i + 1);
                wrong = integers.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(","));
                fixed = false;
                break;
            }
        }

        String[] split = wrong.split(",");
        List<Integer> numbers = Arrays.stream(split).toList().stream().map(Integer::parseInt).toList();
        System.out.println("Adding : " + numbers.get(numbers.size() / 2));
        ok2 += numbers.get(numbers.size() / 2);
    }

    public static <T> List<T> swapElementsInImmutableList(List<T> list, int index1, int index2) {
        List<T> mutableList = new ArrayList<>(list);
        T temp = mutableList.get(index1);
        mutableList.set(index1, mutableList.get(index2));
        mutableList.set(index2, temp);
        return Collections.unmodifiableList(mutableList);
    }

    private static Integer checkWrong(List<Integer> targets, List<Integer> others) {
        for (Integer integer : targets) {
            if(others.contains(integer)) {
                return others.indexOf(integer);
            }
        }
        return null;
    }

    private static boolean checkDepth(List<Integer> targets, List<Integer> others) {
        for (Integer integer : targets) {
            if(others.contains(integer)) {
                return false;
            }
            List<Integer> depths = map.get(integer);
            if(depths == null || depths.isEmpty()) {
                continue;
            }
            //            return checkDepth(depths, others);
        }
        return true;
    }

    public static List<Integer> getOthers(Integer index, List<Integer> numbers) {
        return numbers.subList(index + 1, numbers.size());
    }
}
