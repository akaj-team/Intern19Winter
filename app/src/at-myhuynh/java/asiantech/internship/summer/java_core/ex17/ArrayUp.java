package asiantech.internship.summer.java_core.ex17;

import java.util.Arrays;

public class ArrayUp {

	public static void main(String[] args) {
		int[] arrInt = { 1, 2, 4, 7, 3, 9, 5, 8, 6 };
		Arrays.sort(arrInt);
		for (int i : arrInt) {
			System.out.print(i + " ");
		}

	}

}
