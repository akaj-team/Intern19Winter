package asiantech.internship.summer.java_core.ex20;

import java.util.logging.Logger;

import asiantech.internship.summer.java_core.common.Common;

public class FibonacciPrime {
    public static void main(String[] args) {
        int n = Common.input("Nhập vào số n:");
        showFibonacciIsPrime(n);
    }

    private static void showFibonacciIsPrime(int n) {
        Logger log = Logger.getLogger(FibonacciPrime.class.getName());
        int f1 = 1;
        int f2 = 1;
        int fn = 1;

        do {
            if (fn < n && Common.isPrime(fn)) {
                log.info(String.valueOf(fn));
            }
            fn = f1 + f2;
            f1 = f2;
            f2 = fn;
        } while (fn < n);
    }
}
