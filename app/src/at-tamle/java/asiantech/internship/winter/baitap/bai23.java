package asiantech.internship.winter.baitap;
import java.util.*;
// Liệt kê n số nguyên tố đầu tiên.
//Liệt kê n số Fibonaci đầu tiên.
public class bai23 {
	
	 public static int nhap(){
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
	 public static boolean checkSNT(int n){
	  if(n>1){
	   for(int i=2;i<=Math.sqrt(n);i++){ if(n%i==0) return false;
	 
	   }
	   return true;
	 
	  }
	  else return false;
	 }
	 public static void lietKeSNT(int n){
	  int i=1,count=0;
	  System.out.println("Cac so nguyen to nho hon "+n+" la: "); while(i<n){
	  if(checkSNT(i)){
	   System.out.print(" "+i); count++;
	 
	   } i++;
	 
	  }
	  System.out.println("\n Co "+count+" so thoa man");
	 
	 }
	 public static void main(String[] args) { 
	  System.out.print("Nhap n");
	  int n= nhap(); lietKeSNT(n); int[] f= new int[n]; f[0]= 1; f[1]= 1;
	  int i=1;
	  System.out.print("Cac so Fibonanci nho hon "+n+" la : \n 1"); while(f[i]<n){
	   System.out.print(" "+f[i]);
	   i++;
	   f[i]= f[i-1] + f[i-2];
	 
	  }
	  System.out.println("\n Co "+i+" so thoa man");
	 
	  }
	 
	 
}
