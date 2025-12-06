package aoc2025.day6;

import java.util.ArrayList;
import java.util.List;

public class Resolver {

    void main() {
        String input = Input.input;
        List<String> list = input.lines().toList();

        List<char[]> charList = new ArrayList<>();
        for (String line: list) {
            charList.add(line.toCharArray());
        }
        int maxLength = charList.getFirst().length;
        long total = 0;

        int nbMult = 0;
        int nbPlus = 0;

        String currSign = "";
        List<Integer> numbers = new ArrayList<>();
        for (int i = maxLength - 1; i >= 0; i--) {
            String currNumber = "";
            for (char[] chars : charList) {
                char currChar = chars[i];
                if(Character.isDigit(currChar)) {
                    currNumber += currChar;
                }
                if(currChar == '+' || currChar == '*') {
                    currSign = ""+currChar;
                }

            }

            if(currNumber.isEmpty()) {
                if(currSign.equals("+")) {
                    long sum = 0;
                    String operations = "";
                    for (Integer number : numbers) {
                        operations += number + " + ";
                        sum += number;
                    }
                    ++nbPlus;
                    System.out.println(operations + " = " + sum);
                    total += sum;
                } else {
                    long mult = 1;

                    String operations = "";
                    for (Integer number : numbers) {
                        operations += number + " * ";
                        mult *= number;
                    }
                    System.out.println(operations + " = " + mult);
                    ++nbMult;
                    total += mult;
                }
                numbers.clear();
                currSign = "";
                currNumber = "";
            } else {
                numbers.add(Integer.parseInt(currNumber));
                currNumber = "";
            }
        }

        if(currSign.equals("+")) {
            long sum = 0;
            StringBuilder operations = new StringBuilder();
            for (Integer number : numbers) {
                operations.append(number).append(" + ");
                sum += number;
            }
            ++nbPlus;
            System.out.println(operations + " = " + sum);
            total += sum;
        } else {
            long mult = 1;

            StringBuilder operations = new StringBuilder();
            for (Integer number : numbers) {
                operations.append(number).append(" * ");
                mult *= number;
            }
            ++nbMult;
            System.out.println(operations + " = " + mult);
            total += mult;
        }
        System.out.println("Mult : " + nbMult + " + " + "Plus : " + nbPlus + " =");
        System.out.println(total);
    }



    void p1() {
        String input = Input.input;

        List<String> list = input.lines().toList();
        List<String[]> charList = new ArrayList<>();
        for (String val : list) {
            String[] split = val.split("\\s+");
            charList.add(split);
        }

        int nbCharByLine = charList.getFirst().length;
        long total = 0;
        String sign = "";
        for (int i = 0; i < nbCharByLine; i++) {
            long currAcc = 0;
            List<String> calculation = new ArrayList<>();
            for (int j = charList.size() - 1; j >= 0; j--) {
                String[] split = charList.get(j);
                String currVal = split[i];
                calculation.add(currVal);
                if(currVal.equals("+")) {
                    sign = "+";
                } else if(currVal.equals("*")) {
                    sign = "*";
                    currAcc = 1;
                } else {
                    if(sign.equals("+")) {
                        currAcc += Long.parseLong(currVal);
                    } else {
                        currAcc *= Long.parseLong(currVal);
                    }
                }

            }
            System.out.println("currAcc : " + currAcc);
            System.out.println("------------");
            total += currAcc;
        }

        System.out.println(total);

    }

}
