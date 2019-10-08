package asiantech.internship.summer.java_core.ex11;

import asiantech.internship.summer.java_core.common.Common;

public class Permutation {

	public static void main(String[] args) {
		int n = Common.input("Nhập vào số n: ");
		int[] arrInt = new int[n];

		initArr(arrInt, n);
		listPermutation(arrInt);

	}

	public static void initArr(int[] arrInt, int n) {
		for (int i = 0; i < n; i++) {
			arrInt[i] = i + 1;
		}
	}

	public static void listPermutation(int[] arrInt) {
		int n = arrInt.length;
		
		for (int i = 0; i < n; i++) {
			System.out.print(arrInt[i]);
		}
		System.out.println();
		
		for (int i = n - 1; i > 0; i--) {
			if (arrInt[i] >= arrInt[i - 1]) {
				for (int j = n - 1; j >= i; j--) {
					if (arrInt[j] > arrInt[i - 1]) {
						int temp = arrInt[j];
						arrInt[j] = arrInt[i - 1];
						arrInt[i - 1] = temp;
						break;
					}
				}

				for (int j = n - 1; j > ((n - 1 + i) / 2); j--) {
					int temp = arrInt[i + n - 1 - j];
					arrInt[i + n - 1 - j] = arrInt[j];
					arrInt[j] = temp;
				}

				for (int j = 0; j < n; j++) {
					System.out.print(arrInt[j]);
				}
				System.out.println();
				i = n;
			}
		}
	}

}
