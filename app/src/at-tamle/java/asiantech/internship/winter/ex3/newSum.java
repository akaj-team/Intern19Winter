package asiantech.internship.winter.ex3;

import java.util.Scanner;
// tinh tong so bat ki vd 123= 1+2+3
public class newSum {
    public static int  process() {
        Scanner input = new Scanner(System.in);
        boolean check = false;
        int n = 0;
        while (!check) {
            System.out.println(" ");
            try {
                n = input.nextInt();
                check = true;
            } catch (Exception e) {
                System.out.println("hay nhap lai");
                input.nextLine();
            }
        }
        return (n);
    }

    public static int total(long i) {
        int sum = 0;
        long n;
        while (i != 0) {
            n = i % 10;
            sum += n;
            i /= 10;
        }
        return (sum);
    }

    public static void main(String[] args) {
        System.out.println("Nhap n");
        int n = process();
        System.out.println("Tong cua so " + n + " = " + total(n));
    }
}
