package aoc2023.seventeen;

import aoc2023.graph.Direction;
import aoc2023.graph.Position;
import java.util.Objects;

public class Node {

    private Direction fromDirection;

    private Position position;

    private int weight;

    public Position getPosition() {
        return position;
    }

    public Node(Position position, Direction fromDirection) {
        this.position = position;
        this.fromDirection = fromDirection;
        weight = Character.getNumericValue(Graph.getCharAt(position));
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Direction getFromDirection() {
        return fromDirection;
    }

    public void setFromDirection(Direction fromDirection) {
        this.fromDirection = fromDirection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Node node)) {
            return false;
        }
        return weight == node.weight && Objects.equals(position, node.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, weight);
    }
}
