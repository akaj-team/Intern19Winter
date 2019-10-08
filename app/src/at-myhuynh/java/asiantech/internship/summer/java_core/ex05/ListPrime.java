package asiantech.internship.summer.java_core.ex05;

import asiantech.internship.summer.java_core.common.Common;

public class ListPrime {

	public static void main(String[] args) {
		int n = Common.input("Nhập vào số n: ");
		print(n);
	}

	public static void print(int number) {
		for (int i = 1; i <= number; i++) {
			if (Common.isPrime(i)) {
				System.out.println(i);
			}
		}
	}
}
