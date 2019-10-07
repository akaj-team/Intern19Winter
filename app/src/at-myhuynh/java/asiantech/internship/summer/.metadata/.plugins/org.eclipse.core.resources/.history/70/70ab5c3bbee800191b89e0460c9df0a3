package ex21;

import common.Common;

public class SumAndPrime {

	public static void main(String[] args) {
		int n = 1123;
		System.out.println("Total: " + Common.totalOfNumber(n));

		String strPrime = "";
		if (Common.isPrime(n)) {
			strPrime += n;
		} else {
			strPrime = Common.primeFactor(n);
			String[] arrPrime = strPrime.split("");
			strPrime = String.join("x", arrPrime);
		}

		System.out.println(n + " => " + strPrime);
	}
}
