package asiantech.internship.winter.baitap;
import java.util.Scanner;
//liet ke all so nguyen to <n
public class bai5 {
	public static void lietKe(int n) {
		  if (n > 2)
		   System.out.print("2");
		  for (int i = 3; i < n; i += 2) {
		   if (ktNguyenTo(i))
		    System.out.print(" " + i);
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
		  System.out.println("Cac so nguyen to nho hon " + n + " ");
		  lietKe(n);
		 }
}
