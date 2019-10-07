package com.example.myapplication;

import java.util.Scanner;

public class CheckImport {
    public static int nhap(){
        Scanner scan = new Scanner(System.in);
        int n = 0;
        boolean check = false;
        while (!check){
            System.out.print(" ");
            try{
                n = scan.nextInt();
                check = true;
            } catch (Exception e){
                System.out.print("n phai la so");
                scan.nextLine();
            }
        }
        return (n);
    }
    public static boolean checkPrimeNumber(int n){
        if(n<2)return false;
        for (int i = 2; i <= n-1; i++){
            if(n % i == 0)return false;
        }
        return true;
    }
    public static void main(String[] args){
        System.out.print("Nhap n");
        int n = nhap();
        if(n == 2){
            System.out.println("La so nguyen to");
        }
        if(checkPrimeNumber(n)){
            System.out.println("So vua nhap la  " + n + " " + "la so nguyen to");
        } else {
            System.out.println("So vua nhap khong phai so nguyen to");
        }

    }
}
