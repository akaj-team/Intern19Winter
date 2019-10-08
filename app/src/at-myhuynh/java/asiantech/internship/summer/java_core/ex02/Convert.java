package asiantech.internship.summer.java_core.ex02;

public class Convert {

	public static void main(String[] args) {
		int n = 100;
		int b = n;
		String strConvert = "";
		int temp = 0;
		while (n > 0) {
			temp = n % 2;
			if (temp == 0) {
				strConvert += "0";
			} else {
				strConvert += "1";
			}
			n /= 2;
		}
		System.out.println(b + " => " + new StringBuffer(strConvert).reverse());
	}

}
