package asiantech.internship.summer.java_core.ex23;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import asiantech.internship.summer.java_core.common.Common;

public class PrimeAndFibonacciNumber {

    public static void main(String[] args) {
        Logger log = Logger.getLogger(PrimeAndFibonacciNumber.class.getName());
        int n = Common.input("Nhập vào n: ");

        if (log.isLoggable(Level.INFO)) {
            log.info(n + " số nguyên tố đầu tiên: " + getListPrime(n));
            log.info(n + " số Fibo đầu tiên: " + Common.getFibonacci(n));
        }
    }

    private static List<Integer> getListPrime(int n) {
        List<Integer> listPrime = new ArrayList<>();
        int count = 1;
        int index = 1;
        while (count <= n) {
            if (Common.isPrime(index)) {
                listPrime.add(index);
                count++;
            }
            index++;
        }
        return listPrime;
    }

}
