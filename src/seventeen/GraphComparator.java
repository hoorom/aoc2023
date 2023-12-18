package seventeen;

import java.util.Comparator;

public class GraphComparator implements Comparator<Graph> {
    @Override
    public int compare(Graph o1, Graph o2) {
        if(!o1.getSubGraphs().isEmpty() && o2.getSubGraphs().isEmpty() ) {
            return 1;
        }

        if(o1.getSubGraphs().isEmpty() && !o2.getSubGraphs().isEmpty() ) {
            return -1;
        }

        int graphWeight = o1.getWeight();
        int otherGraphWeight = o2.getWeight();
        if(graphWeight < otherGraphWeight) {
            return -1;
        } else if (graphWeight > otherGraphWeight) {
            return 1;
        }
        return 0;
    }
}
