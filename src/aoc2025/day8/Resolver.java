package aoc2025.day8;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Resolver {

    void main() {
        String input = Input.test;
        List<String> list = input.lines().toList();
        List<Coord3D> coords = new ArrayList<>();
        for (String coord : list) {
            String[] split = coord.split(",");
            Coord3D coord3D = new Coord3D(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
            coords.add(coord3D);
        }

        Coord3D coord3D = closestCoord(coords, coords.getFirst());
        HashSet<Coord3D> box = new HashSet<>();
        box.add(coord3D);
        box.add(coords.getFirst());

        Set<Set<Coord3D>> boxes = new HashSet<>();
        boxes.add(box);
    }
    static Junction closestCoord(List<Coord3D> coords) {
        Coord3D first = null;
        Coord3D second = null;
        int minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < coords.size(); i++) {
            for (int j = i + 1; j < coords.size(); j++) {
                int distance = coords.get(i).euclideanDistance(coords.get(j));
                if (distance < minDistance) {
                    minDistance = distance;
                    first = coords.get(i);
                    second = coords.get(j);
                }
            }
        }

        return new Junction(first, second);
    }
    
    static Coord3D closestCoord(List<Coord3D> coords, Coord3D target) {
        Coord3D closest = null;
        int minDistance = Integer.MAX_VALUE;

        for (Coord3D coord : coords) {
            int distance = target.euclideanDistance(coord);
            if (distance < minDistance) {
                minDistance = distance;
                closest = coord;
            }
        }

        return closest;
    }
    
    record Junction(Coord3D first, Coord3D second) {
        
    }

    record Coord3D(int x, int y, int z) {
        int euclideanDistance(Coord3D other) {
            return Math.abs(x - other.x) + Math.abs(y - other.y) + Math.abs(z - other.z);
        }
    }
}
