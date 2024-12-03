package aoc2023;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day11 {

    public static int expansionFactor = 999999;

    public static String test = """
            ...#......
            .......#..
            #.........
            ..........
            ......#...
            .#........
            .........#
            ..........
            .......#..
            #...#.....""";

    public static String expanded = """
            ....#........
            .........#...
            #............
            .............
            .............
            ........#....
            .#...........
            ............#
            .............
            .............
            .........#...
            #....#.......""";

    public static List<Integer> linesToExpand = new ArrayList<>();

    public static List<Integer> columnsToExpand = new ArrayList<>();

    public static List<Matched> matchedToCompute = new ArrayList<>();

    public static void main(String[] args) {
                List<List<Character>> universe = createUniverse(Day11Input.input);
//        List<List<Character>> universe = createUniverse(test);
        expand(universe);

        List<List<Character>> expandedUniverse = createUniverse(expanded);

        assert universe.equals(expandedUniverse);

        long tot = 0;
        for (int line = 0; line < universe.size(); line++) {
            List<Character> characters = universe.get(line);
            for (int column = 0; column < characters.size(); column++) {
                Character character = characters.get(column);
                if(character != '#') {
                    continue;
                }
                tot += sumPaths(universe, line, column);
            }
        }


        for (Matched matched : matchedToCompute) {
            Coordinates origin = matched.origin;
            Coordinates target = matched.target;

            long originLine = origin.x;
            long targetLine = target.x;
            long distanceLine = Math.abs(originLine - targetLine);

            for (Integer i : linesToExpand) {
                if(i > Math.min(originLine, targetLine) &&  i < Math.max(originLine, targetLine)) {
                    distanceLine += expansionFactor;
                }
            }

            long originColumn = origin.y;
            long targetColumn = target.y;
            long distanceColumn = Math.abs(originColumn - targetColumn);

            for (Integer i : columnsToExpand) {
                if(i > Math.min(originColumn, targetColumn) &&  i < Math.max(originColumn, targetColumn)) {
                    distanceColumn += expansionFactor;
                }
            }

            long matchDist = distanceLine + distanceColumn;
            tot += matchDist;

        }
        //1978913319 => too low
        System.out.println(tot);
    }

    public static Integer sumPaths(List<List<Character>> universe, int originLine, int originColumn) {

        boolean started = false;
        int sumPath = 0;
        for (int line = 0; line < universe.size(); line++) {
            if(!started && line < originLine) {
                continue;
            }

            List<Character> characters = universe.get(line);
            for (int column = 0; column < characters.size(); column++) {
                if(!started && column < originColumn) {
                    continue;
                }
                started = true;


                Character character = characters.get(column);
                if(character == '#') {
                    Coordinates origin = new Coordinates(originLine, originColumn);
                    Coordinates target = new Coordinates(line, column);
                    Matched matched = new Matched(origin, target);
                    matchedToCompute.add(matched);
                }
            }
        }

        return sumPath;
    }

    public static List<List<Character>> createUniverse(String input) {
        List<List<Character>> universe = new ArrayList<>();
        Stream<String> lines = input.lines();
        List<String> list = lines.toList();

        for (String s : list) {
            List<Character> curLine = new ArrayList<>();
            char[] charArray = s.toCharArray();
            for (char c : charArray) {
                curLine.add(c);
            }
            universe.add(curLine);
        }

        return universe;
    }

    public static void expand(List<List<Character>> universe) {

        for (int i = 0; i < universe.size(); i++) {
            List<Character> characters = universe.get(i);
            boolean empty = true;
            for (Character character : characters) {
                if(character != '.') {
                    empty = false;
                    break;
                }
            }

            if(empty) {
                linesToExpand.add(i);
            }
        }

        int size = universe.getFirst().size();
        for (int i = 0; i < size; ++i) {
            boolean empty = true;
            for (List<Character> characters : universe) {
                Character character = characters.get(i);
                if(character != '.') {
                    empty = false;
                    break;
                }
            }

            if(empty) {
                columnsToExpand.add(i);
            }
        }

    }

    public record Matched(Coordinates origin, Coordinates target) {}

    public record Coordinates(int x, int y) {}
}
