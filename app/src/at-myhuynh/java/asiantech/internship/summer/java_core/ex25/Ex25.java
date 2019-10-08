package asiantech.internship.summer.java_core.ex25;

import asiantech.internship.summer.java_core.common.Common;

public class Ex25 {

	public static void main(String[] args) {
		for (int i = 1000000; i < 10000000; i++) {
			int total = Common.totalOfNumber(i);
			if (Common.isPrime(i) && Common.thuanNghich(String.valueOf(i)) && Common.thuanNghich(String.valueOf(total))) {
				System.out.println(i);
			}
		}
	}

}
