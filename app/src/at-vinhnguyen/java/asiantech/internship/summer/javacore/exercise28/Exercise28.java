package asiantech.internship.summer.javacore.exercise28;

import android.util.Log;
import java.util.Locale;
import java.util.Scanner;

public class Exercise28 {

    private static final String TAG = Exercise28.class.getSimpleName();

    private static String standardized(String str) {
        str = str.trim();
        str = str.replaceAll("\\s+", " ");
        return str;
    }

    private static String standardizedProperNoun(String str) {
        str = standardized(str);
        String[] temp = str.split(" ");
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < temp.length; i++) {
            strBuilder.append(String.valueOf(temp[i].charAt(0)).toUpperCase(Locale.getDefault()))
                    .append(temp[i].substring(1));
            if (i < temp.length - 1) {
                strBuilder.append(" ");
            }
        }
        str = strBuilder.toString();
        return str;
    }

    public static void main(String[] sgr) {
        Log.d(TAG, "Nhap xau ban can chuan hoa: ");
        String str = new Scanner(System.in, "UTF-8").nextLine();
        str = standardizedProperNoun(str);
        Log.d(TAG, str);
    }
}
