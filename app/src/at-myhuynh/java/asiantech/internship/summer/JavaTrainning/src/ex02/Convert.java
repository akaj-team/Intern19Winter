package ex02;
/**
 * Convert cơ số 10 sang cơ số 2
 */
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
