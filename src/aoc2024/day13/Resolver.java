package aoc2024.day13;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Resolver {

    static Pattern patternEq = Pattern.compile("Button [A-B]: X\\+(\\d+), Y\\+([0-9]+)");
    static Pattern patternRes = Pattern.compile("Prize: X=([0-9]+), Y=([0-9]+)");
    static String test = """
            Button A: X+94, Y+34
            Button B: X+22, Y+67
            Prize: X=8400, Y=5400
                        
            Button A: X+26, Y+66
            Button B: X+67, Y+21
            Prize: X=12748, Y=12176
                        
            Button A: X+17, Y+86
            Button B: X+84, Y+37
            Prize: X=7870, Y=6450
                        
            Button A: X+69, Y+23
            Button B: X+27, Y+71
            Prize: X=18641, Y=10279""";



    public static void main(String[] args) {
        List<String> list = Input.input.lines().toList();
        long a = 0;
        long b = 0;
        for (int i = 0; i < list.size(); i++) {
            if ("".equals(list.get(i)) || i == list.size() - 1) {
                int dec = "".equals(list.get(i)) ? 1 : 0;
                Matcher matcherEq1 = patternEq.matcher(list.get(i - 2 - dec));
                Matcher matcherEq2 = patternEq.matcher(list.get(i - 1 - dec));
                Matcher matcherRes = patternRes.matcher(list.get(i - dec));
                if(matcherEq1.find() && matcherEq2.find() && matcherRes.find()){
                    long eq11 = Integer.parseInt(matcherEq1.group(1));
                    long eq12 = Integer.parseInt(matcherEq1.group(2));
                    long eq21 = Integer.parseInt(matcherEq2.group(1));
                    long eq22 = Integer.parseInt(matcherEq2.group(2));
                    long res1 = Integer.parseInt(matcherRes.group(1)) + 10000000000000L;
                    long res2 = Integer.parseInt(matcherRes.group(2)) + 10000000000000L;
                    long p1 = (eq11 * res2) - eq12 * res1;
                    long p2 = (eq11 * eq22) - (eq12 * eq21);
                    long currB = p1 / p2;
                    long r1 = res1 - (eq21 * currB);
                    long currA = r1 / eq11;

                    if(/*currA > 100 || currB > 100 || */currA < 0 || currB < 0 || p1 % p2 != 0 || r1 % eq11 != 0) {
                        continue;
                    }
                    a += 3*currA;
                    b += currB;
                    System.out.println(currA + " / " + currB);
                }
            }
        }
        System.out.println(a  + b);
    }

}
