package asiantech.internship.summer.java_core.ex19;

import asiantech.internship.summer.java_core.common.Common;

public class SumNumberPrime {
	public static void main(String[] args) {
		int n = 14;
		for(int i = 10000; i < 100000; i++){
			if(Common.isPrime(i) && Common.totalOfNumber(i) == n){
				System.out.println(i);
			}
		}

	}
}