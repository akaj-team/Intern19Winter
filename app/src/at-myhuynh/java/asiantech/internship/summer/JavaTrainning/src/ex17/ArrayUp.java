package ex17;

import java.util.Arrays;

public class ArrayUp {

	public static void main(String[] args) {
		int[] arr = { 1, 2, 4, 7, 3, 9, 5, 8, 6 };
		Arrays.sort(arr);
		for (int i : arr) {
			System.out.print(i + " ");
		}

	}

}
