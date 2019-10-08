package asiantech.internship.summer.java_core.ex10;

import asiantech.internship.summer.java_core.common.Common;

public class Subset {

	public static void main(String[] args) {
		int n = Common.input("Nhập n: ");
		int k = Common.input("Nhập k: ");

		int[] arr = new int[k];
		quayLui(n, k, arr, 0, 0);
	}

	public static void quayLui(int n, int k, int arr[], int i, int j) {
		for (j = 0; j < n - k + i + 1; j++) {
			arr[i] = j + 1;
			if (i == (k - 1)) {
				int temp;
				for (temp = 0; temp < k; temp++) {
					System.out.print(arr[temp]);
				}
				System.out.println();
			}

			else {
				quayLui(n, k, arr, i + 1, j + 1);
			}
		}
	}

}
