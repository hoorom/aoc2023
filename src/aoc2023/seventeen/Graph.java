package aoc2023.seventeen;

import aoc2023.graph.Direction;
import aoc2023.graph.Position;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
//import org.jetbrains.annotations.NotNull;

public class Graph implements Comparable<Graph> {

    public static char[][] field;

    public static char getCharAt(Position position) {
        return field[position.getX()][position.getY()];
    }

    private Set<Node> visitedNode = new HashSet<>();

    private Set<Graph> subGraphs = new HashSet<>();

    private Node node;

    private int weight;

    private Graph parentGraph;

    private Direction fromDirection;

    private static final Set<Graph> deadGraph = new HashSet<>();

    public static List<Graph> allGraphs = new LinkedList<>();

    public Graph(Graph parentGraph, Direction fromDirection, Node node) {
        this.parentGraph = parentGraph;
        this.node = node;
        this.fromDirection = fromDirection;

        if (parentGraph != null) {
            visitedNode.addAll(parentGraph.getVisitedNode());
        }
        weight = initWeight();
        visitedNode.add(node);
    }

    private int initWeight() {
        int weight = 0;
        Graph ancestor = this;
        while (ancestor != null) {
            weight += ancestor.node.getWeight();
            ancestor = ancestor.getParentGraph();
        }
        return weight;
    }


    public Graph move() {

        Graph lightestGraph = getMinGraph();
        List<Node> possibleNodes = lightestGraph.getPossibleNode();
        if(possibleNodes.isEmpty()) {
            deadGraph.add(lightestGraph);
            allGraphs.remove(lightestGraph);
            return null;
        }

        allGraphs.removeFirst();
        for (Node possibleNode : possibleNodes) {
            Graph subGraph = new Graph(lightestGraph, possibleNode.getFromDirection(), possibleNode);
            allGraphs.add(subGraph);
            lightestGraph.subGraphs.add(subGraph);
        }
        lightestGraph = getMinGraph();

        Position position = lightestGraph.node.getPosition();
        boolean end = position.getX() == Graph.field[0].length - 1 && position.getY() == Graph.field.length - 1;
        if(end) {
            return lightestGraph;
        }
        return null;
    }

    public Graph getMinGraph() {
        Graph minGraph = null;
        for (Graph graph : allGraphs) {
            if (minGraph == null || graph.getWeight() < minGraph.getWeight()) {
                minGraph = graph;
            }
        }
        return minGraph;
    }

    private List<Node> getPossibleNode() {
        Direction[] directions = Direction.values();
        List<Direction> possibleDirections = new ArrayList<>(Arrays.asList(directions));
        if(fromDirection != null) {
            possibleDirections.remove(fromDirection.getOpposite());
        }

        int numberOfSameDirection = 1;
        if(parentGraph != null && parentGraph.getFromDirection() != null && parentGraph.getFromDirection().equals(getFromDirection())) {
            numberOfSameDirection = 2;
            if(parentGraph.getParentGraph().getFromDirection() != null && parentGraph.getParentGraph().getFromDirection().equals(getFromDirection())) {
                numberOfSameDirection = 3;
            }
        }

        if(numberOfSameDirection == 3) {
            possibleDirections.remove(fromDirection);
        }

        List<Node> possibleNodes = new ArrayList<>();
        for (Direction possibleDirection : possibleDirections) {

            Position position = node.getPosition();
            Position clonedPosition = position.clone();
            possibleDirection.move(clonedPosition);
            if(clonedPosition.getX() < 0 || clonedPosition.getY() < 0 || clonedPosition.getY() >= field.length || clonedPosition.getX() >= field[0].length) {
                continue;
            }
            Node e = new Node(clonedPosition, possibleDirection);
            if(visitedNode.contains(e)) {
                continue;
            }
            possibleNodes.add(e);
        }
        return possibleNodes;
    }

    public Set<Node> getVisitedNode() {
        return visitedNode;
    }

    public void setVisitedNode(Set<Node> visitedNode) {
        this.visitedNode = visitedNode;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Set<Graph> getSubGraphs() {
        return subGraphs;
    }

    public void setSubGraphs(Set<Graph> subGraphs) {
        this.subGraphs = subGraphs;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Graph getParentGraph() {
        return parentGraph;
    }

    public void setParentGraph(Graph parentGraph) {
        this.parentGraph = parentGraph;
    }

    public Direction getFromDirection() {
        return fromDirection;
    }

    public void setFromDirection(Direction fromDirection) {
        this.fromDirection = fromDirection;
    }

    public List<Position> getPathPositions() {
        List<Position> positions = new ArrayList<>();
        Graph ancestor = this;
        while (ancestor != null) {
            positions.add(ancestor.getNode().getPosition().clone());
            ancestor = ancestor.getParentGraph();
        }

        return positions;
    }

    public static String createCharArray(int x, int y, List<Position> positions) {
        char[][] array = new char[x][y];

        // Initialize with default value
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                array[i][j] = '.'; // Default value
            }
        }

        // Set specified positions to '#'
        for (Position position : positions) {
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

    @Override
    public String toString() {
        return String.join(" => ", getPathPositions().stream().map(p -> p.toString()).toList());
    }

    @Override
    public int compareTo( Graph o) {

        if(!getSubGraphs().isEmpty() && o.getSubGraphs().isEmpty() ) {
            return 1;
        }

        if(getSubGraphs().isEmpty() && !o.getSubGraphs().isEmpty() ) {
            return -1;
        }

        int graphWeight = getWeight();
        int otherGraphWeight = o.getWeight();
        if(graphWeight < otherGraphWeight) {
            return -1;
        } else if (graphWeight > otherGraphWeight) {
            return 1;
        }
        return 0;
    }
}
