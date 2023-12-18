package graph;

public enum Direction {

    RIGHT {
        @Override
        public Direction getOpposite() {
            return LEFT;
        }

        public void move(Position position) {
            position.setY(position.getY() + 1);
        }
    },
    LEFT {
        public void move(Position position) {
            position.setY(position.getY() - 1);
        }

        @Override
        public Direction getOpposite() {
            return RIGHT;
        }
    },
    TOP {
        public void move(Position position) {
            position.setX(position.getX() - 1);
        }

        @Override
        public Direction getOpposite() {
            return BOT;
        }
    },
    BOT {
        public void move(Position position) {
            position.setX(position.getX() + 1);
        }

        @Override
        public Direction getOpposite() {
            return TOP;
        }
    };

    public abstract void move(Position position);

    public abstract Direction getOpposite();
}
