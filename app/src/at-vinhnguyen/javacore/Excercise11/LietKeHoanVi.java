package Excercise11;

import java.util.Scanner;

public class LietKeHoanVi {

    public static void main(String[] args) {
        System.out.print("Nhap n: ");
        int n = new Scanner(System.in).nextInt();
        lietKeHoanVi(n);
    }

    private static void lietKeHoanVi(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i + 1;
        }
        for (int i = 0; i < n; i++) {
            System.out.print("" + a[i]);
        }
        for (int i = n - 1; i > 0; i--) {

            if (a[i] > a[i - 1]) {

                for (int j = n - 1; j >= i; j--) {
                    if (a[j] > a[i - 1]) {
                        int temp = a[j];
                        a[j] = a[i - 1];
                        a[i - 1] = temp;
                        break;
                    }
                }

                for (int j = n - 1; j > ((n - 1 + i) / 2); j--) {
                    int temp = a[i + n - 1 - j];
                    a[i + n - 1 - j] = a[j];
                    a[j] = temp;
                }

                System.out.println("");
                for (int j = 0; j < n; j++) {
                    System.out.print("" + a[j]);
                }
                i = n;
            }
        }
    }
}
