package aoc2023;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day13 {

    static String test = """
            #.##..##.
            ..#.##.#.
            ##......#
            ##......#
            ..#.##.#.
            ..##..##.
            #.#.##.#.
                        
            #...##..#
            #....#..#
            ..##..###
            #####.##.
            #####.##.
            ..##..###
            #....#..#""";

    static List<Symbol> symbols = new ArrayList<>();

    public static void main(String[] args) {
        parse(Day13Input.input);
//        parse(test);


        int sum = 0;
        for (Symbol symbol : symbols) {
            int sum1 = calculateSum(symbol, null);
//            sum += sum1;
            int newSum = transform(symbol, sum1);
            System.out.println(newSum);
            sum += newSum;
        }
        System.out.println(sum);
    }

    private static int transform(Symbol symbol, int oldSum) {

        Symbol transformed = new Symbol(new ArrayList<>(symbol.lines), new ArrayList<>(symbol.columns));
        List<List<Character>> lines = symbol.lines;
        for (int i = 0; i < transformed.lines.size(); i++) {
            List<Character> characters = lines.get(i);
            for (int j = 0; j < characters.size(); j++) {
                if(characters.get(j) == '.') {
                    characters.set(j, '#');

                } else {
                    characters.set(j, '.');
                }
                int newSum = calculateSum(symbol, oldSum);
                if(newSum != 0 && newSum != oldSum) {
                    return newSum;
                } else {
                    if(characters.get(j) == '.') {
                        characters.set(j, '#');

                    } else {
                        characters.set(j, '.');
                    }
                }
            }
        }

        for (int i = 0; i < transformed.columns.size(); i++) {
            List<Character> characters = transformed.columns.get(i);
            for (int j = 0; j < characters.size(); j++) {
                if(characters.get(j) == '.') {
                    characters.set(j, '#');

                } else {
                    characters.set(j, '.');
                }
                int newSum = calculateSum(symbol, oldSum);
                if(newSum != 0 && newSum != oldSum) {
                    return newSum;
                } else {
                    if(characters.get(j) == '.') {
                        characters.set(j, '#');

                    } else {
                        characters.set(j, '.');
                    }
                }
            }
        }
//        throw new RuntimeException();
        return 0;
    }

    private static int calculateSum(Symbol symbol, Integer oldSum) {
        int verticalMirror = findHorizontalMirror(symbol);
        int horizontalMirror = findVerticalMirror(symbol);
//        if(verticalMirror != 0) {
//            return verticalMirror;
//        }
//        return horizontalMirror;

//        if(horizontalMirror == 0 && verticalMirror == 0) {
//            findVerticalMirror(symbol);
//            throw new RuntimeException();
//        }

//        if(horizontalMirror != 0  && (oldSum == null || oldSum != horizontalMirror)) {
//            return horizontalMirror;
//        }
//        return verticalMirror;

        if(verticalMirror != 0  && (oldSum == null || oldSum != verticalMirror)) {
            return verticalMirror;
        }
        return horizontalMirror;


    }

    private static int findHorizontalMirror(Symbol symbol) {
        for (int i = symbol.lines.size() - 2; i >= 0; i--) {
            List<Character> currList = symbol.lines.get(i);
            List<Character> mirrorList = symbol.lines.get(i + 1);

            //Potentiellement tester jusqu'a la fin
            if(currList.equals(mirrorList)) {
                int k = 1;
                boolean ko = false;
                while (i - k >= 0 && i + k < symbol.lines.size() - 1) {
                    if(!symbol.lines.get(i - k).equals(symbol.lines.get(i + k + 1))) {
                        ko = true;
                        break;
                    }
                    ++k;
                }
//                System.out.println("-------------HORIZONTAL-----------------");
//                System.out.println(currList);
//                System.out.println(mirrorList);
                if(!ko) {
                    return (i + 1) * 100;
                }
            }
        }
        return 0;
    }

    private static int findVerticalMirror(Symbol symbol) {
        for (int i = symbol.columns.size() - 2; i >= 0; i--) {
            List<Character> currList = symbol.columns.get(i);
            List<Character> mirrorList = symbol.columns.get(i + 1);

            if(currList.equals(mirrorList)) {
                int k = 1;
                boolean ko = false;
                while (i - k >= 0 && i + k < symbol.columns.size() - 1) {
                    if(!symbol.columns.get(i - k).equals(symbol.columns.get(i + k + 1))) {
                        ko = true;
                        break;
                    }
                    ++k;
                }
                //                System.out.println("-------------VERTICAL-----------------");
                //                System.out.println(currList);
                //                System.out.println(mirrorList);
                if(!ko) {
                    return (i + 1);
                }
            }
        }
        return 0;
    }

    static void parse(String input) {
        Stream<String> inputLines = input.lines();
        List<String> inputList = inputLines.toList();

        List<List<Character>> lines = new ArrayList<>();
        List<List<Character>> columns = new ArrayList<>();
        for (String line : inputList) {

            if(line.isEmpty()) {
                for (int i = 0; i < lines.getFirst().size(); i++) {
                    ArrayList<Character> currCol = new ArrayList<>();
                    columns.add(currCol);
                    for (List<Character> lineForCol : lines) {
                        currCol.add(lineForCol.get(i));
                    }
                }
                Symbol symbol = new Symbol(lines, columns);
                symbols.add(symbol);
                lines = new ArrayList<>();
                columns = new ArrayList<>();
                continue;
            }

            char[] charArray = line.toCharArray();
            ArrayList<Character> currList = new ArrayList<>();
            lines.add(currList);
            for (char c : charArray) {
                currList.add(c);
            }
        }

        for (int i = 0; i < lines.getFirst().size(); i++) {
            ArrayList<Character> currCol = new ArrayList<>();
            columns.add(currCol);
            for (List<Character> lineForCol : lines) {
                currCol.add(lineForCol.get(i));
            }
        }

        Symbol symbol = new Symbol(lines, columns);
        symbols.add(symbol);

    }

    public record Symbol(List<List<Character>> lines, List<List<Character>> columns) {

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("");
            for (List<Character> line : lines) {
                for (Character c : line) {
                    sb.append(c);
                }
                sb.append("\n");
            }
            return sb.toString();
        }
    }
}
