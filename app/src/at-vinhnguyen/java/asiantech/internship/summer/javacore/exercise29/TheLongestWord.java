package asiantech.internship.summer.javacore.exercise29;

import android.util.Log;
import java.util.Scanner;

public class TheLongestWord {

    private static final String TAG = TheLongestWord.class.getSimpleName();

    public static void main(String[] args) {
        Log.d(TAG, "Nhap tu ban can tim:");
        String str = new Scanner(System.in, "UTF-8").nextLine();
        findTheLongestWord(str);
    }

    private static void findTheLongestWord(String str) {
        int position = 0, positionTemp = 0;
        int length = 0;
        while (position <= str.length()) {
            int lengthTemp = 0;
            for (int i = position; i != str.length() && str.charAt(i) != ' '; i++) {
                lengthTemp++;
            }
            if (lengthTemp > length) {
                length = lengthTemp;
                positionTemp = position;
            }
            position += lengthTemp + 1;
        }
        Log.d(TAG, "Ky tu co do dai lon nhat la: " + str.substring(positionTemp,
                positionTemp + length));
    }
}
