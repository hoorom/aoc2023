package aoc2023.seventeen;

import aoc2023.graph.Position;

public class Day17 {

    static String test = """
            2413432311323
            3215453535623
            3255245654254
            3446585845452
            4546657867536
            1438598798454
            4457876987766
            3637877979653
            4654967986887
            4564679986453
            1224686865563
            2546548887735
            4322674655533""";

    static String minTest = """
            24134323113
            32154535356
            32552456542
            34465858454
            45466578675
            14385987984
            44578769877
            36378779796
            12246868655
            25465488877
            43226746555""";

    public static void main(String[] args) {

        Graph.field = toArray(test);
        Graph originGraph = new Graph(null, null, new Node(new Position(0, 0), null));
        originGraph.setWeight(0);
        Graph.allGraphs.add(originGraph);
        Graph end = null;
        int i = 0;
        while (end == null) {
            System.out.println(++i);
            end = originGraph.move();
        }
        String charArray = Graph.createCharArray(Graph.field.length, Graph.field[0].length, end.getPathPositions());
        System.out.println(charArray);
        System.out.println(end.getWeight());

    }

    public static char[][] toArray(String input) {
        String[] lines = input.split("\n");

        // Maintenant, pour chaque ligne, divisez-la en caractères
        char[][] array = new char[lines.length][];
        for (int i = 0; i < lines.length; i++) {
            array[i] = lines[i].toCharArray();
        }
        return array;
    }
}
