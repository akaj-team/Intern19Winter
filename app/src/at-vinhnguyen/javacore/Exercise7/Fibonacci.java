
package Exercise7;

import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        System.out.print("Nhap n:");
        int n = new Scanner(System.in).nextInt();
        System.out.println("So fibonacci thu " + n + " la: "+soFiBonacci(n));
    }

    private static int soFiBonacci(int n) {
        if(n==0 || n==1){
            return n;
        }
        return soFiBonacci(n-1) + soFiBonacci(n-2);
    }
}
