package aoc2024.day19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Resolver {

    private static List<String> availables;
    public static Map<Integer, List<String>> LENGTH_TO_STRING_MAP;
    public static List<Integer> INTEGERS;
    private static final Map<String , Set<Integer>> matchingSize = new HashMap<>();
    private static final Set<String> hasMatched = new HashSet<>();

    private static int currentMatch = 0;
    private static Set<String> currentlyTreated = new HashSet<>();

    public static void main(String[] args) {
        List<String> list = Input.input.lines().toList();
        availables = Arrays.stream(list.getFirst().split(",")).map(String::trim).toList();
        LENGTH_TO_STRING_MAP = availables.stream()
                .collect(Collectors.groupingBy(String::length));
        INTEGERS = LENGTH_TO_STRING_MAP.keySet().stream().toList().stream().sorted().toList();


        int counter = 0;
        List<String> needed = list.subList(2, list.size());
        int i = 0;
        int match = 0;
        for (String need : needed) {
            currentlyTreated.clear();
            currentMatch = 0;
                        System.out.println(need + "("+ ++i + " / " + needed.size()+ ")");
            counter += matchMotif(need,"");
            System.out.println(need+ " : " + currentMatch);
            match+=currentMatch;
        }
        System.out.println(match);
    }

    private static Integer matchMotif(String need, String currChain) {
        if(!currentlyTreated.add(need+"#"+currChain)) {
            return 0;
        }
//        System.out.println("Trying : " + need);
        for (Integer integer : INTEGERS) {
//            if(integer > need.length()) {
//                break;
//            }
            for (String available : LENGTH_TO_STRING_MAP.get(integer)) {
                if(need.equals(available)) {
                    hasMatched.add(need);
                }

                if(need.startsWith(available)) {
//                    System.out.println(need + " : " + available);
                    if(matchingSize.get(need) == null) {
                        matchingSize.put(need, new HashSet<>());
                    }
                    matchingSize.get(need).add(integer);
//                    break;
                }
            }
        }

//        int matches = currPoss;
        if(hasMatched.contains(need)) {
            System.out.println("matchin " + currentMatch+"  on : " + currChain+"#"+need);
//            ++matches;
            ++currentMatch;
        }

        if(matchingSize.containsKey(need)) {
            Set<Integer> integers = matchingSize.get(need);
            for (Integer integer : integers) {
                if(integer < need.length()) {
                    String substring = need.substring(integer);
                    matchMotif(substring, currChain +"#"+ need.substring(0, integer));
                }
            }
        }

        return 0;
    }
}
