package asiantech.internship.summer.java_core.ex05;

import java.util.logging.Logger;

import asiantech.internship.summer.java_core.common.Common;

public class ListPrime {

    public static void main(String[] args) {
        int n = Common.input("Nhập vào số n: ");
        print(n);
    }

    private static void print(int number) {
        Logger log = Logger.getLogger(ListPrime.class.getName());
        for (int i = 1; i <= number; i++) {
            if (Common.isPrime(i)) {
                log.info(String.valueOf(i));
            }
        }
    }
}
