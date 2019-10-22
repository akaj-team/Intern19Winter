package asiantech.internship.summer.java_core.ex03;

import java.util.logging.Level;
import java.util.logging.Logger;

import asiantech.internship.summer.java_core.common.Common;

public class TotalOfNumber {

    public static void main(String[] args) {
        Logger log = Logger.getLogger(TotalOfNumber.class.getName());
        int number = Common.input("Nhập vào số cần tính: ");
        int total = Common.totalOfNumber(number);

        if (log.isLoggable(Level.INFO)) {
            log.info(number + " => " + total);
        }
    }
}
