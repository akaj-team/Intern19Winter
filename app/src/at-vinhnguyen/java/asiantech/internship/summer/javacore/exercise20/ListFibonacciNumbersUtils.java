package asiantech.internship.summer.javacore.exercise20;

import android.util.Log;

import static asiantech.internship.summer.javacore.InputUtils.inputIntNumber;

public class ListFibonacciNumbersUtils {

    private static final String TAG = ListFibonacciNumbersUtils.class.getSimpleName();

    public static void main(String[] args) {
        int n = inputIntNumber(TAG);
        Log.d(TAG, "Các số fibonacci nhỏ hơn " + n + " và " + "là số nguyên tố: ");
        int i = 0;
        while (fibonacci(i) < n) {
            int fi = fibonacci(i);
            if (isPrimeNumber(fi)) {
                Log.d(TAG, fi + " ");
            }
            i++;
        }
    }

    private static int fibonacci(int n) {
        if (n < 0) {
            return -1;
        } else if (n == 0 || n == 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    private static boolean isPrimeNumber(int n) {
        if (n < 2) {
            return false;
        }
        int squareRoot = (int) Math.sqrt(n);
        for (int i = 2; i <= squareRoot; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
