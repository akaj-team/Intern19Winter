package asiantech.internship.summer.javacore.excercise6;

import java.util.Scanner;

public class PrimesLessThanN {

    public static void main(String[] args) {
        System.out.println("Nhap so luong so nguyen to: ");
        int n = Integer.parseInt(new Scanner(System.in).nextLine());
        System.out.println(showPrimes(n));
    }

    private static String showPrimes(int n) {
        StringBuilder builder = new StringBuilder();
        int count = 0;
        for (int j = 2; count < n; j+=2) {
            if(isPrime(j)){
                count++;
                builder.append(" ").append(j);
            }
        }
        return builder.toString();
    }

    private static boolean isPrime(int i) {
        for (int j = 2; j < i; j++) {
            if (i % j == 0) {
                return false;
            }
        }
        return true;
    }
}
