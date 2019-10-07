package ex26;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ex26 {

	public static void main(String[] args) {
		List<Integer> listNumber = new ArrayList<>();
		listNumber.add(1);
		listNumber.add(2);
		listNumber.add(4);
		listNumber.add(7);
		listNumber.add(2);
		listNumber.add(4);
		listNumber.add(5);
		System.out.println(listNumber.toString());

		int[] maxNumbers = findMaxNumber(listNumber);
		System.out.println("Max number: " + maxNumbers[0] + " - " + maxNumbers[1]);

		int[] secondMaxNumber = findMaxNumberSecond(listNumber, maxNumbers[0]);
		System.out.println("Second max number: " + secondMaxNumber[0] + " - " + secondMaxNumber[1]);

		Collections.sort(listNumber, (Integer o1, Integer o2) -> o2 - o1);
		System.out.println(listNumber.toString());

		insertEle(listNumber, 1);
		System.out.println(listNumber.toString());

	}

	public static int[] findMaxNumber(List<Integer> listNumber) {
		int[] numbers = new int[] { 0, 0 };
		for (int i = 0; i < listNumber.size(); i++) {
			if (numbers[0] < listNumber.get(i)) {
				numbers[0] = listNumber.get(i);
				numbers[1] = i;
			}
		}
		return numbers;
	}

	public static int[] findMaxNumberSecond(List<Integer> listNumber, int maxNumber) {
		int[] numbers = new int[] { 0, 0 };
		for (int i = 0; i < listNumber.size(); i++) {
			int n = listNumber.get(i);
			if (numbers[0] < n && n != maxNumber) {
				numbers[0] = n;
				numbers[1] = i;
			}
		}
		return numbers;
	}

	public static void insertEle(List<Integer> listNumber, int n) {
		int max = listNumber.get(1);
		int min = listNumber.get(listNumber.size() - 1);
		if (n >= max) {
			listNumber.add(0, n);
		}

		if (n <= min) {
			listNumber.add(n);
		}

		if (n < max && n > min) {
			int index = 0;
			for (int i = 0; i < listNumber.size() - 1; i++) {
				if (n < listNumber.get(i) && n > listNumber.get(i + 1)) {
					index = i + 1;
				}
			}
			listNumber.add(index, n);
		}
	}

}
