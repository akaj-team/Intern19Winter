package asiantech.internship.winter.baitap;
import java.util.Scanner;
//liet ke all so thuan nghich 6 chu so
public class bai8 {
	public static boolean testSoThuanNghich(int n) {
		  StringBuilder xau = new StringBuilder();
		  String str = "" + n;
		  xau.append(str);
		  String check = "" + xau.reverse();
		  if (str.equals(check))
		   return true;
		  else
		   return false;
		 }

	 public static void main(String[] args) {
		  int n, count = 0;
		  for (n = 100000; n <= 999999; n++) {
		   if (testSoThuanNghich(n)) {
		    System.out.println(n);
		    count++;
		   }
		  }
		  System.out.println("Co " + count + " so thuan nghich co 6 chu so");
		 }

}
