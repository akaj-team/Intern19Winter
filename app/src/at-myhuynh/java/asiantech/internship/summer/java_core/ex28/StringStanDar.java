package asiantech.internship.summer.java_core.ex28;

import java.util.logging.Logger;

import asiantech.internship.summer.java_core.common.Common;

public class StringStanDar {

    public static void main(String[] args) {
        Logger log = Logger.getLogger(StringStanDar.class.getName());
        String str = Common.inputString("Nhập vào chuỗi: ");
        log.info(str);
        str = str.trim().toLowerCase();
        String[] arrStr = str.split("[ ]+");

        for (int i = 0; i < arrStr.length; i++) {
            String temp = String.valueOf(arrStr[i].charAt(0));
            arrStr[i] = arrStr[i].replaceFirst(temp, temp.toUpperCase());
        }

        log.info(Common.joinString(" ", arrStr));
    }
}
