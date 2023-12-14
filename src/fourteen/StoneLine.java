package fourteen;

import java.util.ArrayList;
import java.util.List;

public class StoneLine {

    private Integer length;

    private List<Interval> intervals;

    private List<Character> originalLine;

    public StoneLine(List<Character> fieldLine) {
        originalLine = fieldLine;
        length  = fieldLine.size();
    }

    public void bend() {
        int nbBall = 0;
        int currIndex = 0;
        intervals = new ArrayList<>();
        for (int i = 0; i < originalLine.size(); i++) {
            if (originalLine.get(i) == '#') {
                intervals.add(new Interval(currIndex, nbBall));
                currIndex = i + 1;
                nbBall = 0;
            } else if(originalLine.get(i) == 'O'){
                ++nbBall;
            }
        }

        if(nbBall != 0) {
            intervals.add(new Interval(currIndex, nbBall));
        }
    }

    public Integer getWeight() {
        int weight = 0;
        for (Interval interval : getIntervals()) {
            Integer ballsBetween = interval.ballsBetween;
            for (Integer i = 0; i < ballsBetween; i++) {
                weight += length- (interval.stoneIndex) - i;
            }
        }
        return weight;
    }

    public String transformedLine() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Interval interval : intervals) {

            if(interval.stoneIndex != 0) {
                stringBuilder.append(".".repeat((interval.stoneIndex - 1) - stringBuilder.length()));
                stringBuilder.append("#");
            }

            stringBuilder.append("O".repeat(interval.ballsBetween));
        }


        stringBuilder.append(".".repeat((length - 1) - stringBuilder.length()));
        if (stringBuilder.length() < length) {
            if(intervals.size() > 1) {
                stringBuilder.append("#");
            } else {
                stringBuilder.append(".");
            }
        }

        if(stringBuilder.length() != length) {
            throw new RuntimeException();
        }
        return stringBuilder.toString();
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public List<Interval> getIntervals() {
        return intervals;
    }

    public void setIntervals(List<Interval> intervals) {
        this.intervals = intervals;
    }

    public List<Character> getOriginalField() {
        return originalLine;
    }

    public void setOriginalField(List<Character> originalField) {
        this.originalLine = originalField;
    }

    public record Interval(Integer stoneIndex, Integer ballsBetween) {}
}
