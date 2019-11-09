package asiantech.internship.summer.javacore;

import android.util.Log;
import java.util.Scanner;

public class InputUtils {

    public static int inputIntNumber(String TAG) {
        Scanner input = new Scanner(System.in, "UTF-8");
        boolean check = false;
        int n = 0;
        while (!check) {
            Log.d(TAG, "Nhap so: ");
            try {
                n = Integer.parseInt(input.nextLine());
                check = true;
            } catch (Exception e) {
                Log.d(TAG, "Ban phai nhap so! hay nhap lai...");
            }
        }
        return (n);
    }
}
