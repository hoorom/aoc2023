package aoc2024.day15;

import aoc2024.Direction;
import aoc2024.Plan;
import java.util.List;

public class Resolver {

    private static Plan plan;

    public static void main(String[] args) {
        plan = new Plan(Input.testLarge, '@', true);

        for (char move : Input.testMove.toCharArray()) {

            System.out.println("Moving : " + move);

            Direction nextDir = switch (move) {
                case '<': yield Direction.WEST;
                case '>': yield Direction.EAST;
                case '^': yield Direction.NORTH;
                case 'v': yield Direction.SOUTH;
                default:
                    yield null;
//                    throw new IllegalStateException("Unexpected value: **" + move+"**");
            };
            if (nextDir == null) {
                continue;
            }
            plan.position = new Plan.Position(plan.position.x(), plan.position.y(), nextDir);

            Plan.Position position = plan.nextPos(plan.position);
            char charAtPosition = plan.getCharAtPosition(position);
            if(charAtPosition == '#') {
                continue;
            } else if(charAtPosition == '.') {
                plan.moveWithSwap(plan.position);
            } else if (charAtPosition == 'O') {
                tryMoveBox(position);
                if(plan.getCharAtPosition(position) == '.') {
                    plan.moveWithSwap(plan.position);
                }
            }

            System.out.println(plan);
        }

        List<Plan.Position> o = plan.getAllPositionForChar('O');
        long sum = 0;
        for (Plan.Position position : o) {
            sum += position.x() * 100;
            sum += position.y();
        }
        System.out.println(sum);
    }

    private static void tryMoveBox(Plan.Position currBoxPos) {
        Plan.Position position = plan.nextPos(currBoxPos);
        char nextChar = plan.getCharAtPosition(position);
        if(nextChar == 'O') {
            //recur
            tryMoveBox(position);
            if(plan.getCharAtPosition(position) == '.') {
                plan.swapCharPosition(currBoxPos, position);
            }
        } else if (nextChar == '#') {
            return;
        } else if (nextChar == '.') {
            plan.swapCharPosition(currBoxPos, position);
        }
    }
}
