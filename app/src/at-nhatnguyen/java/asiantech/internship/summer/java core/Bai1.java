package com.example.myapplication;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Bai1 {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.print("Nhap so a :");
        int a = scan.nextInt();
        System.out.print("nhap so b :");
        int b = scan.nextInt();
        Bai1 nameUCLN = new Bai1();
        Bai1 nameBCNN = new Bai1();
        System.out.println("UCLN la :" + nameUCLN.checkUCLN(a,b));
        System.out.println("BCNN la :"+ nameBCNN.checkBCNN(a,b) );
    }
    public int checkUCLN(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return checkUCLN(b, a % b);

        }
    }
    public int checkBCNN(int a, int b){
        return (a*b)/checkUCLN(a,b);
    }
}
