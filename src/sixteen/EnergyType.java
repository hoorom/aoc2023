package sixteen;

import graph.Direction;
import graph.Position;
import java.util.Objects;

public class EnergyType {

    Position position;

    Direction direction;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public EnergyType(Position position, Direction direction) {
        this.position = new Position(position.getX(), position.getY());
        switch (direction) {
            case RIGHT -> this.direction = Direction.RIGHT;
            case LEFT -> this.direction = Direction.LEFT;
            case TOP -> this.direction = Direction.TOP;
            case BOT -> this.direction = Direction.BOT;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EnergyType that)) {
            return false;
        }
        return Objects.equals(position, that.position) && direction == that.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, direction);
    }
}
