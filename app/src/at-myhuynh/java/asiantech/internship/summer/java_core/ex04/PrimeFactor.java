package asiantech.internship.summer.java_core.ex04;

import java.util.List;

import asiantech.internship.summer.java_core.common.Common;

public class PrimeFactor {

	public static void main(String[] args) {
		int number = 28;
		List<String> listPrime = Common.primeFactor(number);
		System.out.println(String.join("x", listPrime));
	}
}
