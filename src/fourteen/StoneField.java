package fourteen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StoneField {

    private String field;

    public StoneField(String field) {
        this.field = field;
    }

    public void cycle() {
        bendField();
        rotate();

        bendField();
        rotate();

        bendField();
        rotate();

        bendField();
        rotate();
    }

    public void rotate() {
        String[] lines = field.split("\n");
        int M = lines.length;
        int N = lines[0].length();
        char[][] matrix = new char[M][N];
        for (int i = 0; i < M; i++) {
            matrix[i] = lines[i].toCharArray();
        }

        // Effectue la rotation
        for (int x = 0; x < N / 2; x++) {
            for (int y = x; y < N - x - 1; y++) {
                char temp = matrix[x][y];
                matrix[x][y] = matrix[y][N - 1 - x];
                matrix[y][N - 1 - x] = matrix[N - 1 - x][N - 1 - y];
                matrix[N - 1 - x][N - 1 - y] = matrix[N - 1 - y][x];
                matrix[N - 1 - y][x] = temp;
            }
        }

        // Compile la String
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                result.append(matrix[j][i]);
            }
            result.append("\n");
        }
        field = result.toString();
    }

    private void bendField() {
        List<List<Character>> lines = initLines();
        List<String> transformedLines = new ArrayList<>();
        for (List<Character> fieldLine : lines) {

            if("#...#...OO".equals(fieldLine.stream().map(String::valueOf).collect(Collectors.joining()))) {
                System.out.println("debug");
            }

            StoneLine stoneLine = new StoneLine(fieldLine);
            stoneLine.bend();

            System.out.println("------------------------------");
            System.out.println(fieldLine.stream().map(String::valueOf).collect(Collectors.joining()));
            System.out.println(stoneLine.transformedLine());
            System.out.println("------------------------------");

            transformedLines.add(stoneLine.transformedLine());
        }
        field = String.join("\n", transformedLines);
    }

    private List<List<Character>> initLines() {
        List<List<Character>> fieldLines = new ArrayList<>();
        List<String> list = field.lines().toList();

        boolean init = false;
        for (String s : list) {
            if(!init) {
                init = true;
                for (int l = 0; l < s.length(); ++l) {
                    fieldLines.add(new ArrayList<>());
                }
            }
            char[] charArray = s.toCharArray();
            for (int i = 0; i < charArray.length; i++) {
                fieldLines.get(i).add(charArray[i]);
            }
        }
        return fieldLines;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
