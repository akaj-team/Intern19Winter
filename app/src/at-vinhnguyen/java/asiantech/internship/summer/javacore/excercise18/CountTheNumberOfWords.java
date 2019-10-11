package asiantech.internship.summer.javacore.excercise18;

import java.util.Scanner;

public class CountTheNumberOfWords {

    public static final char SPACE = ' ';
    public static final char TAB = '\t';
    public static final char BREAK_LINE = '\n';

    public static void main(String[] args) {
        System.out.print("Nhap chuoi can tim: ");
        String str = new Scanner(System.in).nextLine();
        System.out.print("Số từ của chuỗi đã cho là: "
                + countWords(str) + "\n");
    }

    public static int countWords(String input) {
        if (input == null) {
            return -1;
        }
        int count = 0;
        int size = input.length();
        boolean notCounted = true;
        for (int i = 0; i < size; i++) {
            if (input.charAt(i) != SPACE && input.charAt(i) != TAB
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
