package aoc2024;

import aoc2023.Day11;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dijkstra {

    //all dots
    Map<Day11.Coordinates, Integer> visitedCoordinates = new HashMap<>();

    List<DijkstraPath> allPaths = new ArrayList<>();

    public boolean addNewPathIfLighter(DijkstraPath dijkstraPath) {

        Plan.Position last = dijkstraPath.getPath().getLast();
        Integer weight = visitedCoordinates.get(last.coordinates());
        if(weight != null && weight <= dijkstraPath.getCurrentWeight()) {
            return false;
        }
//        System.out.println(last + "lighter : " + dijkstraPath.getCurrentWeight() + " / " + weight);

        allPaths.add(dijkstraPath);
        visitedCoordinates.put(last.coordinates(), dijkstraPath.getCurrentWeight());

        return true;
    }

//    public Map<Plan.Position, Integer> getVisitedCoordinates() {
//        return visitedCoordinates;
//    }
//
//    public void setVisitedCoordinates(Map<Plan.Position, Integer> visitedCoordinates) {
//        this.visitedCoordinates = visitedCoordinates;
//    }

    public List<DijkstraPath> getAllPaths() {
        return allPaths;
    }

    public void setAllPaths(List<DijkstraPath> allPaths) {
        this.allPaths = allPaths;
    }
}
