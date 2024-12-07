package aoc2024.day7;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Resolver {

    public static final String test = """
            190: 10 19
            3267: 81 40 27
            83: 17 5
            156: 15 6
            7290: 6 8 6 15
            161011: 16 10 13
            192: 17 8 14
            21037: 9 7 18 13
            292: 11 6 16 20""";

    public static void main(String[] args) {
        List<String> list = Input.input.lines().toList();

        long solution = 0;
        for (String equation : list) {
            String[] split = equation.split(":");
            Long result = Long.parseLong(split[0]);

            String values = split[1];
            String[] strValues = values.split(" ");
            List<Long> val = new ArrayList<>();
            for (String strValue : strValues) {
                if(strValue == null || strValue.isEmpty())  {
                    continue;
                }
                val.add(Long.parseLong(strValue));
            }
            Long calculate = calculate(result, val);
            System.out.println("Adding : " + calculate + " / " + result);
            solution += calculate;
            System.out.println("Current : " + solution);
        }

        System.out.println(solution);
    }

    static Long calculate(Long target, List<Long> values) {
        Long result = 0L;
        Long calcResult = resultWithNext(target, values, 0, result);
        if(calcResult.equals(target)) {
            return calcResult;
        }
        return 0L;
    }

    private static Long resultWithNext(Long target, List<Long> values, int index, Long result) {
        if(index == values.size()) {
            return result;
        }
        Long addResult = result + values.get(index);
        Long resultWithNext = resultWithNext(target, values, index + 1, addResult);
        if(resultWithNext.equals(target)) {
            return resultWithNext;
        }
        Long resultWithMult = resultWithNext(target, values, index + 1, result * values.get(index));
        if(resultWithMult.equals(target)) {
            return resultWithMult;
        }

        long result1 = Long.parseLong("" + result + values.get(index));
        return resultWithNext(target, values, index + 1, result1);
    }
}
