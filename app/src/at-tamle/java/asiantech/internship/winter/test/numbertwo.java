package asiantech.internship.winter.test;

import java.util.Scanner;
//  doi co so 10 thanh co so bat ki
public class numbertwo {
    public static void change(int n, int base) {
        if (n >= base)
            change(n / base, base);
        if (n % base > 9)
            System.out.printf("%c", n % base + 55);
        else
            System.out.print((n % base));
    }

    public static int New() {
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

    public static void main(String[] args) {
        System.out.println("Nhap n");
        int n = New();
        System.out.println("Nhap vao co so can chuyen sang b");
        int b = New();
        System.out.println("So " + n + " chuyen sang co so " + b + " thanh: ");
        change(n, b);
    }
}
