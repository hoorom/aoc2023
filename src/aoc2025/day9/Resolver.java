package aoc2025.day9;

import aoc2023.Day11;
import java.util.ArrayList;
import java.util.List;

public class Resolver {

    void main() {
        String input = Input.input;

        List<Day11.Coordinates> allCoordinates = new ArrayList<>();
        List<String> list = input.lines().toList();
        for (String coord : list) {
            String[] split = coord.split(",");
            Day11.Coordinates coordinates = new Day11.Coordinates(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            allCoordinates.add(coordinates);
        }

        long maxArea = 0;
        for (int i = 0; i < allCoordinates.size(); i++) {
            for (int j = i + 1; j < allCoordinates.size(); j++) {
                long area = new Square(allCoordinates.get(i), allCoordinates.get(j)).area();
                maxArea = Math.max(maxArea, area);
            }
        }
        System.out.println(maxArea);
    }

    record Square(Day11.Coordinates a, Day11.Coordinates b) {
        long area() {
            return (long) Math.abs(a.x() - b.x() + 1) * Math.abs(a.y() - b.y() + 1);
        }
    }
}
