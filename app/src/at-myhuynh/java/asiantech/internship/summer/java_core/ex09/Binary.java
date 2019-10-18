package asiantech.internship.summer.java_core.ex09;

import java.util.logging.Logger;

import asiantech.internship.summer.java_core.common.Common;

public class Binary {

    public static void main(String[] args) {
        int n = Common.input("Nhập vào độ dài n: ");
        int[] a = new int[n];

        for (int i = 0; i < Math.pow(2, n); i++) {
            generate(a, n, i);
            print(a);
        }
    }

    private static void generate(int[] a, int n, int index) {
        if (index != 0) {
            a[n - 1]++;
        }
        for (int i = n - 1; i > 0; --i) {
            if (a[i] > 1) {
                a[i - 1]++;
                a[i] -= 2;
            }
        }
    }

    private static void print(int[] a) {
        Logger log = Logger.getLogger(Binary.class.getName());
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            str.append(a[i]);
            if (i == a.length - 1) {
                log.info(str.toString());
                str = new StringBuilder();
            }
        }
    }

}
