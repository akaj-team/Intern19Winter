package asiantech.internship.summer.javacore;

import java.util.Scanner;

public class Exercise1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Nhap so a :");
        int a = scan.nextInt();
        System.out.print("nhap so b :");
        int b = scan.nextInt();
        Exercise1 nameUCLN = new Exercise1();
        Exercise1 nameBCNN = new Exercise1();
        System.out.println("UCLN la :" + nameUCLN.greatestCommonDivisor(a, b));
        System.out.println("BCNN la :" + nameBCNN.checkBCNN(a, b));
    }

    private int greatestCommonDivisor(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return greatestCommonDivisor(b, a % b);

        }
    }

    private int checkBCNN(int a, int b) {
        return (a * b) / greatestCommonDivisor(a, b);
    }
}
