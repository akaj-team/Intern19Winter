package Excercise29;

import java.util.Scanner;

public class TimTuDaiNhat {

    public static void main(String[] args) {
        System.out.print("Nhap tu ban can tim:");
        String str = new Scanner(System.in).nextLine();
        timTuDaiNhat(str);
    }

    private static void timTuDaiNhat(String str) {
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
        System.out.println("Ky tu co do dai lon nhat la: " + str.substring(positionTemp, positionTemp+length));
    }
}
