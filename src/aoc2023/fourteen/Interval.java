package aoc2023.fourteen;

public class Interval {

    Integer stoneIndex;
    Integer ballsAfter;

    public Interval(Integer stoneIndex, Integer ballsAfter) {
        this.stoneIndex = stoneIndex;
        this.ballsAfter = ballsAfter;
    }

    public Integer getStoneIndex() {
        return stoneIndex;
    }

    public void setStoneIndex(Integer stoneIndex) {
        this.stoneIndex = stoneIndex;
    }

    public Integer getBallsBetween() {
        return ballsAfter;
    }

    public void setBallsBetween(Integer ballsBetween) {
        this.ballsAfter = ballsBetween;
    }
}
