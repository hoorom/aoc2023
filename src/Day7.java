import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Day7 {

    public static String test = """
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483""";

    public static void main(String[] args) {
//        CamelHand c1 = new CamelHand("32T3K", 765);
//        CamelHand c2 = new CamelHand("T55J5", 684);
//        CamelHand c3 = new CamelHand("KK677", 28);
//        CamelHand c4 = new CamelHand("KTJJT", 220);
//        CamelHand c5 = new CamelHand("QQQJA", 483);

        Stream<String> lines = Day7Input.value.lines();
//        Stream<String> lines = test.lines();
        List<String> list = lines.toList();
        List<CamelHand> hands = new ArrayList<>();

        for (String s : list) {
            String[] s1 = s.split(" ");
            String cards = s1[0];
            String s2 = s1[1];
            CamelHand camelHand = new CamelHand(cards, Integer.parseInt(s2));
            hands.add(camelHand);
        }
        Collections.sort(hands);

        Integer sum = 0;
        for (CamelHand hand : hands) {
            System.out.println(hand.getKind() + " : " +hand.getCards());
            int rank = hands.indexOf(hand);
            int factor = hands.size() - rank;
            int value = hand.getMoney() * factor;
            sum += value;
        }

        //248811015
        //248800891
        System.out.println(sum);
    }
}
