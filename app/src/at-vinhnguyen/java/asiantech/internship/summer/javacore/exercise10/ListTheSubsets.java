package asiantech.internship.summer.javacore.exercise10;

import android.util.Log;

import static asiantech.internship.summer.javacore.InputUtils.inputIntNumber;

public class ListTheSubsets {

    private static final String TAG = ListTheSubsets.class.getSimpleName();

    public static void main(String[] args) {
        Log.d(TAG, "Nhap n: ");
        int n = inputIntNumber(TAG);
        Log.d(TAG, "Nhap k: ");
        int k = inputIntNumber(TAG);
        doListTheSubsets(n, k);
    }

    private static void doListTheSubsets(int n, int k) {
        int[] a = new int[k];
        for (int i = 0; i < k; i++) {
            a[i] = i + 1;
            Log.d(TAG, String.valueOf(a[i]));
        }
        Log.d(TAG, "\n");
        for (int i = k - 1; i >= 0; i--) {
            if (a[i] < n - k + i + 1) {
                a[i]++;
                int j;
                for (j = i + 1; j < k; j++) {
                    a[j] = a[j - 1] + 1;
                }
                for (j = 0; j < k; j++) {
                    Log.d(TAG, String.valueOf(a[j]));
                }
                Log.d(TAG, "\n");
                i = k;
            }
        }
    }
}
