package ex28;

public class StringStandar {

	public static void main(String[] args) {
		String str = "    nguYen  vAn a  ";
		System.out.println(str);
		str = str.trim().toLowerCase();
		String[] arrStr = str.split("[ ]+");

		for (int i = 0; i < arrStr.length; i++) {
			String temp = String.valueOf(arrStr[i].charAt(0));
			arrStr[i] = arrStr[i].replaceFirst(temp, temp.toUpperCase());
		}

		System.out.println(String.join(" ", arrStr));

	}

}
