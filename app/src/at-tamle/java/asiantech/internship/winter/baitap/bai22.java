package asiantech.internship.winter.baitap;
import java.util.*;
// liet ke uoc n va liet ke uoc n la so nguyen to
public class bai22 {
	 public static int nhap(){
		 Scanner input= new Scanner(System.in); boolean check= false;
		 int n=0; while(!check){
		 System.out.print(" "); try{
		 n= input.nextInt(); check= true;
		 
		 }catch(Exception e){
		 System.out.println("phai nhap so, hay nhap lai"); 
		 input.nextLine();
		 
		 }
		 
		 }
		 return (n);
		 }
		 public static boolean checkSNT(int n){
		 if(n>1){
		 for(int i=2;i<=Math.sqrt(n);i++){ if(n%i==0) return false;}
		 
		 return true;
		 
		 }
		 else return false;
		 
		 }
		 public static void lietKeUoc(int n){
		 int count=0;
		 System.out.print("\nCac uoc cua "+n+" la:"); for(int i=1 ; i<=n ; i++){
		 if(n%i==0) {
		 System.out.print(" "+i); count++;
		 
		 }
		 
		 }
		 System.out.println("\nCo "+count+" uoc");
		 }
		 public static void lietKeUocSNT(int n){ 
		 int count=0;
		 System.out.print("\nCac uoc cua "+n+" la:"); for(int i=1 ; i<=n ; i++){
		 if(n%i==0 && (checkSNT(i))) { System.out.print(" "+i); count++;
		 
		 }
		 
		 }
		 System.out.println("\nCo "+count+" uoc la so nguyen to");
		 
		 }
		 public static void main(String[] args) { 
		 System.out.print("Nhap n");
		 int n= nhap(); lietKeUoc(n); lietKeUocSNT(n);
		 
		 }
}
