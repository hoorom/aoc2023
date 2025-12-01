package aoc2025.day1;

import java.util.List;
import java.util.stream.Stream;

public class Resolver {

    int startingPos = 50;
    int modulo = 100;

    void main() {
        part2();
    }

    private void part1() {
        String input = Input.input;
        Stream<String> lines = input.lines();
        List<String> list = lines.toList();
        int currPos = startingPos;
        int key = 0;
        for (String instruction : list) {
            int nbMove = Integer.parseInt(instruction.substring(1));
            if(instruction.startsWith("L")) {
                currPos =  (currPos - nbMove + modulo) % modulo;
            } else {
                currPos = (currPos + nbMove + modulo) % modulo;
            }
            if(currPos == 0) {
                ++key;
            }
        }
        System.out.println(key);
    }

    private void part2() {
        String input = Input.input;
        Stream<String> lines = input.lines();
        List<String> list = lines.toList();
        int currPos = startingPos;
        int key = 0;
        for (String instruction : list) {
            int nbMove = Integer.parseInt(instruction.substring(1));

            int zeroHits;
            if (instruction.startsWith("R")) {
                zeroHits = (currPos + nbMove) / modulo;
                currPos = (currPos + nbMove) % modulo;
            } else {
                if (currPos == 0) {
                    zeroHits = nbMove / modulo;
                } else {
                    zeroHits = nbMove >= currPos ? 1 + (nbMove - currPos) / modulo : 0;
                }
                currPos = (currPos - nbMove) % modulo;
                if (currPos < 0) currPos += modulo;
            }
            key += zeroHits;
        }
        System.out.println(key);
    }
}
