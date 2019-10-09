package asiantech.internship.winter.test;

import java.util.Scanner;
//phan tich thanh thua so nguyen to
public class number4 {
    public static void New(int n) {
        int i = 2;
        while (n > 1) {
            if (n % i == 0) {
                System.out.print(i);
                n /= i;
                if(n > 1) System.out.print(".");
            } else
                i++;
        }
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
                System.out.println("nhap so hay nhap lai");
                input.nextLine();
            }
        }
        return (n);
    }

    public static void main(String[] args) {
        System.out.print("Nhap n");
        int n = Input();
        System.out.print(n + "= ");
        New(n);
    }
}
