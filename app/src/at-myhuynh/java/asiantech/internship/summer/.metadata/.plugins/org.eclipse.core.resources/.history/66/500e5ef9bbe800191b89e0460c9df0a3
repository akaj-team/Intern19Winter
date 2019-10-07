package ex14;

import java.util.ArrayList;
import java.util.List;

public class ListNumberExistOnly {

	public static void main(String[] args) {
		List<Integer> listNumber = new ArrayList<>();
		listNumber.add(1);
		listNumber.add(2);
		listNumber.add(4);
		listNumber.add(2);
		listNumber.add(4);
		listNumber.add(5);
		listNumber.add(7);

		System.out.println(getNumbers(listNumber).toString());

	}

	public static List<Integer> getNumbers(List<Integer> listNumber) {
		List<Integer> listNum = new ArrayList<>();
		for (int i = 0; i < listNumber.size(); i++) {
			if (isExist(listNumber.get(i), listNumber, i)) {
				listNum.add(listNumber.get(i));
			}
		}
		return listNum;
	}

	public static boolean isExist(int n, List<Integer> listNumber, int index) {
		for (int i = 0; i < listNumber.size(); i++) {
			if (i != index) {
				if (n == listNumber.get(i)) {
					return false;
				}
			}
		}
		return true;
	}

}
