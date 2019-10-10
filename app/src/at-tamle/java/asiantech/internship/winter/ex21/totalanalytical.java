package asiantech.internship.winter.ex21;

import java.util.Scanner;
//tinh tong + phan tich thua so ngto
public class totalanalytical {
    public static int process(){
        Scanner input= new Scanner(System.in); boolean check= false;
        int n=0; while(!check){
            System.out.print(" "); try{
                n= input.nextInt(); check= true;
            }catch(Exception e){
                System.out.println("phai nhap so, moi nhap lai"); input.nextLine();
            }
        }
        return (n);
    }
    public static int New(int n){ int T=0;
        while(n>0){
            T+= n%10;
            n/= 10;
        }
        return (T);
    }
    //Ham kiem tra so nguyen to
    public static boolean incheck(int n){ if(n>1){for(int i=2;i<=Math.sqrt(n);i++){ if(n%i==0) return false;
    }
        return true;
    }
    else return false;
    }
    public static void result(int n){
        int i=2; while(n>1){
            if(incheck(i)){
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
        int n= process(); System.out.print("n= " ); result(n);
        System.out.println("Tong cac chu so cua "+n+" la: "+New(n));
    }
}
