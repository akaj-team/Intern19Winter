package asiantech.internship.summer.java_core.ex23;

import java.util.ArrayList;
import java.util.List;

import asiantech.internship.summer.java_core.common.Common;

public class PrimeAndFibonaciNumber {

	public static void main(String[] args) {
		int n = 10;

		System.out.println(n + " số nguyên tố đầu tiên: " + getListPrime(n));
		System.out.println(n + " số Fibo đầu tiên: " + Common.getListFibonaci(n));
	}

	public static List<Integer> getListPrime(int n) {
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
