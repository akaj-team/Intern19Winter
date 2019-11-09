package asiantech.internship.summer.javacore.exercise8;

import android.util.Log;
import java.util.Scanner;

public class Reversible {

    private static final String TAG = Reversible.class.getSimpleName();

    public static void main(String[] args) {
        Log.d(TAG, "Nhap chuoi: ");
        String str = new Scanner(System.in, "UTF-8").nextLine();
        if (checkString(str)) {
            Log.d(TAG, "Chuoi thuan nghich doc");
        } else {
            Log.d(TAG, "Khong phai chuoi thuan nghich doc");
        }
    }

    private static boolean checkString(String str) {
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
