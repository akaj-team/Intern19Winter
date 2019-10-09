package asiantech.internship.summer.java_core.ex01;

import java.util.logging.Logger;

import asiantech.internship.summer.java_core.common.Common;

public class CommonDivisor {

    public static void main(String[] args) {
        Logger log = Logger.getLogger(CommonDivisor.class.getName());
        int a = Common.input("Nhập số a: ");
        int b = Common.input("Nhập số b: ");
        log.info("Common Divisor: " + greatestCommonDivisor(a, b));
        log.info("Least Common Multiple: " + leastCommonMultiple(a, b));
    }

    public static int greatestCommonDivisor(int a, int b) {
        while (a != b) {
            if (a > b) {
                a -= b;
            } else {
                b -= a;
            }
        }
        return a;
    }

    public static int leastCommonMultiple(int a, int b) {
        int index = a > b ? a : b;
        while (true) {
            if (index % a == 0 && index % b == 0) {
                break;
            }
            index++;
        }
        return index;
    }
}
