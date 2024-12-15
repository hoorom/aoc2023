package aoc2024;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Direction {

    NORTH(-1, 0), EAST(0, 1), SOUTH(1, 0), WEST(0, -1), NORTH_EAST(-1, 1), SOUTH_EAST(1, 1), NORTH_WEST(-1, -1), SOUTH_WEST(1, -1);

    public final int x;
    public final int y;

    public static final Map<Direction, List<Direction>> mapToFollow;

    static {
        mapToFollow = new HashMap<>();
        mapToFollow.put(NORTH, List.of(WEST, NORTH, EAST, SOUTH));
        mapToFollow.put(EAST, List.of(NORTH, EAST, SOUTH, WEST));
        mapToFollow.put(SOUTH, List.of(EAST, SOUTH, WEST, NORTH));
        mapToFollow.put(WEST, List.of(SOUTH, WEST, NORTH, EAST));
    }

    public boolean isOpposite(Direction direction) {
        if(getTurn90Right(getTurn90Right(direction)).equals(this)) {
            return true;
        }
        return false;
    }

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Direction getTurn90Right(Direction direction) {
        return switch (direction) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
            case NORTH_EAST -> SOUTH_EAST;
            case SOUTH_EAST -> SOUTH_WEST;
            case NORTH_WEST -> NORTH_EAST;
            case SOUTH_WEST -> NORTH_WEST;
        };
    }
}
