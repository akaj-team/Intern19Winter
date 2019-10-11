package asiantech.internship.summer.java_core.ex18;

import java.util.logging.Level;
import java.util.logging.Logger;

import asiantech.internship.summer.java_core.common.Common;

public class CountWord {

    public static void main(String[] args) {
        Logger log = Logger.getLogger(CountWord.class.getName());
        String strWord = Common.inputString("Nhập vào chuỗi: ");
        int count = strWord.split("[ ]+").length;
        if (log.isLoggable(Level.INFO)) {
            log.info("'" + strWord + "' có " + count + " từ.");
        }
    }

}
