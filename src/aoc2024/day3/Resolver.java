package aoc2024.day3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Resolver {

    public static void main(String[] args) {

        String mulRegex = "mul\\((\\d{1,3}),(\\d{1,3})\\)";
        String doRegex = "do\\(\\)";
        String[] substrings = Input.input.split("don't\\(\\)");


        Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");

        int sum = 0;
        for (int i = 0; i < substrings.length; i++) {
            String text = substrings[i];
            if(i == 0) {
                Matcher matcher = pattern.matcher(text);
                while (matcher.find()) {
                    sum += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
                    System.out.println(matcher.group());
                }
            } else {
                String[] doS = text.split("do\\(\\)");
                for (int d = 0; d < doS.length; d++) {
                    if(d == 0) {
                        continue;
                    }

                    String aDo = doS[d];
                    Matcher matcher = pattern.matcher(aDo);
                    while (matcher.find()) {
                        sum += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
                        System.out.println(matcher.group());
                    }
                }
            }
        }



        System.out.println(sum);





    }
}
