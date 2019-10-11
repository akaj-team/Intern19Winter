package asiantech.internship.summer.java_core.ex21;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import asiantech.internship.summer.java_core.common.Common;

public class SumAndPrime {

    public static void main(String[] args) {
        Logger log = Logger.getLogger(SumAndPrime.class.getName());
        int n = Common.input("Nhập vào 1 số:");
        if (log.isLoggable(Level.INFO)) {
            log.info("Total: " + Common.totalOfNumber(n));
        }

        List<String> strPrime = new ArrayList<>();
        if (Common.isPrime(n)) {
            strPrime.add(String.valueOf(n));
        } else {
            strPrime = Common.primeFactor(n);
        }
        if (log.isLoggable(Level.INFO)) {
            log.info(n + " => " + Common.joinString("x", strPrime.toArray()));
        }
    }
}
