package asiantech.internship.winter.ex23;

import java.util.Scanner;
//Liệt kê n số nguyên tố đầu tiên.
//Liệt kê n số Fibonaci đầu tiên.

public class ListPrimeFisrt {
    public static int process(){
        Scanner input= new Scanner(System.in); boolean check= false;
        int n=0; while(!check){
            System.out.print(" "); try{
                n= input.nextInt(); check= true;

            }catch(Exception e){
                System.out.println("Ban phai nhap so! hay nhap lai..."); input.nextLine();

            }

        }
        return (n);

    }
    public static boolean incheck(int n){
        if(n>1){
            for(int i=2;i<=Math.sqrt(n);i++){ if(n%i==0) return false;

            }
            return true;

        }
        else return false;
    }
    public static void Element(int n){
        int i=1,count=0;
        System.out.println("Cac so nguyen to nho hon "+n+" la: "); while(i<n){
            if(incheck(i)){
                System.out.print(" "+i); count++;

            } i++;

        }
        System.out.println("\n Co "+count+" so thoa man");

    }
    public static void main(String[] args) {
        System.out.println("Nhap n");
        int n= process(); Element(n); int[] f= new int[n]; f[0]= 1; f[1]= 1;
        int i=1;
        System.out.print("Cac so Fibonanci nho hon "+n+" la : \n 1"); while(f[i]<n){
            System.out.print(" "+f[i]);
            i++;
            f[i]= f[i-1] + f[i-2];

        }
        System.out.println("\n Co "+i+" so thoa man");

    }


}
