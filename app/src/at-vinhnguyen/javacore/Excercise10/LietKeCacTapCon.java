package Excercise10;

import java.util.Scanner;

public class LietKeCacTapCon {

    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        System.out.print("Nhap n: ");
        int n = sn.nextInt();
        System.out.print("Nhap k: ");
        int k = sn.nextInt();
        lietKeCacTapCon(n, k);
    }

    private static void lietKeCacTapCon(int n, int k) {
        int[] a = new int[k];
        for (int i = 0; i < k; i++) {
            a[i] = i + 1;
            System.out.print(a[i]);
        }
        System.out.print("\n");
        for (int i = k - 1; i >= 0; i--) {
            if (a[i] < n - k + i + 1) {
                a[i]++;

                int j;
                for (j = i + 1; j < k; j++) {
                    a[j] = a[j - 1] + 1;
                }

                for (j = 0; j < k; j++) {
                    System.out.print("" + a[j]);
                }
                System.out.print("\n");

                i = k;
            }
        }
    }
}
