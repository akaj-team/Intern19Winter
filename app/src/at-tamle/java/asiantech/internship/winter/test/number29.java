package asiantech.internship.winter.test;

import java.util.Scanner;
import java.util.StringTokenizer;
// Viết chương trình thực hiện nhập một xâu ký tự và tìm từ dài nhất trong xâu đó. Từ đó xuất hiện ở vị trí nào?
public class number29 {
    public static void searchMax(String strInput){
        StringTokenizer strToken= new StringTokenizer(strInput," ,\t,\r");
        int Max,i=1,lengthStr;
        Max= strToken.nextToken().length();
        int inMax= i;
        while(strToken.hasMoreTokens()){
            lengthStr= strToken.nextToken().length();
            i++;
            if(Max < lengthStr){
                Max= lengthStr;
                inMax= i;
            }
        }
        System.out.println("Do dai xau lon nhat la: "+Max+" o vi tri "+inMax);
    }
    public static void main(String[] args) {

        Scanner input= new Scanner(System.in);
        System.out.println("Nhap vao 1 xau: ");
        String strInput= input.nextLine();
        searchMax(strInput);
    }
}
