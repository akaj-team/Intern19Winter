package Exercise4;

import static Excercise21.Bait21.checkSNT;
import java.util.Scanner;

public class ThuaSoNguyenTo {

    public static void main(String[] args) {
        System.out.print("Nhap n: ");
        int n = new Scanner(System.in).nextInt();
        phanTichThuaSo(n);
    }

    private static void phanTichThuaSo(int n) {
        int i = 2;
        while (n > 1) {
            if (checkSNT(i)) {
                if (n % i == 0) {
                    System.out.print(i + ".");
                    n /= i;
                } else {
                    i++;
                }
            } else {
                i++;
            }
        }
    }

}
