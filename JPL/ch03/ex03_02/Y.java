package ch03.ex03_02;

public class Y extends X {

    protected int yMask = 0xff00;

    {
        printMask(6);
    }

    public Y() {
        fullMask |= yMask;
        printMask(7);
    }

    public void printMask(int step) {
        System.out.printf("%d\t%x\t%x\t%x\t\n", step, xMask, yMask, fullMask);
    }

}
