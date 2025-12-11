package aoc2025.day11;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class Resolver {

    private static Map<String, List<String>> graph;

    private static Map<String, Long> memo = new HashMap<>();

    private static long nbRes = 0;

    void main() {
        String input = Input.input;
        Stream<String> lines = input.lines();
        List<String> list = lines.toList();

        graph = new HashMap<>();
        for (String entry : list) {
            String[] split = entry.split(":");
            String key = split[0];
            List<String> value = Stream.of(split[1].split(" ")).filter(s -> !s.isEmpty()).toList();
            graph.put(key, value);
        }

        long svr = go2("svr", false, false);
        System.out.println(svr);

        System.out.println(nbRes);
    }

    private static long go2(String currentKey, boolean seenFft, boolean seenDac) {
        if ("fft".equals(currentKey)) seenFft = true;
        if ("dac".equals(currentKey)) seenDac = true;

        if ("out".equals(currentKey)) {
            return (seenFft && seenDac) ? 1 : 0;
        }

        String memoKey = currentKey + "|" + (seenFft ? "1" : "0") + (seenDac ? "1" : "0");
        if (memo.containsKey(memoKey)) {
            return memo.get(memoKey);
        }

        List<String> targets = graph.get(currentKey);
        long totalPaths = 0;
        for (String target : targets) {
            totalPaths += go2(target, seenFft, seenDac);
        }
        memo.put(memoKey, totalPaths);

        return totalPaths;
    }

    private static void go(String currentKey, Set<String> visitedKey) {
        if(!visitedKey.add(currentKey)) {
            return;
        }

        List<String> targets = graph.get(currentKey);
        for (String target : targets) {
            if("out".equals(target)) {
                if (visitedKey.contains("fft") && visitedKey.contains("dac")) {
                    ++nbRes;
                }
                return;
            }
            HashSet<String> newVisited = new HashSet<>(visitedKey);
            go(target, newVisited);
        }
    }
}
