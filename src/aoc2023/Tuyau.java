package aoc2023;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Tuyau {

    PIPE('|'),
    MINUS('-'),
    L('L'),
    J('J'),
    SEVEN('7'),
    F('F'),
    DOT('.'),
    FINAL('S');

    public Character car;

    public static Tuyau fromChar(Character c) {
        if(c == '|') {
            return PIPE;
        } else if(c == '-') {
            return MINUS;
        } else if(c == 'L') {
            return L;
        } else if(c == 'J') {
            return J;
        } else if(c == '7') {
            return SEVEN;
        } else if(c == 'F') {
            return F;
        } else if(c == '.') {
            return DOT;
        } else if(c == 'S') {
            return FINAL;
        }
        throw new IllegalArgumentException();
    }

    Tuyau(Character car) {
        this.car = car;
    }

    public static List<Day10.Position> getPossiblePositions(Tuyau t) {
        switch (t) {
            case PIPE -> {
                return Arrays.asList(new Day10.Position(-1, 0), new Day10.Position(+1, 0));
            }
            case MINUS -> {
                return Arrays.asList(new Day10.Position(0, -1), new Day10.Position(0, +1));
            }
            case L -> {
                return Arrays.asList(new Day10.Position(-1, 0), new Day10.Position(0, +1));
            }
            case J -> {
                return Arrays.asList(new Day10.Position(-1, 0), new Day10.Position(0, -1));
            }
            case SEVEN -> {
                return Arrays.asList(new Day10.Position(+1, 0), new Day10.Position(0, -1));
            }
            case F -> {
                return Arrays.asList(new Day10.Position(+1, 0), new Day10.Position(0, +1));
            }
            case DOT -> {
                return Collections.emptyList();
            }
            case FINAL -> {
                return Arrays.asList(
//                new Day10.Position(-1, 0),
//                        new Day10.Position(0, -1),
                        new Day10.Position(0, 1)
//                        new Day10.Position(-1, 0)
                );
            }
        }
        return null;
    }
}
