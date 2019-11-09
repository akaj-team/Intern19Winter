package asiantech.internship.summer.javacore.exercise6;

import android.util.Log;
import asiantech.internship.summer.javacore.exercise3.SumOfAllNumber;

import static asiantech.internship.summer.javacore.InputUtils.inputIntNumber;

public class PrimesLessThanN {
    private static final String TAG = SumOfAllNumber.class.getSimpleName();

    public static void main(String[] args) {
        Log.d(TAG, "Nhap so luong so nguyen to: ");
        int n = inputIntNumber(TAG);
        Log.d(TAG, showPrimes(n));
    }

    private static String showPrimes(int n) {
        StringBuilder builder = new StringBuilder();
        int count = 0;
        for (int j = 2; count < n; j += 2) {
            if (isPrime(j)) {
                count++;
                builder.append(" ").append(j);
            }
        }
        return builder.toString();
    }

    private static boolean isPrime(int i) {
        for (int j = 2; j < i; j++) {
            if (i % j == 0) {
                return false;
            }
        }
        return true;
    }
}
