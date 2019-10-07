package Exercise2;

import java.util.Scanner;

public class ChuyenHeCoSo {

    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        System.out.print("Nhap he co so ban can chuyen: ");
        int coSo = sn.nextInt();
        System.out.print("Nhap so o he thap phan ban can chuyen: ");
        int so = sn.nextInt();
        System.out.println("So sau khi chuyen: " + chuyenHeCoSo(coSo, so));
    }

    private static int chuyenHeCoSo(int baseNumber, int number) {
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
