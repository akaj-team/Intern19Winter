package asiantech.internship.summer.javacore;

import java.util.Scanner;

public class Bai5 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Nhập n = ");
        int n = scan.nextInt();
        System.out.printf("Tất cả các số nguyên tố nhỏ hơn %d là: \n",n);
        if (n >= 2) {
            System.out.print(2);
        }
        for (int i = 3; i < n; i+=2) {
            if (PrimeNumber(i)) {
                System.out.print(" " + i);
            }
        }
    }
    private  static boolean PrimeNumber(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i <= n-1; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
