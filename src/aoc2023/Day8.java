package aoc2023;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day8 {

    public static String test1 = """
            AAA = (BBB, CCC)
            BBB = (DDD, EEE)
            CCC = (ZZZ, GGG)
            DDD = (DDD, DDD)
            EEE = (EEE, EEE)
            GGG = (GGG, GGG)
            ZZZ = (ZZZ, ZZZ)""";

    public static String test2 = """
            11A = (11B, XXX)
            11B = (XXX, 11Z)
            11Z = (11B, XXX)
            22A = (22B, XXX)
            22B = (22C, 22C)
            22C = (22Z, 22Z)
            22Z = (22B, 22B)
            XXX = (XXX, XXX)""";

    public static Map<String, Map.Entry<String, String>> nodes = new HashMap<>();
    public static String directions;

    public static void main(String[] args) {
        directions = Day8Input.dir;
        initMap(Day8Input.input);
        System.out.println(walk());
    }

    public static Integer walk() {
        int distance = 0;

        List<String> currPoses = new LinkedList<>();
        Set<String> keys = nodes.keySet();
        for (String key : keys) {
            if(key.endsWith("A")) {
                currPoses.add(key);
            }
        }

        Map<Integer , Integer> mapFirstMatch = new HashMap<>();
        List<Character> charList = directions.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        while (mapFirstMatch.size() < currPoses.size()) {
            ++distance;
            List<String> newPoses = new LinkedList<>();
            if(charList.isEmpty()) {
                charList = directions.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
            }
            char c = charList.remove(0);
            for (String currPos : currPoses) {

                Map.Entry<String, String> entry = nodes.get(currPos);
                String newPos;
                if(c == 'R') {
                    newPos = entry.getValue();
                } else {
                    newPos = entry.getKey();
                }

                if(newPos.endsWith("Z")) {
                    int key = currPoses.indexOf(currPos);
                    if(mapFirstMatch.get(key) == null) {
                        mapFirstMatch.put(key, distance);
                    }
                }

                newPoses.add(newPos);
            }
            currPoses = newPoses;
        }
        return distance;
    }

    public static boolean isEnd(List<String> poses) {
        for (String pose : poses) {
            if(!pose.endsWith("Z")) {
                return false;
            }
        }
        return true;
    }

    private static void initMap(String s) {
        Stream<String> lines = s.lines();
        List<String> list = lines.toList();
        for (String string : list) {
            String[] split = string.split("=");
            String keyNode = split[0].trim();
            String targetNodes = split[1].trim();
            String nodesCleaned = targetNodes.replace("(", "").replace(")", "").trim();
            String[] splitted = nodesCleaned.split(",");

            nodes.put(keyNode, Map.entry(splitted[0].trim(), splitted[1].trim()));

        }
    }
}
