package asiantech.internship.summer.javacore;

import java.util.Scanner;
import java.util.StringTokenizer;
public class Bai18 {
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Nhap vao 1 xau: ");
        String str = scan.nextLine();
        StringTokenizer strToken = new StringTokenizer(str, " ");
        System.out.println("So tu trong xau la: "+strToken.countTokens());
    }
}