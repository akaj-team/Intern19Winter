package ex24;

import common.Common;

public class Ex24 {

	public static void main(String[] args) {

		for (int i = 10000; i < 10000000; i++) {
			if (Common.isPrime(i) && Common.thuanNghich(String.valueOf(i)) && checkItemIsPrime(i)) {
				System.out.println(i);
			}
		}

	}

	public static boolean checkItemIsPrime(int n) {
		String[] arrNum = String.valueOf(n).split("");
		for (int i = 0; i < arrNum.length; i++) {
			if (!Common.isPrime(Integer.parseInt(arrNum[i]))) {
				return false;
			}
		}
		return true;
	}

}
