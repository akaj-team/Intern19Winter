package asiantech.internship.summer.javacore.excercise2;

import java.util.Scanner;

public class BaseNumber {

    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        System.out.println("Nhap he co so ban can chuyen: ");
        int coSo = sn.nextInt();
        System.out.println("Nhap so o he thap phan ban can chuyen: ");
        int so = sn.nextInt();
        System.out.println("So sau khi chuyen: " + convertBaseNumber(coSo, so));
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
