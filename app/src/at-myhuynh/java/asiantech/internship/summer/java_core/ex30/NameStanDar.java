package asiantech.internship.summer.java_core.ex30;

public class NameStanDar {

	public static void main(String[] args) {
		String name = "Nguyễn Văn A";
		
		String firstName = name.substring(0, name.indexOf(" "));
		String middleName = name.substring(name.indexOf(" ") + 1, name.lastIndexOf(" "));
		String lastName = name.substring(name.lastIndexOf(" ") + 1, name.length());

		String nameStand = lastName + " " + middleName + " " + firstName;
		System.out.println(name + " => " + nameStand);
	}

}
