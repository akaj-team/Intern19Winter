package asiantech.internship.winter.ex29;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Longword {
    private static void searchMax(String strInput){
        StringTokenizer strToken= new StringTokenizer(strInput," \t\r");
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
