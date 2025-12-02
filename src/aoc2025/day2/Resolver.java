package aoc2025.day2;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Resolver {

    void main() {

        String input = Input.input;
        String[] split = input.split(",");
        long result = 0;
        for (String range : split) {
            String[] values = range.split("-");
            result += countInvalid(Long.parseLong(values[0]), Long.parseLong(values[1]));
        }
        System.out.println(result);
    }

    private static long countInvalid(long start, long end) {
        long currInvalids = 0;
        List<String > numbers = LongStream.rangeClosed(start, end)
                .boxed()/*.filter(i -> String.valueOf(i).length() % 2 == 0)*/
                .map(String::valueOf)
                .toList();

        for (String number : numbers) {
            long invalidPart2 = isInvalidPart2(number);
            currInvalids += invalidPart2;
        }
        return currInvalids;
    }

    private static long isInvalid(String id) {
        if(id.substring(0, id.length() / 2).equals(id.substring(id.length() / 2))) {
            return Long.parseLong(id);
        }
        return 0;
    }

    private static long isInvalidPart2(String id) {
        String pattern = "";
        while (pattern.length() < id.length() / 2) {
            pattern = id.substring(0, (pattern.length() + 1));
            Pattern compile = Pattern.compile("^(" + pattern + ")\\1+$");
            if(compile.matcher(id).find()) {
                return Long.parseLong(id);
            }
        }
        return 0;
    }
}
