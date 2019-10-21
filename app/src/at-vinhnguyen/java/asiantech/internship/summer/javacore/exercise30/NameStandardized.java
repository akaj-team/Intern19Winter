package asiantech.internship.summer.javacore.exercise30;

import android.util.Log;
import java.util.Scanner;

public class NameStandardized {

    private static final String TAG = NameStandardized.class.getSimpleName();

    public static void main(String[] args) {
        Log.d(TAG, "Nhap ten cua ban: ");
        String fullName = new Scanner(System.in, "UTF-8").nextLine();
        int lastNamePosition = fullName.lastIndexOf(' ') + 1;
        Log.d(TAG,
                "Your name: " + fullName.substring(lastNamePosition) + " " + fullName.substring(0,
                        lastNamePosition - 1));
    }
}
