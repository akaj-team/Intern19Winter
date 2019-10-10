package asiantech.internship.summer.java_core.ex17;

import java.util.Arrays;
import java.util.logging.Logger;

import asiantech.internship.summer.java_core.common.Common;

public class ArrayUp {

    public static void main(String[] args) {
        Logger log = Logger.getLogger(ArrayUp.class.getName());
        int n = Common.input("Nhập số lượng phần tử:");
        int[] arrInt = new int[n];
        for (int i = 0; i < n; i++) {
            arrInt[i] = Common.input("Nhập phần tử thứ " + i);
        }

        Arrays.sort(arrInt);
        for (int i : arrInt) {
            log.info(String.valueOf(i));
        }

    }

}
