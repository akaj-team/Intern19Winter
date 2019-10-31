package asiantech.internship.summer.javacore;
import java.util.Scanner;
public class Exercise7 {
    private static int input(){
        Scanner scan= new Scanner(System.in);
        boolean check = false;
        int n = 0;
        while(!check){
            System.out.print(" ");
            try{
                n= scan.nextInt();
                check= true;
            }catch(Exception e){
                System.out.println("Ban phai nhap so :");
                scan.nextLine();
            }
        }
        return (n);
    }
    public static void main(String[] args) {
        System.out.print("Nhap n");
        int n= input();
        int[] f= new int[n+1];
        f[0]= 1; f[1]= 1;
        for(int i=2;i<=n;i++){
            f[i]= f[i-1]+f[i-2];
        }
        System.out.println("So Fibonanci thu "+n+" la: f["+n+"]= "+f[n]);
    }
}