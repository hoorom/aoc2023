package aoc2024.day8;

import aoc2023.Day11;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Resolver {

    static String test = """
            ............
            ........0...
            .....0......
            .......0....
            ....0.......
            ......A.....
            ............
            ............
            ........A...
            .........A..
            ............
            ............""";

    static String test2 = """
            ..........
            ...#......
            ..........
            ....a.....
            ..........
            .....a....
            ..........
            ......#...
            ..........
            ..........""";
    private static List<String> lines;

    public static void main(String[] args) {

        Map<Character, List<Day11.Coordinates>> antennes = new HashMap<>();

        lines = Input.input.lines().toList();
        for (int x = 0; x < lines.size(); x++) {
            String line = lines.get(x);
            char[] charArray = line.toCharArray();
            for (int y = 0; y < charArray.length; y++) {
                char c = charArray[y];

                if(c != '.') {
                    antennes.putIfAbsent(c, new ArrayList<>());
                    antennes.get(c).add(new Day11.Coordinates(x, y));
                }

            }
        }

        Set<Day11.Coordinates> nodes = new HashSet<>();
        for (Map.Entry<Character, List<Day11.Coordinates>> characterListEntry : antennes.entrySet()) {
            List<Day11.Coordinates> coordinates = characterListEntry.getValue();

            for (Day11.Coordinates currCoord : coordinates) {
                int currX = currCoord.x();
                int currY = currCoord.y();
                for (Day11.Coordinates targetCoord : coordinates) {

                    if(currCoord == targetCoord) {
                        continue;
                    }

                    int targetX = targetCoord.x();
                    int targetY = targetCoord.y();
// 3 / 4 ------- 5 / 6 => 2
                    int distanceX = Math.abs(currX - targetX);
                    int distanceY = Math.abs(currY - targetY);

                    allNodes(currX, targetX, distanceX, currY, targetY, distanceY, nodes);
                }
            }
        }

        int ok = 0;
        for (Day11.Coordinates node : nodes) {
            if(node.x() < 0 || node.y() < 0 || node.x() >= lines.size() || node.y() >= lines.getFirst().length()) {
                continue;
            }
            ++ok;
        }
        System.out.println(ok);
    }

    private static void allNodes(int currX, int targetX, int distanceX, int currY, int targetY, int distanceY, Set<Day11.Coordinates> nodes) {
        for (int f = 0; f < 100; f++) {
            int newDistX = distanceX * f;
            int newDistY = distanceY * f;


            int nodeX;
            if(currX > targetX) {
                nodeX = ( currX + newDistX);
            } else {
                nodeX = ( currX - newDistX);
            }

            int nodeY;
            if(currY > targetY) {
                nodeY = ( currY + newDistY);
            } else {
                nodeY = ( currY - newDistY);
            }
            nodes.add(new Day11.Coordinates(nodeX, nodeY));
        }
    }

}
