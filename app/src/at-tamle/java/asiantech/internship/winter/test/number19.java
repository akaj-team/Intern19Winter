package asiantech.internship.winter.test;

import java.util.Scanner;
// liet ke all so nguyen to co 5 chu so sao cho tong cua cac chu so trong moi so nguyen to deu = S cho truoc
public class number19 {
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
    public static boolean Check(int n){
        if(n>1){
            for(int i=2;i<=Math.sqrt(n);i++){
                if(n%i==0) return false;
            }
            return true;
        }
        else return false;
    }
    public static int tongChuSo(int n){
        int T=0;
        while(n>0){
            T+= n%10;
            n/= 10;
        }
        return (T);
    }
    public static void main(String[] args) {
        System.out.print("Nhap S= ");
        int s= Input(); int i,count=0;
        System.out.println("Cac so nguyen to co tong cac chu so co tong bang "+s+" la: ");
        for(i=10000 ; i<=99999 ; i++){
            if(Check(i)){
                if(tongChuSo(i)== s) {
                    System.out.println(" "+i);
                    count++;
                }
                else continue;
            }
        }
        System.out.println("Co "+count+" so thoa man");
    }
}
