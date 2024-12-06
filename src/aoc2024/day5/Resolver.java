package aoc2024.day5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Resolver {

    static Map<Integer, List<Integer>> map = new HashMap<>();
    private static int ok;

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
        for (String s : rulesTest.lines().toList()) {
            String[] split = s.split("\\|");

            List<Integer> integers = map.get(Integer.parseInt(split[0]));
            if(integers == null) {
                integers = new ArrayList<>();
                map.put(Integer.parseInt(split[0]), integers);
            }
        }

        String input = Input.input;
        String inputTest = """
                75,47,61,53,29
                97,61,53,29,13
                75,29,13
                75,97,47,61,53
                61,13,29
                97,13,75,29,47""";
        List<String> list = inputTest.lines().toList();
        for (String up : list) {
            String[] split = up.split(",");
            List<Integer> numbers = Arrays.stream(split).toList().stream().map(Integer::parseInt).toList();
            try {

                for (int i = 0; i < numbers.size(); i++) {
                    List<Integer> others = getOthers(i, numbers);
                    Integer key = numbers.get(i);
                    List<Integer> targets = map.get(key);
                    checkDepth(targets, others);
                }
            } catch (RuntimeException e) {
            } catch (IOException e1) {
                ok += numbers.get(numbers.size() / 2 + 1);
            }

        }
        System.out.println(ok);
    }

    private static void checkDepth(List<Integer> targets, List<Integer> others) throws IOException {
        for (Integer integer : targets) {
            if(others.contains(integer)) {
                throw new RuntimeException();
            }
            List<Integer> depths = map.get(integer);
            if(depths == null || depths.isEmpty()) {
                throw new IOException();
            }
            checkDepth(depths, others);
        }
    }

    public static List<Integer> getOthers(Integer index, List<Integer> numbers) {
        return numbers.subList(index, numbers.size());
    }
}
