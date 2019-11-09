package asiantech.internship.summer.javacore.exercise19;

import android.util.Log;

import static asiantech.internship.summer.javacore.InputUtils.inputIntNumber;

public class ListPrimes {

    private static final String TAG = ListPrimes.class.getSimpleName();

    public static void main(String[] args) {
        Log.d(TAG, "Nhap n");
        int n = inputIntNumber(TAG);
        Log.d(TAG, "Liệt kê tất cả số nguyên tố có 5 chữ số có tổng các chữ số bằng: " + n);
        for (int i = 10001; i < 99999; i += 2) {
            if (isPrimeNumber(i)) {
                if (isSumEqual(n, i)) {
                    Log.d(TAG, String.valueOf(i));
                }
            }
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

    private static boolean isSumEqual(int n, int number) {
        int sum = 0;

        while (number > 0) {
            sum += n % 10;
            number /= 10;
        }
        return sum == n;
    }
}
