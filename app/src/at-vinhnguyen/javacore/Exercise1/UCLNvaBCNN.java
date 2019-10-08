package Exercise1;

import java.util.Scanner;

public class UCLNvaBCNN {

    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        System.out.println("Nhap vao a: ");
        int a = sn.nextInt();
        System.out.println("Nhap vao b: ");
        int b = sn.nextInt();
        System.out.println("Uoc chung lon nhat: \n" + uocChungLonNhat(a, b));
        System.out.println("Boi chung nho nhat: \n" + boiChungNhoNhat(a, b));
    }

    private static int uocChungLonNhat(int a, int b) {
        if (a == 0 || b == 0) {
            return a + b;
        }
        while (a != b) {
            if (a > b) {
                a -= b;
            } else {
                b -= a;
            }
        }
        return a;
    }

    private static int boiChungNhoNhat(int a, int b) {
        int max, bcnn = 1;
        if (a == 0 || b == 0) {
            System.out.println(a + " va " + b + " khong co boi chung nho nhat");
        }
        if (a > b) {
            max = a;
        } else {
            max = b;
        }
        int i = max;
        while (true) {
            if (i % a == 0 && i % b == 0) {
                bcnn = i;
                break;
            }
            i += max;
        }
        return bcnn;
    }
}
