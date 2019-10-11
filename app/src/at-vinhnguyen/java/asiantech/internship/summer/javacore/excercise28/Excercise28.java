package asiantech.internship.summer.javacore.excercise28;

import java.util.Scanner;

public class Excercise28 {

    public static String standardized(String str) {
        str = str.trim();
        str = str.replaceAll("\\s+", " ");
        return str;
    }

    public static String standardizedProperNoun(String str) {
        str = standardized(str);
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
        str = standardizedProperNoun(str);
        System.out.println(str);
    }
}
