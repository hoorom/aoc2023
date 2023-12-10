import java.util.ArrayList;
import java.util.List;

public class Day9 {

    public static String test = """
            0 3 6 9 12 15
            1 3 6 10 15 21
            10 13 16 21 30 45""";

    public static void main(String[] args) {
        List<String> list = Day9Input.input.lines().toList();
        int sum = 0;
        for (String s : list) {
            long result = result(s);
            System.out.println(result);
            sum += result;
        }
        System.out.println(sum);
    }

    public static long result(String line) {
        String[] values = line.split(" ");
                List<Integer> intValues = new ArrayList<>();
        for (String value : values) {
            intValues.add(Integer.valueOf(value));
        }
        return computeDiffs(intValues);
    }

    private static int computeDiffs(List<Integer> values) {
        List<Integer> differences = new ArrayList<>();
        boolean onlyZero = true;
        for (int i = 0; i < values.size() - 1; i++) {
            int intValue = values.get(i);
            int nextIntValue = values.get(i + 1);
            int difference = nextIntValue - intValue;
            if(difference != 0) {
                onlyZero = false;
            }
            differences.add(difference);
        }

        if(!onlyZero) {
            differences.addFirst(computeDiffs(differences));
        } else {
            differences.addFirst(0);
        }

        int newVal = values.getFirst() - differences.getFirst();
        return newVal;
    }
}
