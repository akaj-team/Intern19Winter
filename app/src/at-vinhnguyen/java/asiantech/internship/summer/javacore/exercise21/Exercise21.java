package asiantech.internship.summer.javacore.exercise21;

import android.util.Log;

import static asiantech.internship.summer.javacore.InputUtils.inputIntNumber;

public class Exercise21 {

    private static final String TAG = Exercise21.class.getSimpleName();

    public static int sumOfNumber(int n) {
        int T = 0;
        while (n > 0) {
            T += n % 10;
            n /= 10;
        }
        return T;
    }

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

    public static void analysis(int n) {
        int i = 2;
        while (n > 1) {
            if (isPrime(i)) {
                if (n % i == 0) {
                    Log.d(TAG, i + ".");
                    n /= i;
                } else {
                    i++;
                }
            } else {
                i++;
            }
        }
    }

    public static void main(String[] args) {
        Log.d(TAG, "Nhap n");
        int n = inputIntNumber(TAG);
        Log.d(TAG, "n = 1.");
        analysis(n);
        Log.d(TAG, "Tong cac chu so cua " + n + " la: " + sumOfNumber(n));
    }
}
