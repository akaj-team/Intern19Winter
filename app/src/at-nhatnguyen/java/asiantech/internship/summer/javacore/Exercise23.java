package asiantech.internship.summer.javacore;
import java.util.Scanner;
public class Exercise23 {
    public static int nhap() {
        Scanner scan = new Scanner(System.in);
        boolean check = false;
        int n = 0;
        while (!check) {
            System.out.print(" ");
            try {
                n = scan.nextInt();
                check = true;

            } catch (Exception e) {
                System.out.println("Ban phai nhap so ");
                scan.nextLine();

            }

        }
        return (n);

    }

    private static boolean isPrimeNumber(int n) {
        if (n > 1) {
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) return false;

            }
            return true;

        } else return false;
    }

    private static void primeNumber(int n) {
        int i = 1, count = 0;
        System.out.println("Cac so nguyen to nho hon " + n + " la: ");
        while (i < n) {
            if (isPrimeNumber(i)) {
                System.out.print(" " + i);
                count++;

            }
            i++;

        }
        System.out.println("\n Co " + count + " so thoa man");

    }

    public static void main(String[] args) {
        System.out.print("Nhap n");
        int n = nhap();
        primeNumber(n);
        int[] f = new int[n];
        f[0] = 1;
        f[1] = 1;
        int i = 1;
        System.out.print("Cac so Fibonanci nho hon " + n + " la : \n 1");
        while (f[i] < n) {
            System.out.print(" " + f[i]);
            i++;
            f[i] = f[i - 1] + f[i - 2];

        }
        System.out.println("\n Co " + i + " so thoa man");

    }

}

