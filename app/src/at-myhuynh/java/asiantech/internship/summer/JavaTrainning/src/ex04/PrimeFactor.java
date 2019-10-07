package ex04;

import common.Common;

public class PrimeFactor {

	public static void main(String[] args) {
		int number = 1123;
		String strPrime = Common.primeFactor(number);
		if (!Common.isPrime(number)) {
			String[] arrPrime = strPrime.split("");
			strPrime = String.join("x", arrPrime);
		}

		System.out.println(number + " => " + strPrime);
	}
}
