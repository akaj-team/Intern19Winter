package asiantech.internship.summer.java_core.ex10;

import java.util.logging.Logger;

import asiantech.internship.summer.java_core.common.Common;

public class Subset {

    public static void main(String[] args) {
        int n = Common.input("Nhập n: ");
        int k = Common.input("Nhập k: ");

        int[] arr = new int[k];
        backTracking(n, k, arr, 0, 0);
    }

    private static void backTracking(int n, int k, int[] arr, int i, int j) {
        Logger log = Logger.getLogger(Subset.class.getName());
        for (j = 0; j < n - k + i + 1; j++) {
            arr[i] = j + 1;
            if (i == (k - 1)) {
                StringBuilder str = new StringBuilder();
                for (int temp = 0; temp < k; temp++) {
                    str.append(arr[temp]);
                    if (temp == k - 1) {
                        log.info(str.toString());
                    }
                }
            } else {
                backTracking(n, k, arr, i + 1, j + 1);
            }
        }
    }
}
