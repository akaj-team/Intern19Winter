package Exercise3;

import java.util.Scanner;

public class TongCacChuSo {

    public static void main(String[] args) {
        System.out.print("Nhap so: ");
        Scanner sn = new Scanner(System.in);
        int a = sn.nextInt();
        System.out.println("Tong cac chu so: " + totalAllNumbers(a));
    }

    private static int totalAllNumbers(int a) {
        int totalAllNumbers = 0;
        while (a > 0) {
            totalAllNumbers += a % 10;
            a /= 10;
        }
        return totalAllNumbers;
    }
}
