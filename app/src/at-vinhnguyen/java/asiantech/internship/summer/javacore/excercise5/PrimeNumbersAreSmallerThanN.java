package asiantech.internship.summer.javacore.excercise5;

import java.util.Scanner;

public class PrimeNumbersAreSmallerThanN {

    public static void main(String[] args) {
        System.out.println("Nhap n:");
        int n = new Scanner(System.in).nextInt();
        System.out.println("Cac so nguyen to nho hon n bao gom: " + findPrimeNumbersAreSmallerThanN(n));
    }

    private static String findPrimeNumbersAreSmallerThanN(int n) {
        StringBuilder builder = new StringBuilder();
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) {
                builder.append(i).append(" ");
            }
        }
        return builder.toString();
    }

    private static boolean isPrime(int i) {
        for (int j = 2; j < i / 2; j++) {
            if (i % j == 0) {
                return false;
            }
        }
        return true;
    }
}
