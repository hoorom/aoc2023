package aoc2024.day9;

import java.util.ArrayList;
import java.util.List;

public class Resolver {

    static String test = "2333133121414131402";

    public static void main(String[] args) {
        char[] charArray = test.toCharArray();

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < charArray.length; i++) {
            int val = Integer.parseInt(String.valueOf(charArray[i]));
            if(i % 2 == 0) {
                for (int j = 0; j < val; j++) {
                    result.add(i/2);
                }
//                result += String.valueOf(i/2).repeat(Integer.parseInt(String.valueOf(charArray[i])));
            } else {
                for (int j = 0; j < val; j++) {
                    result.add(Integer.MIN_VALUE);
//                    result += ".".repeat(val);
                }
            }
        }

//        char[] resultArray = result.toCharArray();

        List<Integer> finalResult = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {

            if(result.get(i) != Integer.MIN_VALUE) {
                finalResult.add(result.get(i));
            } else {
                int freeSpacies = 0;
                for (int sp = i; sp < result.size(); sp++) {
                    if(result.get(sp) == Integer.MIN_VALUE) {
                        ++freeSpacies;
                    } else {
                        break;
                    }
                }
                Integer currVal = null;
                int spaceRequired = 0;
                for (int l = result.size() - 1; l >= i; l--) {
                    if(result.get(l) != Integer.MIN_VALUE) {
                        if(currVal != null && !result.get(l).equals(currVal)) {
                            if(spaceRequired <= freeSpacies) {
                                for (int t = 0; t < spaceRequired; t++) {
                                    Integer valueToAdd = result.get(l + 1);
                                    finalResult.add(valueToAdd);
                                    ++i;
                                    result.set(l + t,Integer.MIN_VALUE);
                                }
                                break;
                            }
                            spaceRequired = 0;
                        }
                        currVal = result.get(l);
                        spaceRequired++;
                    } else {
                        currVal = null;
                        spaceRequired = 0;
                    }
                }
            }
        }

        long val = 0;
        for (int i = 0; i < finalResult.size(); i++) {
            Integer curr = finalResult.get(i);
            if(Integer.MIN_VALUE == curr) {
                continue;
            }
            System.out.println("Adding :" + (curr * i )+ " (" + curr + " * " + i+" )");
            val += curr * i;
        }

        System.out.println(val);
    }
}
