package asiantech.internship.winter.ex30;

import java.util.Scanner;
import java.util.StringTokenizer;
// ho dem ten > ten ho dem
public class surname {
    private static void total(String s) {
        StringTokenizer t = new StringTokenizer(s);
        StringBuffer k = new StringBuffer();
        String s1[] = new String[10];
        int i = 1;
        while (t.hasMoreTokens()) {
            s1[i] = t.nextToken();
            i++;
        }
        k.append(s1[3]).append("  ");
        k.append(s1[1]).append("  ");
        k.append(s1[2]).append("  ");
        System.out.println(k);
    }

    public static void main(String[] args) {
        String s0 = new String();
        Scanner in = new Scanner(System.in);
        System.out.println("nhap xau ho ten: ");
        s0 = in.nextLine();
        total(s0);
    }
}
