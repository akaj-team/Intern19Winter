package asiantech.internship.summer.javacore.exercise18;

import android.util.Log;
import java.util.Scanner;

public class CountTheNumberOfWords {

    private static final char SPACE = ' ';
    private static final char TAB = '\t';
    private static final char BREAK_LINE = '\n';
    private static final String TAG = CountTheNumberOfWords.class.getSimpleName();

    public static void main(String[] args) {
        Log.d(TAG, "Nhap chuoi can tim: ");
        String str = new Scanner(System.in, "UTF-8").nextLine();
        Log.d(TAG, "Số từ của chuỗi đã cho là:" + countWords(str) + "\n ");
    }

    private static int countWords(String input) {
        if (input == null) {
            return -1;
        }
        int count = 0;
        int size = input.length();
        boolean notCounted = true;
        for (int i = 0; i < size; i++) {
            if (input.charAt(i) != SPACE
                    && input.charAt(i) != TAB
                    && input.charAt(i) != BREAK_LINE) {
                if (notCounted) {
                    count++;
                    notCounted = false;
                }
            } else {
                notCounted = true;
            }
        }
        return count;
    }
}
