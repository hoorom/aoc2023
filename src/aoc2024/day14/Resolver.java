package aoc2024.day14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

public class Resolver {

    static String test = """
            p=0,4 v=3,-3
            p=6,3 v=-1,-3
            p=10,3 v=-1,2
            p=2,0 v=2,-1
            p=0,0 v=1,3
            p=3,0 v=-2,-2
            p=7,6 v=-1,-3
            p=3,0 v=-1,-2
            p=9,3 v=2,3
            p=7,3 v=-1,2
            p=2,4 v=2,-3
            p=9,5 v=-3,-3""";

    static Pattern pattern = Pattern.compile("-?\\d+");
    //    static int testColSize = 11;
    //    static  int testRowSize = 7;

    static    int testColSize = 101;
    static   int testRowSize = 103;
    public static void main(String[] args) {
        List<String> list = Input.input.lines().toList();
        List<Robot> robots = initRobots(list);


        for (int i = 0; i < 1000000; i++) {
            int[] robotByQ = new int[4];
            Arrays.fill(robotByQ, 0);
            for (Robot robot : robots) {
                //                    System.out.println(robot);

                robot.move();
                if(robot.x < testColSize / 2 && robot.y < testRowSize / 2) {
                    robotByQ[0] =  robotByQ[0] + 1;
                } else if (robot.x > testColSize / 2 && robot.y < testRowSize / 2) {
                    robotByQ[1] = robotByQ[1] + 1;
                } else if (robot.x < testColSize / 2 && robot.y > testRowSize / 2) {
                    robotByQ[2] = robotByQ[2] + 1;
                } else if (robot.x > testColSize / 2 && robot.y > testRowSize / 2) {
                    robotByQ[3] = robotByQ[3] + 1;
                } else {
                    //                        System.out.println("?");
                }
            }

            if(robotByQ[0] > 200 || robotByQ[1] > 200 || robotByQ[2] > 200 || robotByQ[3] > 200) {
                showRobots(robots);
                System.out.println(i + " : " + robotByQ[0] + " / " + robotByQ[1] + " / " + robotByQ[2] + " / " + robotByQ[3]);
            }
        }

    }

    private static void showRobots(List<Robot> robots) {
        for (int y = 0; y < testRowSize; y++) {
            for (int x = 0; x < testColSize; x++) {

                int finalX = x;
                int finalY = y;
                long count = robots.stream().filter(r -> r.x == finalX && r.y == finalY).count();
                System.out.print(count == 0 ? "." : count);
            }
            System.out.println();
        }
    }

    private static @NotNull List<Robot> initRobots(List<String> list) {
        List<Robot> robots = new ArrayList<>();
        for (String r : list) {
            Matcher matcher = pattern.matcher(r);
            List<Integer> matches = new ArrayList<>();
            while (matcher.find()) {
                matches.add(Integer.parseInt(matcher.group()));
            }
            if (matches.size() > 3) {
                int x = matches.get(0);
                int y = matches.get(1);
                int speedX = matches.get(2);
                int speedY = matches.get(3);
                Robot robot = new Robot(x, y, speedX, speedY);
                robots.add(robot);
            }
        }
        return robots;
    }
}
