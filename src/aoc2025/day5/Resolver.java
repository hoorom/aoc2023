package aoc2025.day5;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Resolver {

//    void main() {
//        String input = Input.input;
//
//        Stream<String> lines = input.lines();
//        List<String> list = lines.toList();
//
//        boolean validRange = true;
//        Set<Range> validIds = new HashSet<>();
//
//
//
//        for (String val : list) {
//            if(val.isEmpty()) {
//                validRange = false;
//                break;
//            }
//
//            if (validRange) {
//                String[] split = val.split("-");
//                long start = Long.parseLong(split[0]);
//                long end = Long.parseLong(split[1]);
//
//                Range inRange = isInRange(start, validIds);
//                while (inRange != null) {
//                    System.out.println("Modif : " + start + " a cause de :" + inRange + "(new val " +(inRange.end + 1)+ ")");
//                    start = inRange.end + 1;
//                    inRange = isInRange(start, validIds);
//                }
//
//                inRange = isInRange(end, validIds);
//                while (inRange != null) {
//                    end = inRange.start - 1;
//                    inRange = isInRange(end, validIds);
//                }
//
//                validIds.add(new Range(start, end));
//            } else {
//                //                if (isInRange(val, validIds) != null) {
//                //                    ++nbValid;
//                //                }
//            }
//        }
////        System.out.printf("nbValid -> " + nbValid);
//
//        long p2Res = 0;
//        for (Range validId : validIds) {
//            p2Res += validId.end - validId.start + 1;
//        }
//        System.out.println(p2Res);
//
//        long sum = validIds.stream().mapToLong(e -> e.end - e.start + 1).sum();
//        System.out.println(sum);
//
//    }
//
//    private static Range isInRange(long val, Set<Range> validIds) {
//        for (Range range : validIds) {
//            if (range.isInRange(val)) {
//                return range;
//            }
//        }
//        return null;
//    }
//
//    record Range(long start, long end) {
//
//        boolean isInRange(long value) {
//            return start <= value && value <= end;
//        }
//
//    }

void main() {
    String input = Input.input;
    List<Range> ranges = new ArrayList<>();

    for (String val : input.lines().toList()) {
        if (val.isEmpty()) {
            break;
        }

        String[] split = val.split("-");
        long start = Long.parseLong(split[0]);
        long end = Long.parseLong(split[1]);

        // Fusion des plages qui se chevauchent
        Range newRange = new Range(start, end);
        ranges = mergeRanges(ranges, newRange);
    }

    // Calcul du total
    long total = ranges.stream()
            .mapToLong(r -> r.end - r.start + 1)
            .sum();

    System.out.println(total);
}

    private List<Range> mergeRanges(List<Range> existing, Range newRange) {
        List<Range> result = new ArrayList<>();
        Range current = newRange;

        for (Range range : existing) {
            if (overlaps(current, range)) {
                current = merge(current, range);
            } else {
                result.add(range);
            }
        }

        result.add(current);
        return result;
    }

    private boolean overlaps(Range a, Range b) {
        return !(a.end < b.start - 1 || b.end < a.start - 1);
    }

    private Range merge(Range a, Range b) {
        return new Range(
                Math.min(a.start, b.start),
                Math.max(a.end, b.end)
        );
    }

    record Range(long start, long end) {}

}
