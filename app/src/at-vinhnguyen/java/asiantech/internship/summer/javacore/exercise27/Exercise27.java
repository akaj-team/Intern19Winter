package asiantech.internship.summer.javacore.exercise27;

import android.util.Log;
import java.util.Arrays;

import static asiantech.internship.summer.javacore.InputUtils.inputIntNumber;

public class Exercise27 {

    private static final String TAG = Exercise27.class.getSimpleName();

    private static int maxPosition(int[] a, int n) {
        int max = a[0];
        int key = 0;
        for (int j = 0; j < n; j++) {
            if (max < a[j]) {
                max = a[j];
                key = j;
            }
        }
        return (key);
    }

    private static void inArray(int[] a, int begin, int end) {
        Log.d(TAG, "\n");
        int i;
        for (i = begin; i < end; i++) {
            Log.d(TAG, " " + a[i]);
        }
        Log.d(TAG, "\n");
    }

    private static int maxPositionAfter(int[] a, int n) {
        int i, key = 0, Max2 = 0;
        for (i = 0; i < n; i++) {
            if (a[i] > Max2 && a[i] != a[maxPosition(a, n)]) {
                Max2 = a[i];
                key = i;
            }
        }
        return (key);
    }

    private static void addElement(int[] a, int pt) {
        a[0] = pt;
        Arrays.sort(a);
    }

    public static void main(String[] args) {
        Log.d(TAG, "Nhap n= ");
        int n = inputIntNumber(TAG);
        int[] a = new int[n + 1];
        int i;
        for (i = 0; i < n; i++) {
            Log.d(TAG, "\n Nhap phan tu thu " + i + " = ");
            a[i] = inputIntNumber(TAG);
        }
        for (i = 0; i < n; i++) {
            if (a[i] == a[maxPositionAfter(a, n)]) {
                Log.d(TAG, " Phan tu thu " + i + " lon thu 2 trong mang a[" + i + "]= " + a[i]);
            }
        }
        Arrays.sort(a);
        inArray(a, 1, n + 1);
        Log.d(TAG, "Nhap phan tu muon them pt= ");
        int pt = inputIntNumber(TAG);
        addElement(a, pt);
        inArray(a, 0, n);
    }
}
