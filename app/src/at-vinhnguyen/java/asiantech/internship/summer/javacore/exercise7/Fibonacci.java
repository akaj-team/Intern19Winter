package asiantech.internship.summer.javacore.exercise7;

import android.util.Log;

import static asiantech.internship.summer.javacore.InputUtils.inputIntNumber;

public class Fibonacci {

    private static final String TAG = Fibonacci.class.getSimpleName();

    public static void main(String[] args) {
        int n = inputIntNumber(TAG);
        Log.d(TAG, "So fibonacci thu " + n + " la: " + getFibonacci(n));
    }

    private static int getFibonacci(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return getFibonacci(n - 1) + getFibonacci(n - 2);
    }
}
