package ex18;

public class CountWord {

	public static void main(String[] args) {
		String strWord = "Truong hoc";
		int count = strWord.split(" ").length;
		System.out.println(strWord + " có " + count + " từ.");
	}

}
