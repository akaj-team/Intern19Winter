package asiantech.internship.summer.javacore;
import java.util.Scanner;
public class Exercise6 {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.print("Nhap n = ");
        int n = scan.nextInt();
        int dem = 0;
        int i =2;
        System.out.printf("%d so nguyen to da tien la: \n", n);
        while (dem < n){
            if (isPrimeNumber(i)){
                System.out.print(i + " ");
                dem++;
            }
            i++;
        }
    }
    //điều kiện là 1 số nguyên tố
    private static boolean isPrimeNumber(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i < n - 1; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
