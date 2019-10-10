package asiantech.internship.winter.ex18;

import java.util.Scanner;
import java.util.StringTokenizer;
//dem so ky tu cua chuoi
public class incount {
    public static void main(String[] args)
    { Scanner input= new
            Scanner(System.in);
        System.out.println("Nhap vao 1 xau: ");
        String str= input.nextLine();
        StringTokenizer strToken= new
                StringTokenizer(str, " "); System.out.println("So cac tu trong xau la: "+strToken.countTokens());
    }
}
