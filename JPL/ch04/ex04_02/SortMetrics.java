package ch04.ex04_02;

public final class SortMetrics implements Cloneable {

    public long probeCnt, compareCnt, swapCnt;

    public void init() {
        probeCnt = swapCnt = compareCnt = 0;
    }

    public String toString() {
        return probeCnt + " probes " + compareCnt + " comparees " + swapCnt
                + " swaps";
    }

    public SortMetrics clone() {
        try {
            return (SortMetrics) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.toString());
        }
    }

}
