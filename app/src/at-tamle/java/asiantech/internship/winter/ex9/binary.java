package asiantech.internship.winter.ex9;

import java.util.Scanner;
//liet ke xau nhi phan do dai n
public class binary {
    public static int process() {
        Scanner input = new Scanner(System.in);
        boolean check = false;
        int n = 0;
        while (!check) {
            System.out.println("Nhap n ");
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
        int n = process();
        int[] array = new int[n];
        int New;
        do {
            New = 1;
            System.out.println("");
            // In ra mang va tinh tich cac phan tu trong mang
            for (int j = 0; j < n; j++) {
                System.out.print(" " + array[j]);
                New *= array[j];
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
        } while (New != 1);
    }
}
