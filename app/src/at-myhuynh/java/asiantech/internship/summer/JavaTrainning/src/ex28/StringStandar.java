package ex28;

public class StringStandar {

	public static void main(String[] args) {
		String str = "    nguYen  vAn a  ";
		System.out.println(str);
		str = str.trim().toLowerCase();
		String[] arrstr = str.split("[ ]+");

		String result = "";
		for (String strItem : arrstr) {
			String temp = String.valueOf(strItem.charAt(0));
			strItem = strItem.replaceFirst(temp, temp.toUpperCase());
			result += strItem + " ";
		}

		System.out.println(result);

	}

}
