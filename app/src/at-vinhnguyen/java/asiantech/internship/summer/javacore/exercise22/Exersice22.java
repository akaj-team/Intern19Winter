package asiantech.internship.summer.javacore.exercise22;

import android.util.Log;

import static asiantech.internship.summer.javacore.InputUtils.inputIntNumber;

public class Exersice22 {

    private static final String TAG = Exersice22.class.getSimpleName();

    public static boolean isPrime(int n) {
        if (n > 1) {
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public static void doListDivisor(int n) {
        int count = 0;
        Log.d(TAG, "\nCac uoc cua " + n + " la:");
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                Log.d(TAG, " " + i);
                count++;
            }
        }
        Log.d(TAG, "\nCo " + count + " uoc");
    }

    public static void doListDivisorOfPrimeNumber(int n) {
        int count = 0;
        Log.d(TAG, "\nCac uoc cua " + n + " la:");
        for (int i = 1; i <= n; i++) {
            if (n % i == 0 && (isPrime(i))) {
                Log.d(TAG, " " + i);
                count++;
            }
        }
        Log.d(TAG, "\nCo " + count + " uoc la so nguyen to");
    }

    public static void main(String[] args) {
        Log.d(TAG, "Nhap n");
        int n = inputIntNumber(TAG);
        doListDivisor(n);
        doListDivisorOfPrimeNumber(n);
    }
}
