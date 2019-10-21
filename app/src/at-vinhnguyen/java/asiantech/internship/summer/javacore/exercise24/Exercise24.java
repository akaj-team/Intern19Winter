package asiantech.internship.summer.javacore.exercise24;

import android.util.Log;

public class Exercise24 {

    private static final String TAG = Exercise24.class.getSimpleName();

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

    public static boolean isReversibleNumber(int n) {
        StringBuilder xau = new StringBuilder();
        String str = "" + n;
        xau.append(str);
        String check = "" + xau.reverse();
        return str.equals(check);
    }

    public static boolean isEachDigitIsAPrimeNumber(int n) {
        while (n != 0) {
            if (!isPrime(n % 10)) {
                return false;
            }
            n /= 10;
        }
        return true;
    }

    public static void main(String[] args) {
        int i, count = 0;
        Log.d(TAG, "cac so tu 5-7 chu so thoa man dieu kien la: ");
        for (i = 10001; i < 10000000; i += 2) {
            if (isPrime(i) && isEachDigitIsAPrimeNumber(i) && isReversibleNumber(i)) {
                Log.d(TAG, String.valueOf(i));
                count++;
            }
        }
        Log.d(TAG, "\n Co " + count + " so thoa man");
    }
}
