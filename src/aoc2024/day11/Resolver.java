package aoc2024.day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Resolver {

    static Map<String, List<String>> memo = new HashMap<>();

    public static void main(String[] args) {
        String input = Input.input;

        String[] split = input.split(" ");

        List<String> list = Arrays.stream(split).toList();

        long nbStone = 0;
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<Long>> futures = new ArrayList<>();
        for (String s : list) {
            Future<Long> future = executorService.submit(() -> blink(s, 0));
            futures.add(future);
        }

        for (Future<Long> future : futures) {
            try {
                nbStone += future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
        System.out.println(nbStone);

    }

        private static final ExecutorService executorService = Executors.newCachedThreadPool();

        private static long blink (String s,int depth){
            if (depth == 75) {
                return 1;
            }

            List<String> list = memo.get(s);
            if (list == null) {
                list = new ArrayList<>();
                long l = Long.parseLong(s);
                s = String.valueOf(l);
                if (l == 0) {
                    list.add("1");
                } else if (String.valueOf(l).length() % 2 == 0) {
                    int endIndex = s.length() >> 1;
                    list.add(String.valueOf(Long.parseLong(s.substring(0, endIndex))));
                    list.add(String.valueOf(Long.parseLong(s.substring(endIndex))));
                } else {
                    list.add(String.valueOf(l * 2024));
                }
                memo.put(s, list);
            }

            long sumStone = 0;
            List<Future<Long>> futures = new ArrayList<>();
            for (String newStones : list) {
                Future<Long> future = executorService.submit(() -> blink(newStones, depth + 1));
                futures.add(future);
            }

            for (Future<Long> future : futures) {
                try {
                    sumStone += future.get();
                } catch (InterruptedException | ExecutionException e) {
                    // Handle exceptions
                    e.printStackTrace();
                }
            }
            return sumStone;
        }

}
