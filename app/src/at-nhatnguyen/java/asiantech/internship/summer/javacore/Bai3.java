package asiantech.internship.summer.javacore;

import java.util.Scanner;

public class Bai3 {
    private static int soDu,tong;
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Nhap day so nguyen:");
        int a = scan.nextInt();
        while (a > 0) {
            soDu = a % 10;
            a = a / 10;
            tong += soDu;
        }
        System.out.println("Tong cac so la:"+tong);
    }
}
