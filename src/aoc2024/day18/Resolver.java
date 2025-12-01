package aoc2024.day18;

import aoc2023.Day11;
import aoc2024.Plan;
import java.util.List;

public class Resolver {

    public static void main(String[] args) {
        Plan plan = new Plan(71, 71);

        List<String> list = Input.input.lines().toList();
        int nbSupCorr = 0;
        int dijkstra = 0;
        while (true) {
            for (int i = 0; i < 1024 + nbSupCorr; i++) {
                String in = list.get(i);
                System.out.println("Corrupting : " + in);
                String[] split = in.split(",");
                Day11.Coordinates coordinates = new Day11.Coordinates(Integer.parseInt(split[1]), Integer.parseInt(split[0]));
                plan.putCharAtCoord(coordinates, '#');
            }

            Day11.Coordinates from = new Day11.Coordinates(0, 0);
            Day11.Coordinates to = new Day11.Coordinates(70, 70);
            dijkstra = plan.dijkstra(from, to, '#');
            ++nbSupCorr;
        }

    }
}
