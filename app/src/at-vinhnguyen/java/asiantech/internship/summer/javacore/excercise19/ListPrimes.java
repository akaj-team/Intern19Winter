package asiantech.internship.summer.javacore.excercise19;

import java.util.Scanner;

public class ListPrimes {

    public static void main(String[] args) {
        System.out.print("Nhap n");
        int n = new Scanner(System.in).nextInt();
        System.out.println("Liệt kê tất cả số nguyên tố có 5 chữ số có tổng các chữ số bằng: " + n);
        for (int i = 10001; i < 99999; i += 2) {
            if (isPrimeNumber(i)) {
                if (isSumEqual(n, i)) {
                    System.out.println(i);
                }
            }
        }
    }

    public static boolean isPrimeNumber(int n) {
        if (n < 2) {
            return false;
        }
        int squareRoot = (int) Math.sqrt(n);
        for (int i = 2; i <= squareRoot; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSumEqual(int n, int number) {
        int sum = 0;

        while (number > 0) {
            sum += n % 10;
            number /= 10;
        }
        if (sum == n) {
            return true;
        }
        return false;
    }
}
