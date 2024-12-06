package aoc2024.day4;

import aoc2024.Direction;
import java.util.List;

public class Resolver {

    public static final List<Character> X = List.of('X', 'M', 'A', 'S');
    static int ok = 0;
    private static List<String> list;
    private static char[] cols;

    public static void main(String[] args) {
        String input = Input.input;

//        input = """
//                MMMSXXMASM
//                MSAMXMSMSA
//                AMXSXMAAMM
//                MSAMASMSMX
//                XMASAMXAMM
//                XXAMMXXAMA
//                SMSMSASXSS
//                SAXAMASAAA
//                MAMMMXMMMM
//                MXMXAXMASX""";

        list = input.lines().toList();
        for (int i = 0; i < list.size(); i++) {
            cols = list.get(i).toCharArray();
            for (int c = 0; c < cols.length; c++) {
                char currChar = cols[c];
                subM2(currChar, c, i);
            }

        }

        System.out.println(ok);
    }

//    private static void subM(char currChar, int c, int i, int indexToLook, Direction... directions) {
//
//        if(currChar == X.get(indexToLook)) {
//            if(indexToLook == 3) {
//
//                return;
//            }
//
//            for (int dir = 0; dir < directions.length; dir++) {
//                try {
//                    Direction currDir = directions[dir];
//                    Coordinate move = move(i, c, currDir);
//                    currChar = list.get(move.x).charAt(move.y);
//                    subM(currChar, move.y(), move.x(), indexToLook + 1, currDir);
//                } catch (Exception e) {
//                }
//            }
//        }
//    }

    private static void subM2(char currChar, int c, int i) {

        try {
            if(currChar == 'A') {
                List<Character> characters = List.of(list.get(i - 1).charAt(c + 1), list.get(i + 1).charAt(c - 1));
                if(characters.contains('M') && characters.contains('S')) {
                    List<Character> characters2 = List.of(list.get(i - 1).charAt(c - 1), list.get(i + 1).charAt(c + 1));
                    if(characters2.contains('M') && characters2.contains('S')) {
                        ++ok;
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public static Coordinate move(int x, int y, Direction direction) {
        return new Coordinate(x + direction.x, y + direction.y);
    }

    record Coordinate(int x, int y) {}


}
