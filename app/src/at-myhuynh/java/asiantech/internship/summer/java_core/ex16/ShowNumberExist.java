package asiantech.internship.summer.java_core.ex16;

import java.util.ArrayList;
import java.util.List;

public class ShowNumberExist {

	public static void main(String[] args) {
		List<Integer> listNumber = new ArrayList<>();
		listNumber.add(1);
		listNumber.add(2);
		listNumber.add(4);
		listNumber.add(2);
		listNumber.add(4);
		listNumber.add(5);
		listNumber.add(7);
		listNumber.add(4);
		listNumber.add(5);
		listNumber.add(7);
		
		showNumberExist(listNumber);
	}

	public static void showNumberExist(List<Integer> listNumber) {
		for (int i = 0; i < listNumber.size(); i++) {
			if(isExist(listNumber.get(i), listNumber, i)){
				System.out.println(listNumber.get(i) + ": " + countNumberExist(listNumber.get(i), listNumber, i));
			}
		}
	}

	public static int countNumberExist(int n, List<Integer> listNumber, int index) {
		int count = 1;
		for (int i = index + 1; i < listNumber.size(); i++) {
			if (n == listNumber.get(i) && isExist(n, listNumber, index)) {
				count++;
			}
		}
		return count;
	}

	public static boolean isExist(int n, List<Integer> listNumber, int index) {
		for (int i = 0; i < index; i++) {
			if (n == listNumber.get(i)) {
				return false;
			}
		}
		return true;
	}

}
