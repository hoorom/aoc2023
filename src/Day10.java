import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import javax.swing.text.Position;

public class Day10 {

    public static List<List<Character>> map = new ArrayList<>();

    public static AtomicInteger depth = new AtomicInteger(0);

    public static String test0 = """
            .....
            .S-7.
            .|.|.
            .L-J.
            .....""";

    public static String test = """
            7-F7-
            .FJ|7
            SJLL7
            |F--J
            LJ.LJ""";

    public static void main(String[] args) {
        String input = Day10Input.input;
//        String input = test;
//        String input = test0;
        initMap(input);
        Position initialPosition = findInitialPosition(input);
        Position previousPosition = null;
        Position transitionnal;
        Character c = '0';
        Character charAtPos = getCharAtPos(initialPosition);
        while (c != 'S') {
            transitionnal = getNextPosition(initialPosition, previousPosition);
            c = getCharAtPos(transitionnal);
            previousPosition = initialPosition;
            initialPosition = transitionnal;
        }
        System.out.println(depth);
        System.out.println("Result : " +  (depth.get() >> 1));
        //13539
    }

    private static Position getNextPosition(Position initialPosition, Position previousPosition) {
        depth.incrementAndGet();
        Character charAtCurrPos = getCharAtPos(initialPosition);
//        System.out.println(charAtCurrPos+" : " + initialPosition + " / " + depth);
        Position nextPosition = computeNextPosition(initialPosition, previousPosition);
        return nextPosition;
    }

    public static void initMap(String input) {
        Stream<String> lines = input.lines();
        List<String> inputLines = lines.toList();
        for (String inputLine : inputLines) {
            char[] charArray = inputLine.toCharArray();
            ArrayList<Character> terrain = new ArrayList<>();
            map.add(terrain);
            for (char c : charArray) {
                terrain.add(c);
            }
        }
    }

    public static Position findInitialPosition(String input) {
        int pos = input.indexOf("S");
        int size = map.getFirst().size();
        int line = pos / (size + 1);
        int column = pos % (size + 1);
        Position position = new Position(line, column);
        Character charAtPos = getCharAtPos(position);
        assert charAtPos == 'S';
        return position;
    }

    public static Position computeNextPosition(Position currentPosition, Position previousPosition) {
        Character charAtPos = getCharAtPos(currentPosition);
        Tuyau tuyau = Tuyau.fromChar(charAtPos);
        assert tuyau != null;
        List<Position> possiblePositions = Tuyau.getPossiblePositions(tuyau);

        assert possiblePositions != null;
        List<Position> modifiableList = new ArrayList<>(possiblePositions);
        if (previousPosition != null && !modifiableList.isEmpty()) {
            modifiableList.remove(previousPosition.diff(currentPosition));
        }

        assert modifiableList.size() == 1;

        Position nextPosition = currentPosition.merge(modifiableList.getFirst());
        return nextPosition;
    }

    public static Character getCharAtPos(Position p) {
        return map.get(p.line).get(p.column);
    }

    public record Position(int line, int column){

        public Position merge(Position relativePosition) {
            return new Position((line + relativePosition.line), column + relativePosition.column);
        }

        public Position diff(Position relativePosition) {
            return new Position((line - relativePosition.line), column - relativePosition.column);
        }

    }

}
