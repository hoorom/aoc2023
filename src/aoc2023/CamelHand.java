package aoc2023;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CamelHand implements Comparable<CamelHand> {

    private String cards;

    private Integer money;

    public CamelHand(String cards, Integer money) {
        this.cards = cards;
        this.money = money;
    }

    public String getCards() {
        return cards;
    }

    public void setCards(String cards) {
        this.cards = cards;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CamelHand camelHand)) {
            return false;
        }
        return Objects.equals(cards, camelHand.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    @Override
    public int compareTo(CamelHand o) {
        if(!getKind().equals(o.getKind())) {
            return getKind() - o.getKind();
        }

        for (int i = 0; i < 5; i++) {
            char card = cards.charAt(i);
            char otherCard = o.cards.charAt(i);
            int cardValue = cardValue(card);
            int otherCardValue = cardValue(otherCard);

            if(cardValue != otherCardValue) {
                return cardValue - otherCardValue;
            }
        }
        return 0;
    }

    public static int cardValue(char c) {
        List<Character> values = Arrays.asList('A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2', 'J');
        return values.indexOf(c);
    }

    public int getNbJ() {
        char[] charArray = cards.toCharArray();
        int nbj = 0;
        for (char c : charArray) {
            if(c == 'J') {
                ++nbj;
            }
        }
        return nbj;
    }

    public Integer getKind() {
        char[] sortedArray = cards.toCharArray();
        Map<Character, Integer> occurences = new HashMap<>();
        int nbJ = 0;
        for (char c : sortedArray) {
            if(c == 'J') {
                ++nbJ;
                continue;
            }
            occurences.putIfAbsent(c, 0);
            occurences.put(c, occurences.get(c) + 1);
        }



        Collection<Integer> values = occurences.values();
        if(values.contains(5)) {
            return 0;
        } else if (values.contains(4)) {
            if (nbJ == 1) {
                return 0;
            }
            return 1;
        } else if (values.contains(3) && values.contains(2)) {
            return 2;
        } else if (values.contains(3)) {
            if(nbJ == 1) {
                return 1;
            }

            if(nbJ == 2) {
                return 0;
            }
            return 3;
        } else if (values.contains(2)) {

            int nbPair = 0;
            for (Integer value : values) {
                if(value == 2) {
                    ++nbPair;
                }
            }
            if(nbPair == 2) {
                if(nbJ == 1) {
                    return 2;
                }
                return 4;
            } else {

                if (nbJ == 1) {
                    return 3;
                }

                if (nbJ == 2) {
                    return 1;
                }

                if (nbJ == 3) {
                    return 0;
                }
                return 5;
            }
        }

        if(nbJ == 1) {
            return 5;
        }

        if(nbJ == 2) {
            return 3;
        }

        if(nbJ == 3) {
            return 1;
        }

        if(nbJ == 4) {
            return 0;
        }

        if(nbJ == 5) {
            return 0;
        }
        return 6;
    }

    @Override
    public String toString() {
        return "CamelHand{" + "cards='" + cards + '\'' + '}';
    }
}
