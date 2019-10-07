package ex21;

import java.util.ArrayList;
import java.util.List;

import common.Common;

public class SumAndPrime {

	public static void main(String[] args) {
		int n = 1234;
		System.out.println("Total: " + Common.totalOfNumber(n));

		List<String> strPrime = new ArrayList<>();
		if (Common.isPrime(n)) {
			strPrime.add(String.valueOf(n));
		} else {
			strPrime = Common.primeFactor(n);
		}

		System.out.println(n + " => " + String.join("x", strPrime));
	}
}