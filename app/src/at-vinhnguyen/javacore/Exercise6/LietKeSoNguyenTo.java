package Exercise6;

import java.util.Scanner;

public class LietKeSoNguyenTo {

    public static void main(String[] args) {
        System.out.print("Nhap so luong so nguyen to: ");
        int n = new Scanner(System.in).nextInt();
        System.out.println(inSoNguyenTo(n));
    }

    private static String inSoNguyenTo(int n) {
        StringBuilder builder = new StringBuilder();
        int dem = 0;
        int i = 2;
        while (dem < n) {
            if (kiemTraSoNguyenTo(i)) {
                dem++;
                builder.append(" " + i);
            }
            i++;
        }
        return builder.toString();
    }

    private static boolean kiemTraSoNguyenTo(int i) {
        for (int j = 2; j < i; j++) {
            if (i % j == 0) {
                return false;
            }
        }
        return true;
    }
}
