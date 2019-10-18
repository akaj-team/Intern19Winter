package asiantech.internship.winter.ex1;
import java.util.Scanner;

public class  Common {
    public static int process() {
        Scanner input = new Scanner(System.in);
        boolean check = false;
        int n = 0;
        while (!check) {
            System.out.println(" ");
            try {
                n = input.nextInt();
                check = true;
            } catch (Exception e) {
                System.out.println("Ban phai nhap so! hay nhap lai...");
                input.nextLine();
            }
        }
        return n;
    }
    private static int comConvention(int a, int b) {
        while (a != b) {
            if (a > b)
                a = a - b;
            else
                b = b - a;
        }
        return a;
    }
    public static void main(String[] args) {
        System.out.println("Nhap a");
        int a = process();
        System.out.println("Nhap b");
        int b = process();
        System.out.println("Uoc chung lon nhat cua " + a + " va " + b + " la: " + comConvention(a, b));
        System.out.println("Boi chung nho nhat cua " + a + " va " + b + " la: " + ((a * b) / comConvention(a, b)));
    }
}
