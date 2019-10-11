package asiantech.internship.summer.javacore.excercise7;

import java.util.Scanner;

public class Fibonacci {

    public static void main(String[] args) {
        System.out.print("Nhap n:");
        int n = new Scanner(System.in).nextInt();
        System.out.println("So fibonacci thu " + n + " la: " + getFibonacci(n));
    }

    private static int getFibonacci(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return getFibonacci(n - 1) + getFibonacci(n - 2);
    }
}
