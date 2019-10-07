
package Exercise8;

import java.util.Scanner;

public class KiemTraThuanNghich {
    public static void main(String[] args) {
        System.out.println("Nhap chuoi: ");
        String str = new Scanner(System.in).nextLine();
        if(kiemTraChuoi(str)){
            System.out.println("Chuoi thuan nghich doc");
        } else{
            System.out.println("Khong phai chuoi thuan nghich doc");
        }
    }

    private static boolean kiemTraChuoi(String str) {
        for (int i = 0; i < str.length()/2; i++) {
            if(str.charAt(i)!= str.charAt(str.length()-1-i)){
                return false;
            }
        }
        return true;
    }
}
