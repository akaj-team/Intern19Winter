package asiantech.internship.summer.javacore.exercise17;

import android.util.Log;
import java.util.Scanner;

import static asiantech.internship.summer.javacore.InputUtils.inputIntNumber;

public class Exersice17 {

    private static final String TAG = Exersice17.class.getSimpleName();

    private static float inputFloatNumber() {
        Scanner input = new Scanner(System.in, "UTF-8");
        boolean check = false;
        float n = 0;
        while (!check) {
            Log.d(TAG, "\n");
            try {
                n = Float.parseFloat(input.nextLine());
                check = true;
            } catch (Exception e) {
                Log.d(TAG, "Ban phai nhap so! hay nhap lai...");
            }
        }
        return (n);
    }

    private static int minFloatPosition(float a[], int n) {
        float min = a[0];
        int key = 0;
        for (int j = 0; j < n; j++) {
            if (min > a[j]) {
                min = a[j];
                key = j;
            }
        }
        return (key);
    }

    private static float maxFloat(float a[], int n) {
        float max = a[0];
        for (int j = 0; j < n; j++) {
            if (max < a[j]) {
                max = a[j];
            }
        }
        return (max);
    }

    public static void main(String[] args) {
        int n, i;
        n = inputIntNumber(TAG);
        float[] array = new float[n];
        for (i = 0; i < n; i++) {
            Log.d(TAG, "Nhap phan tu thu " + (i + 1) + " ");
            array[i] = inputFloatNumber();
        }
        i = 0;
        Log.d(TAG, "Sap xep theo thu tu tang dan");
        while (i < n) {
            Log.d(TAG, String.valueOf(array[minFloatPosition(array, n)]));
            array[minFloatPosition(array, n)] = maxFloat(array, n);
            i++;
        }
    }
}
