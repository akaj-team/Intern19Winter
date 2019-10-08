package Excercise28;

import java.util.Scanner;

public class Bai28 {

    public static String chuanHoa(String str) {
        str = str.trim();
        str = str.replaceAll("\\s+", " ");
        return str;
    }

    public static String chuanHoaDanhTuRieng(String str) {
        str = chuanHoa(str);
        String temp[] = str.split(" ");
        str = "";
        for (int i = 0; i < temp.length; i++) {
            str += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
            if (i < temp.length - 1) {
                str += " ";
            }
        }
        return str;
    }

    public static void main(String[] sgr) {
        System.out.print("Nhap xau ban can chuan hoa: ");
        String str = new Scanner(System.in).nextLine();
        str = chuanHoaDanhTuRieng(str);
        System.out.println(str);
    }
}
