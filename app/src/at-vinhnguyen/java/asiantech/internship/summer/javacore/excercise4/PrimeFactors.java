package asiantech.internship.summer.javacore.excercise4;

import static asiantech.internship.summer.javacore.excercise21.Excercise21.isPrime;

import java.util.Scanner;

public class PrimeFactors {

    public static void main(String[] args) {
        System.out.println("Nhap n: ");
        int n = new Scanner(System.in).nextInt();
        doFactorAnalysis(n);
    }

    private static void doFactorAnalysis(int n) {
        int i = 2;
        while (n > 1) {
            if (isPrime(i)) {
                if (n % i == 0) {
                    System.out.println(i + ".");
                    n /= i;
                } else {
                    i++;
                }
            } else {
                i++;
            }
        }
    }
}
