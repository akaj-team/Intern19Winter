package asiantech.internship.summer.javacore.excercise22;

import java.util.Scanner;

public class Excersice22 {

    public static int input() {
        Scanner input = new Scanner(System.in);
        boolean check = false;
        int n = 0;
        while (!check) {
            System.out.print(" ");
            try {
                n = input.nextInt();
                check = true;

            } catch (Exception e) {
                System.out.println("Ban phai nhap so! hay nhap lai...");
                input.nextLine();

            }

        }
        return (n);
    }

    public static boolean checkSNT(int n) {
        if (n > 1) {
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public static void doListDivisor(int n) {
        int count = 0;
        System.out.print("\nCac uoc cua " + n + " la:");
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                System.out.print(" " + i);
                count++;
            }
        }
        System.out.println("\nCo " + count + " uoc");
    }

    public static void doListDivisorOfPrimeNumber(int n) {
        int count = 0;
        System.out.print("\nCac uoc cua " + n + " la:");
        for (int i = 1; i <= n; i++) {
            if (n % i == 0 && (checkSNT(i))) {
                System.out.print(" " + i);
                count++;
            }
        }
        System.out.println("\nCo " + count + " uoc la so nguyen to");

    }

    public static void main(String[] args) {
        System.out.print("Nhap n");
        int n = input();
        doListDivisor(n);
        doListDivisorOfPrimeNumber(n);
    }

}
