import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day6 {

    public static void main(String[] args) {
        Race race1 = new Race(59688274L, 543102016641022L);


        long race1Result = multipliedWinningTime(race1);

        System.out.println(race1Result);
    }

    public static long multipliedWinningTime(Race race) {
        long speed = 0;
        long duration = race.duration;
        long numbers = 0;
        for (int i = 0; i < race.duration; i++) {
            --duration;
            ++speed;
            long result = duration * speed;
            if(result > race.best) {
//                if(multiplied == 0) {
//                    multiplied = result;
//                } else {
//                    multiplied *= result;
//                }
                ++numbers;
            }
        }
        return numbers;
    }

    public record Race(Long duration, Long best) {}
}
