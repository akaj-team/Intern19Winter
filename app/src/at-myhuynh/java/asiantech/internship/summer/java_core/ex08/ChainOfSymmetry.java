package asiantech.internship.summer.java_core.ex08;

import java.util.logging.Logger;

import asiantech.internship.summer.java_core.common.Common;

public class ChainOfSymmetry {

    public static void main(String[] args) {
        Logger log = Logger.getLogger(ChainOfSymmetry.class.getName());
        String str = Common.inputString("Nhập vào chuỗi: ");

        String[] arrStr = str.split("");
        int first = 0;
        int last = arrStr.length - 1;
        boolean check = true;

        while (first < last) {
            if (!arrStr[first++].equals(arrStr[last--])) {
                check = false;
                break;
            }
        }

        if (check) {
            log.info(str + ": Thuan nghich");
        } else {
            log.info(str + ": Khong Thuan nghich");
        }

        //Or Use StringBuffer
        log.info(String.valueOf(str.equals(new StringBuffer(str).reverse().toString())));

    }

}
