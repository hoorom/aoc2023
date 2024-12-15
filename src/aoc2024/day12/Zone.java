package aoc2024.day12;

import aoc2023.Day11;
import java.util.HashSet;
import java.util.Set;

public class Zone {

    char carVal;

    Set<Day11.Coordinates> coordinates;

    Integer area = 0;

    Integer perimeter = 0;

    Day11.Coordinates origin;

    Integer nbSide = 1;

    Set<Day11.Coordinates> perimCoord = new HashSet<>();

    public Zone(Day11.Coordinates coordinates, char val) {
        this.coordinates = new HashSet<>();
        origin = coordinates;
        carVal = val;
    }

    @Override
    public String toString() {
        return "Zone{" + "carVal=" + carVal + ", area=" + area + ", perimeter=" + perimeter + ", nbSide=" + nbSide + '}';
    }
}
