package asiantech.internship.summer.java_core.ex30;

import java.util.logging.Level;
import java.util.logging.Logger;

import asiantech.internship.summer.java_core.common.Common;

public class StringStandardize {

    public static void main(String[] args) {
        Logger log = Logger.getLogger(StringStandardize.class.getName());
        String name = Common.inputString("Nhập vào họ tên:");
        name = name.replaceAll("\\s+", " ").trim();

        String firstName = name.substring(0, name.indexOf(' '));
        String middleName = name.substring(name.indexOf(' ') + 1, name.lastIndexOf(' '));
        String lastName = name.substring(name.lastIndexOf(' ') + 1);

        String nameStand = lastName + " " + middleName + " " + firstName;
        if (log.isLoggable(Level.INFO)) {
            log.info(name + " => " + nameStand);
        }
    }

}
