package asiantech.internship.summer.java_core.ex06;


import java.util.logging.Logger;

import asiantech.internship.summer.java_core.common.Common;

public class ListPrimeFirst {

    public static void main(String[] args) {
        int n = Common.input("Nhập vào n: ");
        print(n);
    }

    public static void print(int n) {
        Logger log = Logger.getLogger(ListPrimeFirst.class.getName());
        int count = 0;
        int number = 1;

        do {
            if (Common.isPrime(number)) {
                count++;
                log.info(String.valueOf(number));
            }
            number++;
        } while (count < n);
    }

}
