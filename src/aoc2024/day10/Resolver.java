package aoc2024.day10;

import aoc2023.Day11;
import aoc2024.Plan;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Resolver {

    public static int res = 0;
    static Day11.Coordinates currentOrigin = null;
    static Map<Day11.Coordinates, Integer> mapValByOrigin = new HashMap<>();
    static Map<Day11.Coordinates, Set<Day11.Coordinates>> accessed9ByOrigin = new HashMap<>();

    public static void main(String[] args) {
        Plan plan = new Plan(Input.input);


        List<Day11.Coordinates> origins = new ArrayList<>();
        List<char[]> cols = plan.cols;
        for (int i = 0; i < cols.size(); i++) {
            char[] col = cols.get(i);
            for (int j = 0; j < col.length; j++) {
                char c = col[j];
                if(c == '0') {
                    origins.add(new Day11.Coordinates(i, j));
                }
            }
        }

        for (Day11.Coordinates origin : origins) {
            currentOrigin = origin;
            int currPos = 0;
            depth(origin, plan, currPos);
        }

        System.out.println(res);
    }

    private static void depth(Day11.Coordinates origin, Plan plan, int currPos) {
        if(currPos == 9) {

//            Set<Day11.Coordinates> accessed = accessed9ByOrigin.get(currentOrigin);
//            if(accessed == null) {
//                accessed = new HashSet<>();
//                accessed9ByOrigin.put(currentOrigin, accessed);
//            } else {
//                if(accessed.contains(origin)) {
//                    return;
//                }
//            }
//            accessed.add(origin);

            Integer valToPut;
            if(mapValByOrigin.get(currentOrigin) == null) {
                valToPut = 1;
            } else {
                valToPut = mapValByOrigin.get(currentOrigin) + 1;
            }
            mapValByOrigin.put(currentOrigin, valToPut);

            res++;
            return;
        }

        List<Day11.Coordinates> newCoordinates = plan.moveAll(origin);
        for (Day11.Coordinates newCoordinate : newCoordinates) {
            if(Integer.parseInt(String.valueOf(plan.getCharAtPosition(newCoordinate))) == (currPos + 1)) {
                depth(newCoordinate, plan, currPos + 1);
            }
        }
    }
}
