package asiantech.internship.summer.ex03;

import asiantech.internship.summer.common.Common;

public class TotalOfNumber {

    public static void main(String[] args) {
        int number = Common.input("Nhập vào số cần tính: ");
        int total = Common.totalOfNumber(number);

        System.out.println(number + " => " + total);
    }
}
