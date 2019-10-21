package asiantech.internship.summer.javacore.exercise2;

import android.util.Log;
import java.util.Scanner;

public class BaseNumber {

    private static final String TAG = BaseNumber.class.getSimpleName();

    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in, "UTF-8");
        Log.d(TAG, "Nhap he co so ban can chuyen: ");
        int base = Integer.parseInt(sn.nextLine());
        Log.d(TAG, "Nhap so o he thap phan ban can chuyen: ");
        int number = Integer.parseInt(sn.nextLine());
        Log.d(TAG, "So sau khi chuyen: " + convertBaseNumber(base, number));
    }

    private static int convertBaseNumber(int baseNumber, int number) {
        int i = 1;
        int result = 0;
        while (number > 1) {
            result += number % baseNumber * i;
            number /= baseNumber;
            i *= 10;
        }
        return result;
    }
}
