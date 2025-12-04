package aoc2025.day4;

import aoc2024.Plan;
import java.util.List;

public class Resolver {

    void main() {
        String input = Input.input;

        Plan plan = new Plan(input);
        while (true) {
            int oldX = plan.getAllPositionForChar('x').size();
            execPlan(plan);
            int newX = plan.getAllPositionForChar('x').size();
            if(oldX == newX) {
                break;
            }
        }
        System.out.println(plan.getAllPositionForChar('x').size());
    }

    private static int execPlan(Plan plan) {
        List<Plan.Position> allPosition = plan.getAllPosition();

        int nbAcc = 0;
        for (Plan.Position position : allPosition) {
            if(plan.getCharAtPosition(position) != '@') {
                continue;
            }
            char[] surroundingChars = plan.getSurroundingChars(position);
            int nbSurrounding = 0;
            for (char surroundingChar : surroundingChars) {
                if(surroundingChar == '@') {
                    nbSurrounding++;
                }
            }
            if(nbSurrounding < 4) {
                ++nbAcc;
                plan.putCharAtCoord(position.coordinates(), 'x');
            }
        }
        return nbAcc;
    }
}
