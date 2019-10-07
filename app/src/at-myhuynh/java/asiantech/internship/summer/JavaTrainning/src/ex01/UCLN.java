package ex01;

import java.util.Scanner;

public class UCLN {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println();
		int a = Integer.parseInt(sc.nextLine());
		int b = Integer.parseInt(sc.nextLine());
		System.out.println(ucln(a, b));
		System.out.println(bcnn(a, b));
	}
	
	public static int ucln(int a, int b) {
		while (a != b) {
			if(a > b){
				a -= b;
			} else {
				b -= a;
			}
		}
		return a;
	}
	
	public static int bcnn(int a, int b){
		int index = a > b ? a : b;
		while(true) {
			if(index % a == 0 && index % b ==0) {
				break;
			}
			index++;
		}
		return index;
	}

}
