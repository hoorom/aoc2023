import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day12 {

    public static String testF = """
            ???.### 1,1,3
            .??..??...?##. 1,1,3
            ?#?#?#?#?#?#?#? 1,3,1,6
            ????.#...#... 4,1,1
            ????.######..#####. 1,6,5
            ?###???????? 3,2,1""";

    public static String test = """
           ?#?#?#?#?#?#?#? 1,3,1,6""";

    public static String testWithoutInterrogation = """
            #.#.### 1,1,3
            .#...#....###. 1,1,3
            .#.###.#.###### 1,3,1,6
            ####.#...#... 4,1,1
            #....######..#####. 1,6,5
            .###.##....# 3,2,1""";


    public static void main(String[] args) {
        List<InputLine> parse = parse(test);
        long sum = 0;
        int i = 0;
        for (InputLine inputLine : parse) {
            ++i;
            if(i % 10 == 0) {
                System.out.println(i);
            }
            long numberOfCombination = getNumberOfCombination(inputLine);
            System.out.println(numberOfCombination);
            sum += numberOfCombination;
        }

        System.out.println(sum);

    }

    public static long getNumberOfCombination(InputLine inputLine) {

        Equipment equipment = inputLine.equipment;
        String equipmentString = equipment.equipments;
        Indication indication = inputLine.indication;
        long nbSol = 0;

        List<String> duplicated = new ArrayList<>();

        equipmentString = STR."\{equipmentString}?\{equipmentString}?\{equipmentString}?\{equipmentString}?\{equipmentString}";

        generateCombinations(equipmentString, duplicated, 0, indication.values);
        System.out.println("Combination generated");
        for (String s : duplicated) {
            boolean ok = isOk(s, new ArrayList<>(indication.values));
            if(ok) {
                ++nbSol;
            }
        }


        return nbSol;
    }

    private static boolean isOk(String s, List<Integer> indication) {
        List<Integer> resolved = computeCurrentlyResolved(s, indication.size());
        return resolved.equals(indication);
    }


    public static void generateCombinations(String input, List<String> result, int start, List<Integer> values) {
        System.out.println("Computing combination for : " + input);
        for(int i = start; i < input.length(); i++) {
            if(input.charAt(i) == '?') {
                String newStr = STR."\{input.substring(0, i)}.\{input.substring(i + 1)}";

                List<Integer> resolved = computeCurrentlyResolved(newStr, values.size());
                for (int k = 0; k < resolved.size(); k++) {
                    if(values.size() >= resolved.size() && resolved.get(k) > values.get(k)) {
                        System.out.println("cut . ");
                        return;
                    }

                    if(!hasInterrogationForGroup(input, k)) {
                        if(k < resolved.size() - 1 && resolved.get(k) < values.get(k)) {
                            System.out.println("cut . ");
                            return;
                        }
                    }
                }

                if(!newStr.contains("?")) {
                    result.add(newStr);
                }
                generateCombinations(newStr, result, i + 1, values);

                // replace '?' with '#'
                newStr = STR."\{input.substring(0, i)}#\{input.substring(i + 1)}";

                resolved = computeCurrentlyResolved(newStr, values.size());
//                if(resolved.size() > values.size()) {
//                    return ;
//                }

                for (int k = 0; k < resolved.size(); k++) {
                    if(values.size() >= resolved.size() && resolved.get(k) > values.get(k)) {
                        System.out.println("cut # ");
                        return;
                    }

                    if(k < resolved.size() - 1 && resolved.get(k) < values.get(k)) {
                        System.out.println("cut # ");
                        return;
                    }
                }

                if(!newStr.contains("?")) {
                    result.add(newStr);
                }
                generateCombinations(newStr, result, i + 1, values);
            }
        }
    }

    private static boolean hasInterrogationForGroup(String input, int k) {
        int nbGroup = 0;
        boolean interrogationFound = false;
        boolean currentlyOnHash = false;
        for (char c : input.toCharArray()) {

            if(nbGroup > k) {
                return false;
            }

            if(nbGroup == k) {
                if(c == '?') {
                    return true;
                }
            }

            if(c == '#' && !currentlyOnHash) {
                ++nbGroup;
                currentlyOnHash = true;
            } else if(c == '.') {
                currentlyOnHash = false;
            }
        }

        return false;
    }

    public static List<Integer> computeCurrentlyResolved(String equipmentString, int length) {
        List<Integer> currentlyResolved = new ArrayList<>();

        char[] charArray = equipmentString.toCharArray();

        int currentLength = 0;
        for (char c : charArray) {
            if(c == '?') {
                return currentlyResolved;
            }

            if(c == '#') {
                ++currentLength;
            } else if(currentLength != 0) {
                currentlyResolved.addFirst(currentLength);
                currentLength = 0;
            }
        }

        if(currentLength != 0) {
            currentlyResolved.addFirst(currentLength);
        }
//        System.out.println(currentlyResolved);
//        while (currentlyResolved.size() < length) {
//            currentlyResolved.addFirst(0);
//        }
        return currentlyResolved;
    }

    public static List<InputLine> parse(String input) {
        Stream<String> lines = input.lines();
        List<String> list = lines.toList();

        List<InputLine> inputLines = new ArrayList<>();
        for (String line : list) {
            String[] s = line.split(" ");
            String equipments = s[0];
            String indications = s[1];

            Equipment equipment = new Equipment(equipments);
            String[] split = indications.split(",");
            List<Integer> indicationList = new ArrayList<>();
            for (String string : split) {
                indicationList.add(Integer.valueOf(string));
            }
            for (String string : split) {
                indicationList.add(Integer.valueOf(string));
            }
            for (String string : split) {
                indicationList.add(Integer.valueOf(string));
            }
            for (String string : split) {
                indicationList.add(Integer.valueOf(string));
            }
            for (String string : split) {
                indicationList.add(Integer.valueOf(string));
            }


            inputLines.add(new InputLine(equipment, new Indication(indicationList)));

        }

        return inputLines;
    }

    public record Indication(List<Integer> values) {}

    public record Equipment(String equipments) {}

    public record InputLine(Equipment equipment, Indication indication) {}
}



