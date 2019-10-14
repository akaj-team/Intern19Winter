package asiantech.internship.summer.java_core.ex11;

import java.util.logging.Logger;

import asiantech.internship.summer.java_core.common.Common;

public class Permutation {

    public static void main(String[] args) {
        int n = Common.input("Nhập vào số n: ");
        int[] arrInt = new int[n];

        initArr(arrInt, n);
        listPermutation(arrInt);

    }

    private static void initArr(int[] arrInt, int n) {
        for (int i = 0; i < n; i++) {
            arrInt[i] = i + 1;
        }
    }

    private static void listPermutation(int[] arrInt) {
        Logger log = Logger.getLogger(Permutation.class.getName());
        int n = arrInt.length;
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < n; i++) {
            str.append(arrInt[i]);
            if (i == n - 1) {
                log.info(str.toString());
            }
        }

        for (int i = n - 1; i > 0; i--) {
            if (arrInt[i] >= arrInt[i - 1]) {
                for (int j = n - 1; j >= i; j--) {
                    if (arrInt[j] > arrInt[i - 1]) {
                        int temp = arrInt[j];
                        arrInt[j] = arrInt[i - 1];
                        arrInt[i - 1] = temp;
                        break;
                    }
                }

                for (int j = n - 1; j > ((n - 1 + i) / 2); j--) {
                    int temp = arrInt[i + n - 1 - j];
                    arrInt[i + n - 1 - j] = arrInt[j];
                    arrInt[j] = temp;
                }

                str = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    str.append(arrInt[j]);
                    if (j == n - 1) {
                        log.info(str.toString());
                    }
                }
                i = n;
            }
        }
    }
}
