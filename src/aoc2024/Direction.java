package aoc2024;

public enum Direction {

    NORTH(-1, 0), EAST(0, 1), SOUTH(1, 0), WEST(0, -1), NORTH_EAST(-1, 1), SOUTH_EAST(1, 1), NORTH_WEST(-1, -1), SOUTH_WEST(1, -1);

    public final int x;
    public final int y;

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
