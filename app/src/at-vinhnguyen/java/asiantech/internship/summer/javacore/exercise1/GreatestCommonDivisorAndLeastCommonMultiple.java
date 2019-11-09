package asiantech.internship.summer.javacore.exercise1;

import android.util.Log;

import static asiantech.internship.summer.javacore.InputUtils.inputIntNumber;

public class GreatestCommonDivisorAndLeastCommonMultiple {

    private static final String TAG =
            GreatestCommonDivisorAndLeastCommonMultiple.class.getSimpleName();

    public static void main(String[] args) {
        Log.d(TAG, "Nhap vao a:");
        int a = inputIntNumber(TAG);
        Log.d(TAG, "Nhap vao b:");
        int b = inputIntNumber(TAG);
        Log.d(TAG, "Uoc chung lon nhat: \n" + findGreatestCommonDivisor(a, b));
        Log.d(TAG, "Boi chung nho nhat: \n" + findLeastCommonMultiple(a, b));
    }

    private static int findGreatestCommonDivisor(int a, int b) {
        if (a == 0 || b == 0) {
            return a + b;
        }
        while (a != b) {
            if (a > b) {
                a -= b;
            } else {
                b -= a;
            }
        }
        return a;
    }

    private static int findLeastCommonMultiple(int a, int b) {
        int max, bcnn = 1;
        if (a == 0 || b == 0) {
            Log.d(TAG, a + " va " + b + " khong co boi chung nho nhat");
        }
        if (a > b) {
            max = a;
        } else {
            max = b;
        }
        int i = max;
        while (true) {
            if (i % a == 0 && i % b == 0) {
                bcnn = i;
                break;
            }
            i += max;
        }
        return bcnn;
    }
}
