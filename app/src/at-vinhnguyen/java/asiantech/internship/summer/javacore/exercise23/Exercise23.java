package asiantech.internship.summer.javacore.exercise23;

import android.util.Log;

import static asiantech.internship.summer.javacore.InputUtils.inputIntNumber;

public class Exercise23 {

    private static final String TAG = Exercise23.class.getSimpleName();

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

    public static void listPrimeNumbers(int n) {
        int i = 1, count = 0;
        Log.d(TAG, "Cac so nguyen to nho hon " + n + " la: ");
        while (i < n) {
            if (isPrime(i)) {
                Log.d(TAG, String.valueOf(i));
                count++;
            }
            i++;
        }
        Log.d(TAG, "\n Co " + count + " so thoa man");
    }

    public static void main(String[] args) {
        int n = inputIntNumber(TAG);
        listPrimeNumbers(n);
        int[] f = new int[n];
        f[0] = 1;
        f[1] = 1;
        int i = 1;
        Log.d(TAG, "Cac so Fibonanci nho hon " + n + " la : \n 1");
        while (f[i] < n) {
            Log.d(TAG, String.valueOf(f[i]));
            i++;
            f[i] = f[i - 1] + f[i - 2];
        }
        Log.d(TAG, "\n Co " + i + " so thoa man");
    }
}
