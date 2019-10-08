package asiantech.internship.summer.java_core.ex01;

import asiantech.internship.summer.java_core.common.Common;

public class UCLN {

    public static void main(String[] args) {
        int a = Common.input("Nhập số a: ");
        int b = Common.input("Nhập số b: ");
        System.out.println("UCLN: " + ucln(a, b));
        System.out.println("BCNN: " + bcnn(a, b));
    }

    public static int ucln(int a, int b) {
        while (a != b) {
            if (a > b) {
                a -= b;
            } else {
                b -= a;
            }
        }
        return a;
    }

    public static int bcnn(int a, int b) {
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
