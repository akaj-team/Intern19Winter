package asiantech.internship.summer.javacore.exercise11;

import android.util.Log;

import static asiantech.internship.summer.javacore.InputUtils.inputIntNumber;

public class ListPermutations {

    private static final String TAG = ListPermutations.class.getSimpleName();

    public static void main(String[] args) {
        Log.d(TAG, "Nhap n: ");
        int n = inputIntNumber(TAG);
        doListPermutations(n);
    }

    private static void doListPermutations(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i + 1;
        }
        for (int i = 0; i < n; i++) {
            Log.d(TAG, String.valueOf(a[i]));
        }
        for (int i = n - 1; i > 0; i--) {

            if (a[i] > a[i - 1]) {

                for (int j = n - 1; j >= i; j--) {
                    if (a[j] > a[i - 1]) {
                        int temp = a[j];
                        a[j] = a[i - 1];
                        a[i - 1] = temp;
                        break;
                    }
                }

                for (int j = n - 1; j > ((n - 1 + i) / 2); j--) {
                    int temp = a[i + n - 1 - j];
                    a[i + n - 1 - j] = a[j];
                    a[j] = temp;
                }

                Log.d(TAG, "\n");
                for (int j = 0; j < n; j++) {
                    Log.d(TAG, String.valueOf(a[j]));
                }
                i = n;
            }
        }
    }
}
