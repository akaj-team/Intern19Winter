package ex20;

import common.Common;

public class FibonaciPrime {
	public static void main(String[] args) {
		int n = 100;
		showFibonaciPrimeNumber(n);
	}

	public static void showFibonaciPrimeNumber(int n) {
		int f1 = 1;
		int f2 = 1;
		int fn = 1;

		do {
			if (fn < n && Common.isPrime(fn)) {
				System.out.println(fn);
			}
			fn = f1 + f2;
			f1 = f2;
			f2 = fn;
		} while (fn < n);
	}
}
