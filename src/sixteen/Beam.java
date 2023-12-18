package sixteen;

import graph.Direction;
import graph.Position;
import java.util.HashSet;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public class Beam {

    int id;

    Position position;

    Direction direction;

    static Set<Position> energizedTile = new HashSet<>();

    static Set<EnergyType> energizedTypeTile = new HashSet<>();

    static int idToGive = 0;

    static Set<Beam> beamToProcess = new HashSet<>();

    public static void reset() {
        energizedTile.clear();
        energizedTypeTile.clear();
        beamToProcess.clear();
        idToGive = 0;
    }

    public void move(char[][] field) {
        direction.move(position);


        if(position.getY() >= field[0].length) {
//            System.out.println(id + " DEAD");
            return;
        } else if (position.getX() >= field.length) {
//            System.out.println(id + " DEAD");
            return;
        } else if (position.getY() < 0) {
//            System.out.println(id + " DEAD");
            return;
        } else if (position.getX() < 0) {
//            System.out.println(id + " DEAD");
            return;
        }

        if(energizedTypeTile.contains(new EnergyType(position, direction))) {
//            System.out.println(id + " DEAD WITH LOOP");
            return;
        }
        energizeTile(position, direction);

        char charAt = getCharAt(field, position);
//        System.out.println(id + " at : " + " ( " + direction + " ) " + position.getX() + " / " + position.getY() + " => " + charAt);
        if(Direction.BOT.equals(direction)) {
            if (charAt == '/') {
                direction = Direction.LEFT;
                move(field);
            } else if(charAt == '\\') {
                direction = Direction.RIGHT;
                move(field);
            } else if (charAt == '-') {
                Beam beam = new Beam(new Position(position.getX(), position.getY()), Direction.LEFT);
                beamToProcess.add(beam);
                direction = Direction.RIGHT;
                move(field);
            } else {
                move(field);
            }
        } else if(Direction.LEFT.equals(direction)) {
            if (charAt == '/') {
                direction = Direction.BOT;
                move(field);
            } else if(charAt == '\\') {
                direction = Direction.TOP;
                move(field);
            } else if (charAt == '|') {
                Beam beam = new Beam(new Position(position.getX(), position.getY()), Direction.TOP);
                beamToProcess.add(beam);
                direction = Direction.BOT;
                move(field);
            } else {
                move(field);
            }
        } else if(Direction.RIGHT.equals(direction)) {
            if (charAt == '/') {
                direction = Direction.TOP;
                move(field);
            } else if(charAt == '\\') {
                direction = Direction.BOT;
                move(field);
            } else if (charAt == '|') {
                Beam beam = new Beam(new Position(position.getX(), position.getY()), Direction.TOP);
                beamToProcess.add(beam);
                direction = Direction.BOT;
                move(field);
            } else {
                move(field);
            }
        } else if(Direction.TOP.equals(direction)) {
            if (charAt == '/') {
                direction = Direction.RIGHT;
                move(field);
            } else if(charAt == '\\') {
                direction = Direction.LEFT;
                move(field);
            } else if (charAt == '-') {
                Beam beam = new Beam(new Position(position.getX(), position.getY()), Direction.LEFT);
                beamToProcess.add(beam);
                direction = Direction.RIGHT;
                move(field);
            } else {
                move(field);
            }
        }
    }

    char getCharAt(char[][] field, Position position) {
        return field[position.getX()][position.getY()];
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public static synchronized void energizeTile(Position position, Direction direction) {
        energizedTile.add(new Position(position.getX(), position.getY()));
        energizedTypeTile.add(new EnergyType(position, direction));
    }

    public static Set<Position> getEnergizedTile() {
        return energizedTile;
    }


    public Beam(int x, int y, Direction direction, char[][] field) {
        this.position = new Position(x, y);
        char charAt = getCharAt(field, position);
        if(charAt == '|' || charAt == '\\') {
            this.direction = Direction.BOT;
        } else {
            this.direction = direction;
        }
        this.id = getNextIdToGive();
        energizeTile(position, direction);
        beamToProcess.add(this);
    }

    public Beam(Position position, Direction direction) {
        this.position = position;
        this.direction = direction;
        this.id = getNextIdToGive();
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    static synchronized int getNextIdToGive() {
        return  ++idToGive;
    }

    public static String  createCharArray(int x, int y, Set<Position> energizedTiles) {
        char[][] array = new char[x][y];

        // Initialize with default value
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                array[i][j] = '.'; // Default value
            }
        }

        // Set specified positions to '#'
        for (Position position : energizedTiles) {
            int posX = position.getX();
            int posY = position.getY();

            // Verifying if position is in bounds of created array
            if (posX >= 0 && posX < x && posY >= 0 && posY < y) {
                array[posX][posY] = '#';
            } else {
                System.out.println("Energized tile at position (" + posX + ", " + posY + ") is out of array bounds, skipping...");
            }
        }
        StringBuilder sb = fieldToString(array);
        return sb.toString();
    }

    @NotNull
    public static StringBuilder fieldToString(char[][] array) {
        StringBuilder sb = new StringBuilder();
        for(char[] row : array){
            for(char c : row){
                sb.append(c);
            }
            sb.append('\n');
        }
        return sb;
    }
}
