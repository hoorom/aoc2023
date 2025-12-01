package aoc2024.day21;

import java.util.*;

public class Resolver {

    // Configuration du pavé numérique
    static final char[][] KEYPAD = {
            {'7', '8', '9'},
            {'4', '5', '6'},
            {'1', '2', '3'},
            {'0', 'A', ' '}
    };

    // Déplacements : haut, bas, gauche, droite
    static final int[] DX = {-1, 1, 0, 0};
    static final int[] DY = {0, 0, -1, 1};
    static final char[] DIRS = {'^', 'v', '<', '>'};

    public static void main(String[] args) {
        String code = "029A"; // Remplace par le code voulu
        String commands = findDirectionsForCode(code);
        System.out.println(commands);
    }

    // Trouver la séquence de directions pour taper tout le code
    public static String findDirectionsForCode(String code) {
        // Position initiale : sur le bouton 'A' (ligne 3, colonne 1)
        int x = 3, y = 1;
        StringBuilder allCommands = new StringBuilder();
        for (char digit : code.toCharArray()) {
            String seq = bfsToTarget(x, y, digit);
            allCommands.append(seq);
            // Appliquer la séquence pour déterminer la nouvelle position
            int[] next = simulateMove(x, y, seq);
            x = next[0];
            y = next[1];
        }
        return allCommands.toString();
    }

    // Recherche en largeur pour atteindre la cible minimale
    static String bfsToTarget(int startX, int startY, char target) {
        Queue<int[]> queue = new ArrayDeque<>();
        Map<String, String> pathMap = new HashMap<>();
        queue.add(new int[]{startX, startY});
        pathMap.put(startX + "," + startY, "");

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int x = pos[0], y = pos[1];
            String path = pathMap.get(x + "," + y);
            if (KEYPAD[x][y] == target) {
                return path + "A"; // Ajoute activation
            }
            for (int d = 0; d < 4; d++) {
                int nx = x + DX[d];
                int ny = y + DY[d];
//                if (nx >= 0 && ny >= 0 && nx < KEYPAD.length && ny < KEYPAD[nx] && KEYPAD[nx][ny] != ' ') {
//                    String state = nx + "," + ny;
//                    if (!pathMap.containsKey(state)) {
//                        queue.add(new int[]{nx, ny});
//                        pathMap.put(state, path + DIRS[d]);
//                    }
//                }
            }
        }
        throw new IllegalArgumentException("Impossible d’atteindre " + target);
    }

    // Simule le déplacement pour connaître la prochaine position
    static int[] simulateMove(int x, int y, String seq) {
        for (char c : seq.toCharArray()) {
            for (int d = 0; d < 4; d++) {
                if (c == DIRS[d]) {
                    int nx = x + DX[d], ny = y + DY[d];
//                    if (nx >= 0 && ny >= 0 && nx < KEYPAD.length && ny < KEYPAD[nx] && KEYPAD[nx][ny] != ' ') {
//                        x = nx;
//                        y = ny;
//                    }
                }
            }
        }
        // On reste sur place après avoir tappé 'A'
        return new int[]{x, y};
    }
}
