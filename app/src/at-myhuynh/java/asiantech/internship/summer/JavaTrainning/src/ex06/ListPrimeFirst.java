package ex06;

public class ListPrimeFirst {

	public static void main(String[] args) {
		int n = 10;
		print(n);
	}
	
	public static void print(int n){
		int count = 0;
		int number = 1;
		
		do {
			if(isPrime(number)){
				count++;
				System.out.println(number);
			}
			number++;
		} while (count < n);
	}
	
	public static boolean isPrime(int number) {
		if (number == 1 || number == 2) {
			return true;
		} else {
			for (int i = 2; i <= number / 2; i++) {
				if (number % i == 0) {
					return false;
				}
			}
		}
		return true;
	}

}
