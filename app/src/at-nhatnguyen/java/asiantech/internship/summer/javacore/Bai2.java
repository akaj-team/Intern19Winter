package asiantech.internship.summer.javacore;
import java.util.Scanner;
public class Bai2 {
    private static void doiCoSo(int n,int base){
        if(n>=base) doiCoSo(n / base, base);
        if(n % base>9) System.out.printf("%c",n%base+55);
        else
            System.out.print((n % base));
    }
    public static int input(){
        Scanner scan= new Scanner(System.in);
        boolean check= false;
        int n=0;
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
        System.out.println("Nhap n");
        int n= input();
        System.out.println("Nhap vao co so can chuyen sang b");
        int b= input();
        System.out.println("So " +n+ " chuyen sang co so " +b+ " thanh: ");
        doiCoSo(n,b);
    }
}