package seventeen;

import graph.Position;

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

//    static String test = """
//            2413
//            3215
//            4546
//            4324""";

    public static void main(String[] args) {

        Graph.field = toArray(test);
        Graph originGraph = new Graph(null, null, new Node(new Position(0, 0), null));
        Graph.allGraphs.add(originGraph);
        Graph end = null;
        int i = 0;
        while (end == null) {
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
//
//    public static BGraph calculateShortestPathFromSource(BGraph graph, BNode source) {
//        source.setDistance(0);
//
//        Set<BNode> settledNodes = new HashSet<>();
//        Set<BNode> unsettledNodes = new HashSet<>();
//
//        unsettledNodes.add(source);
//
//        while (unsettledNodes.size() != 0) {
//            BNode currentNode = getLowestDistanceNode(unsettledNodes);
//            unsettledNodes.remove(currentNode);
//            for (Map.Entry< Node, Integer> adjacencyPair:
//                    currentNode.getAdjacentNodes().entrySet()) {
//                Node adjacentNode = adjacencyPair.getKey();
//                Integer edgeWeight = adjacencyPair.getValue();
//                if (!settledNodes.contains(adjacentNode)) {
//                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
//                    unsettledNodes.add(adjacentNode);
//                }
//            }
//            settledNodes.add(currentNode);
//        }
//        return graph;
//    }
//
//    private static Node getLowestDistanceNode(Set < Node > unsettledNodes) {
//        Node lowestDistanceNode = null;
//        int lowestDistance = Integer.MAX_VALUE;
//        for (Node node: unsettledNodes) {
//            int nodeDistance = node.getDistance();
//            if (nodeDistance < lowestDistance) {
//                lowestDistance = nodeDistance;
//                lowestDistanceNode = node;
//            }
//        }
//        return lowestDistanceNode;
//    }
//
//    private static void CalculateMinimumDistance(Node evaluationNode,
//            Integer edgeWeigh, Node sourceNode) {
//        Integer sourceDistance = sourceNode.getDistance();
//        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
//            evaluationNode.setDistance(sourceDistance + edgeWeigh);
//            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
//            shortestPath.add(sourceNode);
//            evaluationNode.setShortestPath(shortestPath);
//        }
//    }
}
