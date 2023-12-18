package fourteen;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StoneField {

    private String field;

    private Integer lastSum;

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
            StoneLine stoneLine = new StoneLine(fieldLine);
            stoneLine.bend();
            String transformedLine = stoneLine.transformedLine();

            transformedLines.add(transformedLine);
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

    public Integer getTotalWeight() {
        List<List<Character>> lines = initLines();
        int sum = 0;
        for (List<Character> line : lines) {
            for (int i = 0; i < line.size(); i++) {

                if(line.get(i) == 'O') {
                    sum += line.size() - i;
                }
            }
        }
        return sum;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StoneField that)) {
            return false;
        }
        return Objects.equals(field, that.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field);
    }
}
