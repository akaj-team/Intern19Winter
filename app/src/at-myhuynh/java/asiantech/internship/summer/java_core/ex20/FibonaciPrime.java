package asiantech.internship.summer.java_core.ex20;

import asiantech.internship.summer.java_core.common.Common;

public class FibonaciPrime {
	public static void main(String[] args) {
		int n = 100;
		showFibonaciIsPrime(n);
	}

	public static void showFibonaciIsPrime(int n) {
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
