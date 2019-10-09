package asiantech.internship.winter.test;

import java.util.Scanner;
//  n so nguyen to dau tien
public class number6 {
    public static void New(int n) {
        int i = 2, count = 0;
        while (count < n) {
            if (element(i)) {
                System.out.print(" " + i);
                count++;
            }
            i++;
        }
    }

    public static boolean element(int n) {
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

    public static int Input() {
        Scanner input = new Scanner(System.in);
        boolean check = false;
        int n = 0;
        while (!check) {
            System.out.print(" ");
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
        System.out.print("Nhap n");
        int n = Input();
        System.out.println(n + " so nguyen to dau tien la: ");
        New(n);
    }
}
