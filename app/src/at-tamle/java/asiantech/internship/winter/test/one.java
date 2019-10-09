package asiantech.internship.winter.test;

import java.util.Scanner;
//ucln bcnn
public class one {
    public static int number() {
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
    public static int UCLN(int a, int b) {
        while (a != b) {
            if (a > b)
                a = a - b;
            else
                b = b - a;
        }
        return (a);
    }
    public static void main(String[] args) {
        System.out.println("Nhap a");
        int a = number();
        System.out.println("Nhap b");
        int b = number();
        System.out.println("Uoc chung lon nhat cua " + a + " va " + b + " la: " + UCLN(a, b));
        System.out.println("Boi chung nho nhat cua " + a + " va " + b + " la: " + ((a * b) / UCLN(a, b)));
    }
}
