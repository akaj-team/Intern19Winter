package asiantech.internship.summer.java_core.ex18;

public class CountWord {

	public static void main(String[] args) {
		String strWord = "Truong hoc cua em";
		int count = strWord.split(" ").length;
		System.out.println("'" + strWord + "' có " + count + " từ.");
	}

}
