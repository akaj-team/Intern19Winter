package asiantech.internship.winter.baitap;
import java.util.Scanner;
//  liet ke xau nhi phan do dai n
public class bai9 {
	 public static int nhap() {
		  Scanner input = new Scanner(System.in);
		  boolean check = false;
		  int n = 0;
		  while (!check) {
		   System.out.print("Nhap n ");
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
		  int n = nhap();
		  int[] array = new int[n];
		  int tich;
		  do {
		   tich = 1;
		   System.out.println("");
		   // In ra mang va tinh tich cac phan tu trong mang
		   for (int j = 0; j < n; j++) {
		    System.out.print(" " + array[j]);
		    tich *= array[j];
		   }
		   int i = n - 1;
		   do {
		    if (array[i] == 0) {
		     array[i] = 1;
		     for (int j = n - 1; j > i; j--) {
		      array[j] = 0;
		     }
		     break;
		    } else
		     i--;
		   } while (i >= 0);
		  } while (tich != 1);
		 }
}
