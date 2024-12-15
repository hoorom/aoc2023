package aoc2024.day12;

import aoc2023.Day11;
import aoc2024.Direction;
import aoc2024.Plan;
import java.util.ArrayList;
import java.util.List;

public class Resolver {

    static List<Zone> zones = new ArrayList<>();

    static Plan plan;

    public static void main(String[] args) {

        String input = Input.input;

        plan = new Plan(input);

        List<char[]> cols = plan.cols;
        for (int x = 0; x < cols.size(); x++) {
            char[] c = cols.get(x);
            for (int y = 0; y < c.length; y++) {
                char currChar = c[y];
                Day11.Coordinates coordinates = new Day11.Coordinates(x, y);
                boolean alreadyTreated = false;
                for (Zone zone : zones) {
                    if(zone.coordinates.contains(coordinates)) {
                        alreadyTreated = true;
                        break;
                    }
                }

                if(alreadyTreated) {
                    continue;
                }

                Zone currentZone = new Zone(coordinates, currChar);
                zones.add(currentZone);

                sneek(coordinates, currentZone);
            }
        }

        for (Zone zone : zones) {
            System.out.println("Traitement zone : " + zone);
            Plan.Position origin = new Plan.Position(zone.origin.x(), zone.origin.y(), Direction.EAST);

            Plan.Position position = plan.nextPos(new Plan.Position(zone.origin.x(), zone.origin.y(), Direction.SOUTH));
            if(position == null || plan.getCharAtPosition(position) != plan.getCharAtPosition(origin)) {
                zone.nbSide = zone.nbSide +1;
            }

            if (parcours(zone, origin)) {
                break;
            }
        }

        long sum = 0;
        long sumP2 = 0;
        for (Zone zone : zones) {

            System.out.println(zone);
            sum += zone.area * zone.perimeter;
            sumP2 += zone.area * zone.nbSide;
        }

        System.out.println("P1 : " + sum);
        System.out.println("P2 : " + sumP2);
    }

    private static boolean parcours(Zone zone, Plan.Position origin) {
        System.out.println("Sur l ' arrete : " + origin + " curr sides : " + zone.nbSide);


        Plan.Position nextPos = findNextPosition(zone, origin);

        if(nextPos == null) {
            //mono zone
            zone.nbSide = 4;
            return false;
        }

        if(origin.direction() != nextPos.direction()) {
            if(origin.direction().isOpposite(nextPos.direction())) {
                zone.nbSide = zone.nbSide + 2;
            } else {
                zone.nbSide = zone.nbSide + 1;
            }
        }

        if(zone.origin.equals(nextPos.coordinates())) {
            Plan.Position nextFinalPost = findNextPosition(zone, nextPos);
//            if(nextFinalPost.coordinates().equals(origin.coordinates())) {
//                zone.nbSide = zone.nbSide + 1;
//            }
            return true;
        } else {
            parcours(zone, nextPos);
        }
        return false;
    }

    private static Plan.Position findNextPosition(Zone zone, Plan.Position origin) {
        List<Plan.Position> possibles = plan.moveAll(origin);
        Plan.Position nextPos = null;
        for (Plan.Position possible : possibles) {
            char nextChar = plan.getCharAtPosition(possible);
            if(nextChar != zone.carVal) {
                continue;
            }

            nextPos = possible;
            break;
        }
        return nextPos;
    }

    private static void sneek(Day11.Coordinates coordinates, Zone currentZone) {
        if(!currentZone.coordinates.add(coordinates)) {
            return;
        }
        currentZone.area = currentZone.area + 1;
        char charAtCoord = plan.getCharAtPosition(coordinates);
        List<Day11.Coordinates> allRelatedCoordinates = plan.moveAll(coordinates);
        currentZone.perimeter += 4 - allRelatedCoordinates.size();
        for (Day11.Coordinates relatedCoord : allRelatedCoordinates) {
            if(plan.getCharAtPosition(relatedCoord) != charAtCoord) {
                currentZone.perimeter = currentZone.perimeter + 1;
            } else {
                sneek(relatedCoord, currentZone);
            }
        }
    }
}
