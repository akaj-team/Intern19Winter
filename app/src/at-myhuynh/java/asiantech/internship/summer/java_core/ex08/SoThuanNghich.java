package asiantech.internship.summer.java_core.ex08;

public class SoThuanNghich {

	public static void main(String[] args) {
		String str = "abcdcba";
		
		String[] arrStr = str.split("");
		int first = 0;
		int last = arrStr.length -1;
		boolean check = true;
		
		while(first < last) {
			if(!arrStr[first++].equals(arrStr[last--])){
				check = false;
				break;
			}
		}
		
		if(check){
			System.out.println(str + ": Thuan nghich");
		} else {
			System.out.println(str + ": Khong Thuan nghich");
		}
		
		//Or Use StringBuffer
		System.out.println(str.equals(new StringBuffer(str).reverse().toString()));
		
	}
	
}
