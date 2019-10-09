package asiantech.internship.summer.java_core.ex07;

import java.util.logging.Logger;

import asiantech.internship.summer.java_core.common.Common;

public class Fibonacci {

    public static void main(String[] args) {
        Logger log = Logger.getLogger(Fibonacci.class.getName());
        int n = Common.input("Nhập vào n: ");
        int count = 2;
        int f1 = 1;
        int f2 = 1;
        int fn;

        do {
            fn = f1 + f2;
            f1 = f2;
            f2 = fn;
            count++;
        } while (count < n);

        log.info("Số fibonaci thứ " + n + ": " + fn);
    }

}
