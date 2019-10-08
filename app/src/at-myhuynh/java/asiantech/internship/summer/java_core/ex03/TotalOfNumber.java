package asiantech.internship.summer.java_core.ex03;

import asiantech.internship.summer.java_core.common.Common;

public class TotalOfNumber {

    public static void main(String[] args) {
        int number = Common.input("Nhập vào số cần tính: ");
        int total = Common.totalOfNumber(number);

        System.out.println(number + " => " + total);
    }
}
