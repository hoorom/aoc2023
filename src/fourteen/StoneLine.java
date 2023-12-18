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

        intervals = new ArrayList<>();
        Interval interval = new Interval(0, 0);
        for (int i = 0; i < originalLine.size(); i++) {
            if (originalLine.get(i) == '#') {
                intervals.add(interval);
                interval = new Interval(i + 1, 0);
            } else if(originalLine.get(i) == 'O'){
                interval.setBallsBetween(interval.ballsAfter + 1);
            }
        }
        intervals.add(interval);
    }

    public Integer getWeight() {
        int weight = 0;
        for (Interval interval : getIntervals()) {
            Integer ballsBetween = interval.ballsAfter;
            for (Integer i = 0; i < ballsBetween; i++) {
                weight += length- (interval.stoneIndex) - i;
            }
        }
        return weight;
    }

    public String transformedLine() {
        StringBuilder stringBuilder = new StringBuilder();
        int lastIndex = 0;
        for (Interval interval : intervals) {
            lastIndex = interval.stoneIndex;
            if(interval.stoneIndex != 0) {
                stringBuilder.append(".".repeat((interval.stoneIndex - 1) - stringBuilder.length()));
                stringBuilder.append("#");
            }

            stringBuilder.append("O".repeat(interval.ballsAfter));
        }


        if (stringBuilder.length() < length) {
            stringBuilder.append(".".repeat((length - 1) - stringBuilder.length()));
        }
        if (stringBuilder.length() < length) {
            if(intervals.size() > 1 && lastIndex == length) {
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

}
