package baitap;
import java.util.Scanner;
public class bai2 {
	public static void doiCoSo(int n, int base) {
		  if (n >= base)
		   doiCoSo(n / base, base);
		  if (n % base > 9)
		   System.out.printf("%c", n % base + 55);
		  else
		   System.out.print((n % base));
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
		    System.out.println("Ban phai nhap so! hay nhap lai...");
		    input.nextLine();
		   }
		  }
		  return (n);
		 }

		 public static void main(String[] args) {
		  System.out.println("Nhap n");
		  int n = nhap();
		  System.out.println("Nhap vao co so can chuyen sang b");
		  int b = nhap();
		  System.out.println("So " + n + " chuyen sang co so " + b + " thanh: ");
		  doiCoSo(n, b);
		 }
}
