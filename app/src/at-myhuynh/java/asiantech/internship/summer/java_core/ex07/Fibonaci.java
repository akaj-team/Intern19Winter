package asiantech.internship.summer.java_core.ex07;

public class Fibonaci {

	public static void main(String[] args) {
		int n = 10;
		int count = 2;
		int f1 = 1;
		int f2 = 1;
		int fn = 0;
		
		do {
			fn = f1 + f2;
			f1 = f2;
			f2 = fn;
			count++;
		} while (count < n);
		
		System.out.println("Số fibonaci thứ " + n + ": " + fn);
	}

}
