
package Excercise30;

import java.util.Scanner;

public class ChuanHoaTen {
    public static void main(String[] args) {
        System.out.println("Nhap ten cua ban: ");
        String fullName = new Scanner(System.in).nextLine();
        int lastNamePosition = fullName.lastIndexOf(" ")+1;
        System.out.println("Your name: "+ fullName.substring(lastNamePosition)+" " + fullName.substring(0,lastNamePosition-1));
    }
}
