package asiantech.internship.summer.javacore;
import java.util.Scanner;
public class Exercise3 {
    private static int surplus, sum;
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Nhap day so nguyen:");
        int a = scan.nextInt();
        while (a > 0) {
            surplus = a % 10;
            a = a / 10;
            sum += surplus;
        }
        System.out.println("Tong cac so la:"+ sum);
    }
}
