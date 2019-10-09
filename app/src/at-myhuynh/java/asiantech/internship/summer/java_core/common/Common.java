package asiantech.internship.summer.java_core.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Common {

	public static int input(String text) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int n = 0;
		boolean check = true;
		
		do {
			try {
				System.out.print(text);
				n = Integer.parseInt(sc.nextLine());
				check = false;
			} catch (Exception e) {
				check = true;
				System.out.println(e);
				System.err.println("Nhập lại!!");
			}
		} while (check);
		
		return n;
	}

	public static boolean thuanNghich(String text) {
		return text.equals(new StringBuffer(text).reverse().toString());
	}

	public static boolean isPrime(int number) {
		if (number == 1 || number == 2) {
			return true;
		} else {
			for (int i = 2; i <= number / 2; i++) {
				if (number % i == 0) {
					return false;
				}
			}
		}
		return true;
	}

	public static List<String> primeFactor(int number) {
		List<String> listPrime = new ArrayList<>();
		int index = 2;
		while (number >= index) {
			if (number % index == 0) {
				listPrime.add(String.valueOf(index));
				number /= index;
			} else {
				index++;
			}
		}
		return listPrime;
	}

	public static int totalOfNumber(int number) {
		int total = 0;

		while (number > 0) {
			total += number % 10;
			number /= 10;
		}
		return total;
	}

	public static List<Integer> getListFibonaci(int n) {
		List<Integer> listFibo = new ArrayList<>();
		int count = 2;
		int f1 = 1;
		int f2 = 1;
		int fn = 1;

		listFibo.add(f1);
		listFibo.add(f2);

		do {
			fn = f1 + f2;
			f1 = f2;
			f2 = fn;
			listFibo.add(fn);
			count++;
		} while (count < n);

		return listFibo;
	}

	public static String joinString(String delimiter, Object[] arrStr) {
		String result = "";
		for (int i = 0; i < arrStr.length; i++) {
			if (i == arrStr.length - 1) {
				result += arrStr[i];
			} else {
				result += arrStr[i] + delimiter;
			}
		}
		return result;
	}
}
