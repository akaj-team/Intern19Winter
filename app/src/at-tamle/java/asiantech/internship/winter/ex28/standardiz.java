package asiantech.internship.winter.ex28;

import java.util.Scanner;
import java.util.StringTokenizer;
//Viết chương trình thực hiện chuẩn hoá một xâu ký tự nhập từ bàn phím
//loại bỏ các dấu cách thừa, chuyển ký tự đầu mỗi từ thành chữ hoa, các ký tự khác thành chữ thường)
public class standardiz {
    public static String New(String str){
        String s,b;
        s= str.substring(0, 1);
        b= str.replaceFirst(s,s.toUpperCase());
        return (b);
    }
    public static String equal(String strInput){
        String b="";
        StringTokenizer strToken= new StringTokenizer(strInput," ,\t,\r");
        b+=""+New(strToken.nextToken()); while(strToken.hasMoreTokens()){
            b +=" "+New(strToken.nextToken());
        }
        return(b);
    }
    public static void main(String[] args) {

        Scanner input= new Scanner(System.in);
        System.out.println("Nhap vao 1 xau: ");
        String strInput= input.nextLine();
        System.out.println("Xau duoc chuan hoa la: "+equal(strInput));
    }
}
