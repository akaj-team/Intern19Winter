package Exercise9;

import java.util.Scanner;

public class LietKeXauNhiPhan {

    public static void main(String[] args) {
        System.out.print("Nhap n: ");
        int n = new Scanner(System.in).nextInt();
        lietKeXauNhiPhan(n);
    }

    private static void lietKeXauNhiPhan(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = 0;
        }
        for (int i = 0; i < a.length; i++) {
            System.out.print(+a[i]);
        }
        for (int i = n - 1; i >= 0; i--) {
            if (a[i] == 0) {
                a[i] = 1;
                for (int j = i + 1; j < n; j++) {
                    a[j] = 0;
                }
                for (int j = 0; j < n; j++) {
                    System.out.print(a[j]);
                }
                System.out.println("\n");
            }
            i = n;
        }
    }
}
