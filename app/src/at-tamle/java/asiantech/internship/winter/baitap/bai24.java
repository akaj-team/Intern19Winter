package asiantech.internship.winter.baitap;
import java.util.*;
public class bai24 {
	 public static boolean checkSNT(int n){
         if(n>1){
for(int i=2;i<=Math.sqrt(n);i++){ 
            if(n%i==0) return false;
         }
         return true;
    }
         else return false;
    }
public static boolean testSoThuanNghich(int n){ 
StringBuilder xau= new StringBuilder(); 
         String str= ""+n;
         xau.append(str);
String check= ""+xau.reverse(); 
if(str.equals(check)) return true; 
         else return false;
    }
public static boolean nguyenTo(int n){ 
         while(n!=0){
if(!checkSNT(n%10)) return false; 
             n/= 10;
         }
         return true;
    }
public static void main(String[] args) { 
    int i,count= 0;
System.out.println("cac so tu 5-7 chu so thoa man dieu kien la: "); 
          for(i=22223 ; i<7777777 ; i+=2){
if(checkSNT(i) && nguyenTo(i) && testSoThuanNghich(i)){ 
                  System.out.println(" "+i); count++;
             }
         }
         System.out.println("\n Co "+count+" so thoa man");
    }
}
