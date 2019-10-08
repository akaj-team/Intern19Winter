package asiantech.internship.winter.baitap;
import java.util.Scanner;
public class bai4 {
	public static void phanTich(int n) {
		  int i = 2;
		  while (n > 1) {
		   if (n % i == 0) {
		    System.out.print(i);
		    n /= i;
		    if(n > 1) System.out.print(".");
		   } else
		    i++;
		  }
		 }

	 public static int nhap() {
		  Scanner input = new Scanner(System.in);
		  boolean check = false;
		  int n = 0;
		  while (!check) {
		   System.out.print(" ");
		   try {
		    n = input.nextInt();
		    check = true;
		   } catch (Exception e) {
		    System.out.println("nhap so hay, nhap lai");
		    input.nextLine();
		   }
		  }
		  return (n);
		 }

		 public static void main(String[] args) {
		  System.out.print("Nhap n");
		  int n = nhap();
		  System.out.print(n + "= ");
		  phanTich(n);
		 }

}
