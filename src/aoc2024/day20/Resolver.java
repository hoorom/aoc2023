package aoc2024.day20;

import aoc2023.Day11;
import aoc2024.Plan;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Resolver {
//
//    public static void main(String[] args) {
//
//        Plan plan = new Plan(Input.input);
//        Plan.Position start = plan.getPositionForChar('S');
//        Plan.Position end = plan.getPositionForChar('E');
//        Plan.DijkstraResult dijkstraResult = plan.dijkstraWithResult(start.coordinates(), end.coordinates(), '#');
//        int initialDijkstra = dijkstraResult.result();
//
//        Set<Plan.Position> possibleNeighbours = new HashSet<>();
//
//        List<Plan.Position> bestPath = dijkstraResult.dijkstraPath().getPath();
//        System.out.println("Bet path : " + bestPath.size());
//        for (Plan.Position position : bestPath) {
//            List<Plan.Position> positions = plan.moveAll(position);
//            possibleNeighbours.addAll(positions);
//        }
//
//        int nbCheat = 0;
//
//        System.out.println(possibleNeighbours.size());
//
//
//        for (Plan.Position possibleNeighbour : possibleNeighbours) {
//
//            Day11.Coordinates currSwitch = new Day11.Coordinates(possibleNeighbour.x(), possibleNeighbour.y());
//            List<Day11.Coordinates> coordinates = plan.moveAll(currSwitch);
//            for (Day11.Coordinates neighbour : coordinates) {
//                if (neighbour.x() == 0 || neighbour.x() == plan.cols.size() - 2 || neighbour.y() == 0 || neighbour.y() == plan.cols.get(0).length - 2) {
//                    continue;
//                }
//
//                if (plan.getCharAtPosition(neighbour) == '.' && plan.getCharAtPosition(currSwitch) == '.') {
//                    continue;
//                }
//                Plan newPLan = new Plan(plan);
//                newPLan.putCharAtCoord(currSwitch, '.');
//                newPLan.putCharAtCoord(neighbour, '.');
//
//                int newDijkstra = 0;
//                try {
//                    newDijkstra = plan.cheatingDijkstra(neighbour, end.coordinates(), List.of(currSwitch, neighbour), '#');
//                } catch (Exception e) {
//                    continue;
//                }
//                System.out.println("Initial : " + initialDijkstra + " / New : " + newDijkstra + "");
//                if (newDijkstra <= initialDijkstra - 100) {
//                    ++nbCheat;
//                }
//            }
//        }
//
//        System.out.println(nbCheat);
//    }
}
