package aoc2024;

import java.util.ArrayList;
import java.util.List;

public class DijkstraPath {

    private List<Plan.Position> path = new ArrayList<>();

    private Integer currentWeight;

    private boolean treated = false;

    public DijkstraPath()  {

    }

    
    public DijkstraPath(DijkstraPath dijkstraPath) {
        this.path.addAll(dijkstraPath.path);
    }

    public List<Plan.Position> getPath() {
        return path;
    }

    public void setPath(List<Plan.Position> path) {
        this.path = path;
    }

    public Integer getCurrentWeight() {
        return path.size() - 1;
//        return currentWeight;
    }

    public void setCurrentWeight(Integer currentWeight) {
        this.currentWeight = currentWeight;
    }

    public Plan.Position getCurrentPosition() {
        return path.getLast();
    }

    public boolean isTreated() {
        return treated;
    }

    public void setTreated(boolean treated) {
        this.treated = treated;
    }

    @Override
    public String toString() {
        return "DijkstraPath{" + "lastPos=" + path.getLast() + ", weight=" + getCurrentWeight() + '}';
    }
}
