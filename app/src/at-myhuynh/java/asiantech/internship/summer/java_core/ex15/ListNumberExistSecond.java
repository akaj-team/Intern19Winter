package asiantech.internship.summer.java_core.ex15;

import java.util.ArrayList;
import java.util.List;

public class ListNumberExistSecond {

	public static void main(String[] args) {
		List<Integer> listNumber = new ArrayList<>();
		listNumber.add(1);
		listNumber.add(2);
		listNumber.add(4);
		listNumber.add(2);
		listNumber.add(2);
		listNumber.add(4);
		listNumber.add(5);
		listNumber.add(5);
		listNumber.add(7);
		listNumber.add(7);

		System.out.println(getNumbers(listNumber));
	}

	public static List<Integer> getNumbers(List<Integer> listNumber) {
		List<Integer> listNum = new ArrayList<>();
		int count = 0;
		for (int i = 0; i < listNumber.size(); i++) {
			count = count(listNumber.get(i), listNumber, i);
			if (count == 2 && !isExist(listNumber.get(i), listNum)) {
				listNum.add(listNumber.get(i));
			}
		}
		return listNum;
	}

	public static boolean isExist(int n, List<Integer> listNumber) {
		for (int i = 0; i < listNumber.size(); i++) {
			if (n == listNumber.get(i)) {
				return true;
			}
		}
		return false;
	}

	public static int count(int n, List<Integer> listNumber, int index) {
		int count = 1;
		for (int i = 0; i < listNumber.size(); i++) {
			if (i != index) {
				if (n == listNumber.get(i)) {
					count++;
				}
			}
		}
		return count;
	}

}