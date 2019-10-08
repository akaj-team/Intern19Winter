package asiantech.internship.summer.java_core.ex09;

import asiantech.internship.summer.java_core.common.Common;

public class Binary {

	public static void main(String[] args) {
		int n = Common.input("Nhập vào độ dài n: ");
		int[] a = new int[n];

		for (int i = 0; i < Math.pow(2, n); i++) {
			generate(a, n, i);
			print(a);
		}
	}

	public static void generate(int[] a, int n, int index) {
		if (index != 0) {
			a[n - 1]++;
		}
		for (int i = n - 1; i > 0; --i) {
			if (a[i] > 1) {
				a[i - 1]++;
				a[i] -= 2;
			}
		}
	}

	public static void print(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]);
		}
		System.out.println();
	}

}
