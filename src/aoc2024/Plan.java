package aoc2024;

import aoc2023.Day11;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Plan {

    public List<String> rows;

    public List<char[]> cols = new ArrayList<>();

    public Position position;

    public Plan(String plan, Position position) {
        this.position = position;
        rows = plan.lines().toList();
        rows.forEach(row -> {
            char[] charArray = row.toCharArray();
            cols.add(charArray);
        });
    }

    public Plan(String plan) {
        rows = plan.lines().toList();
        rows.forEach(row -> {
            char[] charArray = row.toCharArray();
            cols.add(charArray);
        });
    }

    public Plan(int rows, int cols) {
        this.rows = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < cols; j++) {
                sb.append('.');
            }
            this.rows.add(sb.toString());
        }
        this.cols = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            char[] e = new char[cols];
            Arrays.fill(e, '.');
            this.cols.add(e);
        }
    }


    public Plan(String plan, char depChar) {
        this(plan, depChar, false);
    }

    public Plan(String plan, char depChar, boolean doubleSize) {
        rows = plan.lines().toList();
        rows.forEach(row -> {
            char[] charArray = row.toCharArray();
            if(doubleSize) {
                List<Character> toPut = new ArrayList<>();
                for (char c : charArray) {

                    switch (c) {
                        case '#' -> {
                            toPut.add('#');
                            toPut.add('#');
                        }
                        case 'O' -> {
                            toPut.add('[');
                            toPut.add(']');
                        }
                        case '.' -> {
                            toPut.add('.');
                            toPut.add('.');
                        }
                        case '@' -> {
                            toPut.add('@');
                            toPut.add('.');
                        }

                    }
                }
                char[] array = new char[toPut.size()];

                for (int i = 0; i < toPut.size(); i++) {
                    array[i] = toPut.get(i);
                }
                cols.add(array);
            } else {
                cols.add(charArray);
            }
        });
        position = getPositionForChar(depChar);
    }

    public void dijkstra(Day11.Coordinates from, Day11.Coordinates to, char c) {
        Map<Day11.Coordinates, Integer> distances = new HashMap<>();
        Map<Day11.Coordinates, Day11.Coordinates> prev = new HashMap<>();
        Map<Day11.Coordinates, Boolean> visited = new HashMap<>();
        distances.put(from, 0);
        prev.put(from, null);
        visited.put(from, true);
        while(!distances.isEmpty()) {
            Day11.Coordinates current = null;
            int minDistance = Integer.MAX_VALUE;
            for (Day11.Coordinates coord : distances.keySet()) {
                if(distances.get(coord) < minDistance) {
                    current = coord;
                }
            }
        }


    }

    public record Position(int x, int y, Direction direction) {
        public Day11.Coordinates coordinates() {
            return new Day11.Coordinates(this.x, this.y);
        }
    }

    public Position move(int x, int y, Direction direction) {
        return new Position(x + direction.x, y + direction.y, direction);
    }

    public Position nextPos(Position pos) {
        Position newPos = new Position(pos.x + pos.direction.x, pos.y + pos.direction.y, pos.direction);
        if(newPos.x < 0 || newPos.x >= rows.size() || newPos.y < 0 || newPos.y >= cols.get(0).length) {
            return null;
        }
        return newPos;
    }

    public void move(Position pos) {
        Position nextPos = nextPos(pos);
        this.position = nextPos;
    }

    public void moveWithSwap(Position pos) {
        Position nextPos = nextPos(pos);
        swapCharPosition(pos, nextPos);
        this.position = nextPos;
    }

    public List<Day11.Coordinates> moveAll(Day11.Coordinates coord) {
        List<Day11.Coordinates> possiblePositions = new ArrayList<>();
        this.position = new Position(coord.x(), coord.y(), Direction.EAST);
        Position possible = nextPos(position);
        if(possible != null) {
            possiblePositions.add(new Day11.Coordinates(possible.x(), possible.y()));
        }
        this.position = new Position(coord.x(), coord.y(), Direction.SOUTH);
        possible = nextPos(position);
        if(possible != null) {
            possiblePositions.add(new Day11.Coordinates(possible.x(), possible.y()));
        }
        this.position = new Position(coord.x(), coord.y(), Direction.WEST);
        possible = nextPos(position);
        if(possible != null) {
            possiblePositions.add(new Day11.Coordinates(possible.x(), possible.y()));
        }
        this.position = new Position(coord.x(), coord.y(), Direction.NORTH);
        possible = nextPos(position);
        if(possible != null) {
            possiblePositions.add(new Day11.Coordinates(possible.x(), possible.y()));
        }
        return possiblePositions;
    }

    public List<Position> moveAll(Position position) {
        List<Position> possiblePositions = new ArrayList<>();
        List<Direction> possibleDirections = Direction.mapToFollow.get(position.direction);
        for (Direction possibleDirection : possibleDirections) {
            Position possible = nextPos(new Position(position.x(), position.y(), possibleDirection));
            if(possible != null) {
                possiblePositions.add(new Position(possible.x(), possible.y(), possibleDirection));
            }
        }

        //        possible = nextPos(new Position(position.x(), position.y(), Direction.SOUTH));
        //        if(possible != null) {
        //            possiblePositions.add(new Position(possible.x(), possible.y(), Direction.SOUTH));
        //        }
        //
        //        possible = nextPos(new Position(position.x(), position.y(), Direction.WEST));
        //        if(possible != null) {
        //            possiblePositions.add(new Position(possible.x(), possible.y(), Direction.WEST));
        //        }
        //
        //        possible = nextPos(new Position(position.x(), position.y(), Direction.NORTH));
        //        if(possible != null) {
        //            possiblePositions.add(new Position(possible.x(), possible.y(), Direction.NORTH));
        //        }

        return possiblePositions;
    }

    public char getCharAtPosition(Position position) {
        return cols.get(position.x)[position.y];
    }

    public char getCharAtPosition(Day11.Coordinates coordinates) {
        return cols.get(coordinates.x())[coordinates.y()];
    }

    public Position getPositionForChar(char ch) {
        for (int i = 0; i < cols.size(); i++) {
            char[] row = cols.get(i);
            for (int j = 0; j < row.length; j++) {
                if (row[j] == ch) {
                    return new Position(i, j, Direction.NORTH);
                }
            }
        }
        return null;
    }

    public List<Position> getAllPositionForChar(char ch) {
        List<Position> positions = new ArrayList<>();
        for (int i = 0; i < cols.size(); i++) {
            char[] row = cols.get(i);
            for (int j = 0; j < row.length; j++) {
                if (row[j] == ch) {
                    positions.add(new Position(i, j, Direction.NORTH));
                }
            }
        }
        return positions;
    }

    public void swapCharPosition(Position pos1, Position pos2) {
        char char1 = getCharAtPosition(pos1);
        char char2 = getCharAtPosition(pos2);

        cols.get(pos1.x())[pos1.y()] = char2;
        cols.get(pos2.x())[pos2.y()] = char1;
    }

    public void putCharAtCoord(Day11.Coordinates coord, char val) {
        cols.get(coord.x())[coord.y()] = val;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char[] row : cols) {
            for (char col : row) {
                sb.append(col).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();


    }
}
