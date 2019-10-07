package ex31;

import java.util.ArrayList;
import java.util.List;

public class Ex31 {

	public static void main(String[] args) {
		List<String> A = new ArrayList<>();
		A.add("Lan");
		A.add("Hằng");
		A.add("Minh");
		A.add("Thuỷ");

		List<String> B = new ArrayList<>();
		B.add("Nghĩa");
		B.add("Trung");
		B.add("Minh");
		B.add("Thuỷ");
		B.add("Đức");

		System.out.println("A: " + A.toString());
		System.out.println("B: " + B.toString());

		List<String> C = giao(A, B);
		System.out.println("Giao: " + C.toString());

		List<String> D = hop(A, B);
		System.out.println("Hợp: " + D.toString());
		
		List<String> E = hieu(A, B);
		System.out.println("Hiệu: " + E.toString());

	}

	public static List<String> hieu(List<String> A, List<String> B) {
		List<String> C = new ArrayList<>();
		C.addAll(A);
		for (String itemB : B) {
			if (A.contains(itemB)) {
				C.remove(itemB);
			}
		}

		return C;
	}

	public static List<String> hop(List<String> A, List<String> B) {
		List<String> C = new ArrayList<>();
		C.addAll(A);
		for (String itemB : B) {
			if (!A.contains(itemB)) {
				C.add(itemB);
			}
		}

		return C;
	}

	public static List<String> giao(List<String> A, List<String> B) {
		List<String> C = new ArrayList<>();
		for (String itemB : B) {
			if (A.contains(itemB)) {
				C.add(itemB);
			}
		}

		return C;
	}

}
