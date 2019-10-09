package asiantech.internship.summer.java_core.ex21;

import java.util.ArrayList;
import java.util.List;

import asiantech.internship.summer.java_core.common.Common;

public class SumAndPrime {

    public static void main(String[] args) {
        int n = 28;
        System.out.println("Total: " + Common.totalOfNumber(n));

        List<String> strPrime = new ArrayList<>();
        if (Common.isPrime(n)) {
            strPrime.add(String.valueOf(n));
        } else {
            strPrime = Common.primeFactor(n);
        }

        System.out.println(n + " => " + Common.joinString("x", strPrime.toArray()));
    }
}
