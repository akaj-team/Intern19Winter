package asiantech.internship.summer.javacore.exercise5;

import android.util.Log;
import asiantech.internship.summer.javacore.exercise3.SumOfAllNumber;

import static asiantech.internship.summer.javacore.InputUtils.inputIntNumber;

public class PrimeNumbersAreSmallerThanN {
    private static final String TAG = SumOfAllNumber.class.getSimpleName();

    public static void main(String[] args) {
        int n = inputIntNumber(TAG);
        Log.d(TAG, "Cac so nguyen to nho hon n bao gom: " + findPrimeNumbersAreSmallerThanN(n));
    }

    private static String findPrimeNumbersAreSmallerThanN(int n) {
        StringBuilder builder = new StringBuilder();
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) {
                builder.append(i).append(" ");
            }
        }
        return builder.toString();
    }

    private static boolean isPrime(int i) {
        for (int j = 2; j < i / 2; j++) {
            if (i % j == 0) {
                return false;
            }
        }
        return true;
    }
}
