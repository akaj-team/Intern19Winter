package asiantech.internship.winter.ex2;

import java.util.Scanner;
//chuyen co so 10 thanh co so bat ky
public class Convert {

        public static void radix(int n,int k){
            if(n>=k) radix(n / k, k);
            if(n % k>9) System.out.println("%c"+n%k+55);
            else
                System.out.print((n % k));
        }
        public static int process(){
            Scanner input= new Scanner(System.in);
            boolean check= false;
            int n=0;
            while(!check){
                System.out.println(" ");
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
        public static void main(String[] args) {
            System.out.println("Nhap n");
            int n= process();
            System.out.println("Nhap vao co so can chuyen sang b");
            int b= process();
            System.out.println("So " +n+ " chuyen sang co so " +b+ " thanh: ");
            radix(n,b);
        }
    }


