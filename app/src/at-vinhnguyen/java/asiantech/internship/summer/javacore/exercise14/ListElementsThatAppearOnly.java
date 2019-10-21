package asiantech.internship.summer.javacore.exercise14;

import android.util.Log;

import static asiantech.internship.summer.javacore.InputUtils.inputIntNumber;

public class ListElementsThatAppearOnly {

    private static final String TAG = ListElementsThatAppearOnly.class.getSimpleName();

    private static int countElement(int a[], int n, int i) {
        int count = 0;
        for (int j = 0; j < n; j++) {
            if (a[j] == i) {
                count++;
            }
        }
        return (count);
    }

    public static void main(String[] args) {
        int n, i;
        Log.d(TAG, "Nhap n= ");
        n = inputIntNumber(TAG);
        int[] array = new int[n];
        for (i = 0; i < n; i++) {
            Log.d(TAG, "Nhap phan tu thu " + (i + 1) + " ");
            array[i] = inputIntNumber(TAG);
        }
        Log.d(TAG, "Cac phan tu trong day xuat hien 1 lan: ");
        for (i = 0; i < n; i++) {
            if (countElement(array, n, array[i]) == 1) {
                Log.d(TAG, String.valueOf(array[i]));
            }
        }
    }
}
