package asiantech.internship.summer.javacore.exercise3;

import android.util.Log;
import java.util.Scanner;

public class SumOfAllNumber {

    private static final String TAG = SumOfAllNumber.class.getSimpleName();

    public static void main(String[] args) {
        Log.d(TAG, "Nhap so: ");
        Scanner sn = new Scanner(System.in, "UTF-8");
        int a = Integer.parseInt(sn.nextLine());
        Log.d(TAG, "Tong cac chu so: " + totalAllNumbers(a));
    }

    private static int totalAllNumbers(int a) {
        int totalAllNumbers = 0;
        while (a > 0) {
            totalAllNumbers += a % 10;
            a /= 10;
        }
        return totalAllNumbers;
    }
}
