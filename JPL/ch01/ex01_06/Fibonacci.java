package ch01.ex01_06;

public class Fibonacci {

    public static void exec() {
        int lo = 1;
        int hi = 1;
        System.out.println(lo);
        while (hi < 50) {
            System.out.println(hi);
            hi = lo + hi;
            lo = hi - lo;
        }
    }

}
