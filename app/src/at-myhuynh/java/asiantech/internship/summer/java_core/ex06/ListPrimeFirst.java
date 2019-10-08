package asiantech.internship.summer.java_core.ex06;


import asiantech.internship.summer.java_core.common.Common;

public class ListPrimeFirst {

	public static void main(String[] args) {
		int n = 10;
		print(n);
	}

	public static void print(int n) {
		int count = 0;
		int number = 1;

		do {
			if (Common.isPrime(number)) {
				count++;
				System.out.println(number);
			}
			number++;
		} while (count < n);
	}

}
