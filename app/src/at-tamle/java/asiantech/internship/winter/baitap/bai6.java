package asiantech.internship.winter.baitap;
import java.util.Scanner;
// n so nguyen to dau tien
public class bai6 {
	 public static void lietKe(int n) {
		  int i = 2, count = 0;
		  while (count < n) {
		   if (ktNguyenTo(i)) {
		    System.out.print(" " + i);
		    count++;
		   }
		   i++;
		  }
		 }

	 public static boolean ktNguyenTo(int n) {
		  if (n == 2 || n == 3)
		   return true;
		  if (n == 1 || n % 2 == 0 || n % 3 == 0)
		   return false;
		  int k = -1;
		  do {
		   k += 6;
		   if (n % k == 0 || n % (k + 2) == 0)
		    break;
		  } while (k * k < n);// k < sqrt(n);
		  return k * k > n;// return k > sqrt(n).
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
		    System.out.println("phai nhap so, hay nhap lai");
		    input.nextLine();
		   }
		  }
		  return (n);
		 }

		 public static void main(String[] args) {
		  System.out.print("Nhap n");
		  int n = nhap();
		  System.out.println(n + " so nguyen to dau tien la: ");
		  lietKe(n);
		 }

}
