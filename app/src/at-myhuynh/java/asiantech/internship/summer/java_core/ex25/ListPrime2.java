package asiantech.internship.summer.java_core.ex25;

import java.util.logging.Logger;

import asiantech.internship.summer.java_core.common.Common;

public class ListPrime2 {

    public static void main(String[] args) {
        Logger log = Logger.getLogger(ListPrime2.class.getName());
        for (int i = 1000000; i < 10000000; i++) {
            int total = Common.totalOfNumber(i);
            if (Common.isPrime(i) && Common.isChainOfSymmetry(String.valueOf(i)) && Common.isChainOfSymmetry(String.valueOf(total))) {
                log.info(String.valueOf(i));
            }
        }
    }

}
