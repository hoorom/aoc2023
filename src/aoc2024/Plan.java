package aoc2024;

import aoc2023.Day11;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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

    public Plan(Plan other) {
        this.rows = new ArrayList<>(other.rows);
        this.cols = new ArrayList<>();
        for (char[] col : other.cols) {
            this.cols.add(Arrays.copyOf(col, col.length));
        }
        if (other.position != null) {
            this.position = new Position(other.position.x, other.position.y, other.position.direction);
        }
    }

    public int dijkstra(Day11.Coordinates from, Day11.Coordinates to, char c) {
        Dijkstra dijkstra = new Dijkstra();
        ArrayList<Position> path = new ArrayList<>();
        path.add(new Position(from.x(), from.y(), null));
        DijkstraPath dijkstraPath = new DijkstraPath();
        dijkstraPath.setPath(path);
        dijkstra.addNewPathIfLighter(dijkstraPath);

        while (true) {
            Integer result = runDijkstra(dijkstra, new Position(to.x(), to.y(), null), c);
            if(result != null) {
                return result;
            }
        }
    }

    public int cheatingDijkstra(Day11.Coordinates from, Day11.Coordinates to, List<Day11.Coordinates> cheatingPoints, Dijkstra originaleDijkstra, char c) {
        Dijkstra dijkstra = new Dijkstra();
        ArrayList<Position> path = new ArrayList<>();
        path.add(new Position(from.x(), from.y(), null));
        DijkstraPath dijkstraPath = new DijkstraPath();
        dijkstraPath.setPath(path);
        dijkstra.addNewPathIfLighter(dijkstraPath);

        while (true) {
            Integer result = runCheatingDijkstra(dijkstra, new Position(to.x(), to.y(), null), cheatingPoints, originaleDijkstra, c);
            if(result != null) {
                return result;
            }
        }
    }

    public record DijkstraResult(DijkstraPath dijkstraPath, int result) {}

    public DijkstraResult dijkstraWithResult(Day11.Coordinates from, Day11.Coordinates to, char c) {
        Dijkstra dijkstra = new Dijkstra();
        List<Position> path = new ArrayList<>();
        path.add(new Position(from.x(), from.y(), null));
        DijkstraPath dijkstraPath = new DijkstraPath();
        dijkstraPath.setPath(path);
        dijkstra.addNewPathIfLighter(dijkstraPath);

        while (true) {
            DijkstraPath winningPath = runDijkstraForPath(dijkstra, new Position(to.x(), to.y(), null), c);
            if(winningPath != null) {
                Integer result = winningPath.getCurrentWeight();
                return new DijkstraResult(winningPath, result);
            }
        }
    }

    private Integer runCheatingDijkstra(Dijkstra dijkstra, Position target, List<Day11.Coordinates> cheatingCoord, Dijkstra originaleDijkstra, char c) {
        Optional<DijkstraPath> opt = dijkstra.getAllPaths().stream().filter(dp -> !dp.isTreated()).min(Comparator.comparingInt(DijkstraPath::getCurrentWeight));
        if(!opt.isPresent()) {
            throw new IllegalArgumentException("Dijkstra path not found");
        }
        DijkstraPath lightest = opt.get();

        Position currentPosition = lightest.getCurrentPosition();
        lightest.setTreated(true);

        List<Position> coordinates = moveAll(currentPosition);
        for (Position nextCoordinates : coordinates) {
            if(getCharAtPosition(nextCoordinates) == c) {
                continue;
            }

            DijkstraPath dijkstraPath = new DijkstraPath(lightest);
            dijkstraPath.getPath().add(nextCoordinates);
            if(nextCoordinates.x == target.x && nextCoordinates.y == target.y) {
                return dijkstraPath.getCurrentWeight();
            }

            if (cheatingCoord.contains(currentPosition.coordinates()) && !cheatingCoord.contains(nextCoordinates.coordinates())) {}
            dijkstra.addNewPathIfLighter(dijkstraPath);
        }
        return null;
    }

    private Integer runDijkstra(Dijkstra dijkstra, Position target, char c) {
        Optional<DijkstraPath> opt = dijkstra.getAllPaths().stream().filter(dp -> !dp.isTreated()).min(Comparator.comparingInt(DijkstraPath::getCurrentWeight));
        if(!opt.isPresent()) {
            throw new IllegalArgumentException("Dijkstra path not found");
        }
        DijkstraPath lightest = opt.get();

        Position currentPosition = lightest.getCurrentPosition();
        lightest.setTreated(true);

        List<Position> coordinates = moveAll(currentPosition);
        for (Position nextPosition : coordinates) {
            if(getCharAtPosition(nextPosition) == c) {
                continue;
            }

            DijkstraPath dijkstraPath = new DijkstraPath(lightest);
            dijkstraPath.getPath().add(nextPosition);
            if(nextPosition.x == target.x && nextPosition.y == target.y) {
                return dijkstraPath.getCurrentWeight();
            }
            dijkstra.addNewPathIfLighter(dijkstraPath);
        }
        return null;
    }

    private DijkstraPath runDijkstraForPath(Dijkstra dijkstra, Position target, char c) {
        //        Stream<DijkstraPath> dijkstraPathStream = dijkstra.getAllPaths().stream().filter(dp -> !dp.isTreated());
        //        System.out.println(dijkstraPathStream.count() + " still alive");
        Optional<DijkstraPath> opt = dijkstra.getAllPaths().stream().filter(dp -> !dp.isTreated()).min(Comparator.comparingInt(DijkstraPath::getCurrentWeight));
        if(!opt.isPresent()) {
            throw new IllegalArgumentException("Dijkstra path not found");
        }
        DijkstraPath lightest = opt.get();

        //        System.out.println("Currently on : " + lightest);
        Position currentPosition = lightest.getCurrentPosition();
        lightest.setTreated(true);

        List<Position> coordinates = moveAll(currentPosition);
        for (Position nextPosition : coordinates) {
            if(getCharAtPosition(nextPosition) == c) {
                continue;
            }

            DijkstraPath dijkstraPath = new DijkstraPath(lightest);
            dijkstraPath.getPath().add(nextPosition);
            if(nextPosition.x == target.x && nextPosition.y == target.y) {
                return dijkstraPath;
            }
            dijkstra.addNewPathIfLighter(dijkstraPath);
        }
        return null;
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
        Direction direction = position.direction;
        if(direction == null) {
            direction = Direction.NORTH;
        }
        List<Direction> possibleDirections = Direction.mapToFollow.get(direction);
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

    public List<Position> getAllPosition() {
        List<Position> positions = new ArrayList<>();
        for (int i = 0; i < cols.size(); i++) {
            char[] row = cols.get(i);
            for (int j = 0; j < row.length; j++) {
                    positions.add(new Position(i, j, Direction.NORTH));
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

    public char[] getSurroundingChars(Position position) {
        char[] surroundingChars = new char[8];
        try {
            surroundingChars[0] = cols.get(position.x - 1)[position.y];
        } catch (Exception e) {}
        try {
            surroundingChars[1] = cols.get(position.x)[position.y - 1];
        } catch (Exception e) {}

        try {
            surroundingChars[2] = cols.get(position.x + 1)[position.y];
        } catch (Exception e) {}
        try {
            surroundingChars[3] = cols.get(position.x)[position.y + 1];
        } catch (Exception e) {
        }
        try {
            surroundingChars[4] = cols.get(position.x - 1)[position.y - 1];
        } catch (Exception e) {
        }
        try {
            surroundingChars[5] = cols.get(position.x + 1)[position.y - 1];
        } catch (Exception e) {
        }
        try {
            surroundingChars[6] = cols.get(position.x - 1)[position.y + 1];
        } catch (Exception e) {
        }
        try {
            surroundingChars[7] = cols.get(position.x + 1)[position.y + 1];
        } catch (Exception e) {
        }
        return surroundingChars;



    }
}
