package aoc2024.day15;

import aoc2024.Direction;
import aoc2024.Plan;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ResolverP2 {

    private static Plan plan;
    static List<AbstractMap.SimpleEntry<Plan.Position, Plan.Position>> swapList;
    static Set<Plan.Position> alreadyTreated;

    public static void main(String[] args) {
        plan = new Plan(Input.inputEnt, '@', true);

        for (char move : Input.inputMoves.toCharArray()) {

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
            } else if (charAtPosition == '[' || charAtPosition == ']') {
                swapList = new ArrayList<>();
                alreadyTreated = new HashSet<>();

                try {

                    if(charAtPosition == '[' || charAtPosition == ']') {
                        //recur
                        tryMoveBox(position);
                        if(charAtPosition == '[') {
                            if(position.direction().equals(Direction.NORTH) || position.direction().equals(Direction.SOUTH)) {
                                tryMoveBox(new Plan.Position(position.x(), position.y() + 1, position.direction()));
                            }
                        } else {
                            if(position.direction().equals(Direction.NORTH) || position.direction().equals(Direction.SOUTH)) {
                                tryMoveBox(new Plan.Position(position.x() , position.y()- 1, position.direction()));
                            }
                        }
                    }

                    for (AbstractMap.SimpleEntry<Plan.Position, Plan.Position> positionPositionSimpleEntry : swapList) {
                        plan.swapCharPosition(positionPositionSimpleEntry.getKey(), positionPositionSimpleEntry.getValue());
                    }
                } catch (Exception e) {
//                    throw new RuntimeException(e);
                }
                if(plan.getCharAtPosition(position) == '.') {
                    plan.moveWithSwap(plan.position);
                }
            }

            System.out.println(plan);
        }

        List<Plan.Position> o = plan.getAllPositionForChar('[');
        long sum = 0;
        for (Plan.Position position : o) {
            sum += position.x() * 100;
            sum += position.y();
        }
        System.out.println(sum);
    }

    private static void tryMoveBox(Plan.Position currBoxPos) {
        if(!alreadyTreated.add(currBoxPos)) {
            return;
        }
            Plan.Position position = plan.nextPos(currBoxPos);
            char nextChar = plan.getCharAtPosition(position);
            if(nextChar == '[' || nextChar == ']') {
                //recur
                if(nextChar == '[') {
                    tryMoveBox(position);
                    if(position.direction().equals(Direction.NORTH) || position.direction().equals(Direction.SOUTH)) {
                        tryMoveBox(new Plan.Position(position.x(), position.y() + 1, position.direction()));
                    }
                } else {
                    tryMoveBox(position);
                    if(position.direction().equals(Direction.NORTH) || position.direction().equals(Direction.SOUTH)) {
                        tryMoveBox(new Plan.Position(position.x() , position.y()- 1, position.direction()));
                    }
                }
                if(swapList.stream().filter(s -> s.getKey().equals(position)).findFirst().isPresent()) {
                    swapList.add(new AbstractMap.SimpleEntry<>(currBoxPos, position));
                }
            } else if (nextChar == '#') {
                throw new RuntimeException();
            } else if (nextChar == '.') {
                swapList.add(new AbstractMap.SimpleEntry<>(currBoxPos, position));
            }
    }
}
