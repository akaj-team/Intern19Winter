package asiantech.internship.summer.javacore;
import java.util.Scanner;
public class Exercise21 {
    private static int input(){
        Scanner scan= new Scanner(System.in);
        boolean check= false;
        int n=0; while(!check){
            System.out.print(" "); try{
                n= scan.nextInt(); check= true;
            }catch(Exception e){
                System.out.println("Ban phai nhap so ");
                scan.nextLine();
            }
        }
        return (n);
    }
    private static int totalDigits(int n){ int T=0;
        while(n>0){
            T+= n%10;
            n/= 10;
        }
        return (T);
    }
    //Ham kiem tra so nguyen to
    private static boolean isPrimeNumber(int n)
    {
        if(n>1){
            for(int i=2;i<=Math.sqrt(n);i++)
            {
                if(n%i==0) return false;
    }
        return true;
    }
    else return false;
    }
    private static void analysis(int n){
        int i=2; while(n>1){
            if(isPrimeNumber(i)){
                if(n%i==0){
                    System.out.print(i+"."); n/=i;
                }
                else i++;
            }
            else i++;
        }
    }
    public static void main(String[] args) {
        System.out.print("Nhap n");
        int n= input();
        System.out.print("n= 1" );
        analysis(n);
        System.out.println("Tong cac chu so cua "+n+" la: "+ totalDigits(n));
    }
}