package aoc2024.day18;

import aoc2023.Day11;
import aoc2024.Plan;
import java.util.List;

public class Resolver {

    public static void main(String[] args) {
        Plan plan = new Plan(7, 7);

        List<String> list = Input.test.lines().toList();
        for (int i = 0; i < 12; i++) {
            String in = list.get(i);
            String[] split = in.split(",");
            Day11.Coordinates coordinates = new Day11.Coordinates(Integer.parseInt(split[1]), Integer.parseInt(split[0]));
            plan.putCharAtCoord(coordinates, '#');
        }

        Day11.Coordinates from = new Day11.Coordinates(0, 0);
        Day11.Coordinates to = new Day11.Coordinates(6, 6);
        plan.dijkstra(from, to, '#');

        System.out.println(plan);
    }
}
