package aoc2024.day6;

import aoc2023.Day11;
import aoc2024.Direction;
import aoc2024.Plan;
import java.util.HashSet;
import java.util.Set;

public class Resolver {

    private static int tempLoop;

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        tempLoop = 0;

        String input = Input.input;
//        input = """
//                ....#.....
//                .........#
//                ..........
//                ..#.......
//                .......#..
//                ..........
//                .#..^.....
//                ........#.
//                #.........
//                ......#...""";
        for (int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == '#' || input.charAt(i) == '^') {
                continue;
            }
            String toCheck = input.substring(0, i) + '#' + input.substring(i + 1);
            Plan plan = new Plan(toCheck);
            plan.position = plan.getPositionForChar('^');

            Set<Plan.Position> visited = new HashSet<>();
            visited.add(plan.position);

            tempLoop = getTempLoop(plan, visited);
        }

//        Set<Day11.Coordinates> coordinates = new HashSet<>();
//        for (Plan.Position position : visited) {
//            coordinates.add(new Day11.Coordinates(position.x(), position.y()));
//        }
//        System.out.println(coordinates.size());

        System.out.println(tempLoop);
        System.out.println(System.currentTimeMillis() - l);
    }

    private static int getTempLoop(Plan plan, Set<Plan.Position> visited) {
        Plan.Position nextPosition = plan.nextPos(plan.position);
        while(nextPosition != null) {
            while(plan.getCharAtPosition(nextPosition) == '#') {
                plan.position = new Plan.Position(plan.position.x(), plan.position.y(), Direction.getTurn90Right(plan.position.direction()));
                nextPosition = plan.nextPos(plan.position);
            }

            plan.move(plan.position);
            if(!visited.add(plan.position)) {
                tempLoop++;
                break;
            }
            nextPosition = plan.nextPos(plan.position);
        }
        return tempLoop;
    }
}
