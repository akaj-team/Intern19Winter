package asiantech.internship.winter.ex28;
import java.util.Scanner;
import java.util.StringTokenizer;
public class Standardiz {
    public static String New(String str){
        String s,b;
        s= str.substring(0, 1);
        b= str.replaceFirst(s,s.toUpperCase());
        return (b);
    }
    private static String equal(String strInput){
        String b="";
        StringTokenizer strToken= new StringTokenizer(strInput," ,\t,\r");
        b+=""+New(strToken.nextToken()); while(strToken.hasMoreTokens()){
            b +=" "+New(strToken.nextToken());
        }
        return b;
    }
    public static void main(String[] args) {

        Scanner input= new Scanner(System.in);
        System.out.println("Nhap vao 1 xau: ");
        String strInput= input.nextLine();
        System.out.println("Xau duoc chuan hoa la: "+equal(strInput));
    }
}
