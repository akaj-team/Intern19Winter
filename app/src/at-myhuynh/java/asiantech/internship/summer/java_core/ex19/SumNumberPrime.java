package asiantech.internship.summer.java_core.ex19;

import java.util.logging.Logger;

import asiantech.internship.summer.java_core.common.Common;

public class SumNumberPrime {
    public static void main(String[] args) {
        Logger log = Logger.getLogger(SumNumberPrime.class.getName());
        int n = Common.input("Nhập vào số n:");
        for (int i = 10000; i < 100000; i++) {
            if (Common.isPrime(i) && Common.totalOfNumber(i) == n) {
                log.info(String.valueOf(i));
            }
        }

    }
}
