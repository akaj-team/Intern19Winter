package asiantech.internship.summer.javacore;
import java.util.Scanner;
public class Bai20 {

    private static int input(){
        Scanner scan= new Scanner(System.in);
        boolean check= false;
        int n=0;
        while(!check){
            System.out.print(" ");
            try{
                n= scan.nextInt();
                check= true;
            }catch(Exception e){
                System.out.println("Ban phai nhap so ");
                scan.nextLine();
            }
        }
        return (n);
    }
    private static boolean checkSNT(int n){
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
        int n= input();
        int[] f= new int[n];
        f[0]= 1; f[1]= 1;
        int i=1,count=1;
        System.out.print("Cac so Fibonanci nho hon "+n+" la so nguyen to: \n 1");
        while(f[i]<n){
            if(checkSNT(f[i])){
                System.out.print(" "+f[i]);
                count++;
            } i++;
            f[i]= f[i-1] + f[i-2];
        }
        System.out.println("\n Co "+count+" so thoa man");
    }
}