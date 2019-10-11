package asiantech.internship.summer.javacore.excercise21;

import java.util.Scanner;

public class Excercise21 {

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

    public static int sumOfNumber(int n) {
        int T = 0;
        while (n > 0) {
            T += n % 10;
            n /= 10;
        }
        return T;
    }

    public static boolean isPrime(int n) {
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

    public static void analysis(int n) {
        int i = 2;
        while (n > 1) {
            if (isPrime(i)) {
                if (n % i == 0) {
                    System.out.print(i + ".");
                    n /= i;
                } else {
                    i++;
                }
            } else {
                i++;
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Nhap n");
        int n = input();
        System.out.print("n = 1.");
        analysis(n);
        System.out.println("Tong cac chu so cua " + n + " la: " + sumOfNumber(n));
    }
}
