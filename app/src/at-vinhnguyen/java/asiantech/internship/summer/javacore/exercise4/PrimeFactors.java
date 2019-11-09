package asiantech.internship.summer.javacore.exercise4;

import android.util.Log;
import asiantech.internship.summer.javacore.exercise3.SumOfAllNumber;

import static asiantech.internship.summer.javacore.InputUtils.inputIntNumber;
import static asiantech.internship.summer.javacore.exercise21.Exercise21.isPrime;

public class PrimeFactors {

    private static final String TAG = SumOfAllNumber.class.getSimpleName();

    public static void main(String[] args) {
        int n = inputIntNumber(TAG);
        doFactorAnalysis(n);
    }

    private static void doFactorAnalysis(int n) {
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
}
