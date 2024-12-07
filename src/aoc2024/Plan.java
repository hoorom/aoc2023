package aoc2024;

import java.util.ArrayList;
import java.util.List;

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

    public record Position(int x, int y, Direction direction) {}

    public Position move(int x, int y, Direction direction) {
        return new Position(x + direction.x, y + direction.y, direction);
    }

    public Position nextPos(Position pos) {
        Position newPos = new Position(pos.x + pos.direction.x, pos.y + pos.direction.y, pos.direction);
        if(newPos.x < 0 || newPos.x >= rows.size() || newPos.y < 0 || newPos.y >= cols.size()) {
            return null;
        }
        return newPos;
    }

    public void move(Position pos) {
        this.position = nextPos(pos);
    }

    public char getCharAtPosition(Position position) {
        return cols.get(position.x)[position.y];
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
}
