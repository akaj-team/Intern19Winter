package asiantech.internship.summer.javacore.exercise9;

import android.util.Log;

import static asiantech.internship.summer.javacore.InputUtils.inputIntNumber;

public class ListsBinaryStrings {

    private static final String TAG = ListsBinaryStrings.class.getSimpleName();

    public static void main(String[] args) {
        Log.d(TAG, "Nhap n: ");
        int n = inputIntNumber(TAG);
        doListsBinaryStrings(n);
    }

    private static void doListsBinaryStrings(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = 0;
        }
        for (int value : a) {
            Log.d(TAG, String.valueOf(value));
        }
        for (int i = n - 1; i >= 0; i--) {
            if (a[i] == 0) {
                a[i] = 1;
                for (int j = i + 1; j < n; j++) {
                    a[j] = 0;
                }
                for (int j = 0; j < n; j++) {
                    Log.d(TAG, String.valueOf(a[j]));
                }
                Log.d(TAG, "\n");
            }
            i = n;
        }
    }
}
