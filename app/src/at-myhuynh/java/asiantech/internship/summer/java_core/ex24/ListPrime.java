package asiantech.internship.summer.java_core.ex24;

import java.util.logging.Logger;

import asiantech.internship.summer.java_core.common.Common;

public class ListPrime {

    public static void main(String[] args) {
        Logger log = Logger.getLogger(ListPrime.class.getName());
        for (int i = 10000; i < 10000000; i++) {
            if (Common.isPrime(i) && Common.isChainOfSymmetry(String.valueOf(i)) && checkItemIsPrime(i)) {
                log.info(String.valueOf(i));
            }
        }

    }

    public static boolean checkItemIsPrime(int n) {
        String[] arrNum = String.valueOf(n).split("");
        for (int i = 0; i < arrNum.length; i++) {
            if (!Common.isPrime(Integer.parseInt(arrNum[i]))) {
                return false;
            }
        }
        return true;
    }

}
