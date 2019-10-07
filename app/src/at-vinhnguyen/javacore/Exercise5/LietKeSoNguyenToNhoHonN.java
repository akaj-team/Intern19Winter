package Exercise5;

import java.util.Scanner;

public class LietKeSoNguyenToNhoHonN {

    public static void main(String[] args) {
        System.out.print("Nhap n:");
        int n = new Scanner(System.in).nextInt();
        System.out.print("Cac so nguyen to nho hon n bao gom: " + soNguyenToNhoHon(n));
    }

    private static String soNguyenToNhoHon(int n) {
        StringBuilder builder = new StringBuilder();
        for (int i = 2; i < n; i++) {
            if(kiemTraSoNguyenTo(i)){
                builder.append(i+" ");
            }
        }
        return builder.toString();
    }

    private static boolean kiemTraSoNguyenTo(int i) {
        for (int j = 2; j < i/2; j++) {
            if (i % j == 0) {
                return false;
            }
        }
        return true;
    }
}
