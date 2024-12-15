package aoc2024.day14;

public class Robot {

//    final int testColSize = 11;
//    final int testRowSize = 7;

    final int testColSize = 101;
    final int testRowSize = 103;

    private final int speedX;
    private final int speedY;
    int x;
    int y;

    public Robot(int x, int y, int speedX, int speedY) {
        this.speedX = speedX;
        this.speedY = speedY;
        this.x = x;
        this.y = y;
    }

    public void move() {
        x = (x + speedX) % testColSize;
        if(x < 0) {
            x += testColSize;
        }
        y = (y + speedY) % testRowSize;
        if (y < 0) {
            y += testRowSize;
        }
    }

    @Override
    public String toString() {
        return "Robot{" + "x=" + x + ", y=" + y + '}';
    }
}
