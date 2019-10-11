package asiantech.internship.summer.java_core.ex02;

import java.util.logging.Level;
import java.util.logging.Logger;

import asiantech.internship.summer.java_core.common.Common;

public class Convert {
    static final Logger log = Logger.getLogger(Convert.class.getName());

    public static void main(String[] args) {
        int a = Common.input("Nhập vào một số tự nhiên:");
        int b = Common.input("Nhập vào cơ số cần chuyển sang(1 < b < 36):");
        convertBase(a, b);
    }

    public static void convertBase(int n, int base) {
        if (n >= base) {
            convertBase(n / base, base);
        }
        if (n % base > 9) {
            if (log.isLoggable(Level.INFO)) {
                log.info(String.valueOf(n % base + 55));
            }
        } else {
            if (log.isLoggable(Level.INFO)) {
                log.info(String.valueOf(n % base));
            }
        }
    }

}
