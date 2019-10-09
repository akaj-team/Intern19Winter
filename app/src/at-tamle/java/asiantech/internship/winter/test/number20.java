package asiantech.internship.winter.test;

import java.util.Scanner;
//  liet ke fibonacci < n so nguyen to
public class number20 {
    public static int Input(){
        Scanner input= new Scanner(System.in);
        boolean check= false;
        int n=0;
        while(!check){
            System.out.print(" ");
            try{
                n= input.nextInt();
                check= true;
            }catch(Exception e){
                System.out.println("Ban phai nhap so! hay nhap lai...");
                input.nextLine();
            }
        }
        return (n);
    }
    //Ham kiem tra so nguyen to
    public static boolean incheck(int n){
        if(n>1){
            for(int i=2;i<=Math.sqrt(n);i++){
                if(n%i==0) return false;
            }
            return true;
        }
        else return false;
    }
    public static void main(String[] args) {
        System.out.print("Nhap n= ");
        int n= Input();
        int[] f= new int[n];
        f[0]= 1; f[1]= 1;
        int i=1,count=1;
        System.out.print("Cac so Fibonanci nho hon "+n+" la so nguyen to: \n 1");
        while(f[i]<n){
            if(incheck(f[i])){
                System.out.print(" "+f[i]);
                count++;
            } i++;
            f[i]= f[i-1] + f[i-2];
        }
        System.out.println("\n Co "+count+" so thoa man");
    }
}
