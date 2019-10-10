package asiantech.internship.winter.ex6;

import java.util.Scanner;
// n so nguyen to dau tien
public class primefirst {
    private static void process(int n) {
        int i = 2, count = 0;
        while (count < n) {
            if (element(i)) {
                System.out.print(" " + i);
                count++;
            }
            i++;
        }
    }

    private static boolean element(int n) {
        if (n == 2 || n == 3)
            return true;
        if (n == 1 || n % 2 == 0 || n % 3 == 0)
            return false;
        int k = -1;
        do {
            k += 6;
            if (n % k == 0 || n % (k + 2) == 0)
                break;
        } while (k * k < n);// k < sqrt(n);
        return k * k > n;// return k > sqrt(n).
    }

    private static int process() {
        Scanner input = new Scanner(System.in);
        boolean check = false;
        int n = 0;
        while (!check) {
            System.out.println(" ");
            try {
                n = input.nextInt();
                check = true;
            } catch (Exception e) {
                System.out.println("phai nhap so, hay nhap lai");
                input.nextLine();
            }
        }
        return (n);
    }

    public static void main(String[] args) {
        System.out.println("Nhap n");
        int n = process();
        System.out.println(n + " so nguyen to dau tien la: ");
        process(n);
    }
}
