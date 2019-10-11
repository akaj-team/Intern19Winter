package asiantech.internship.summer.javacore.excercise25;

public class Excercise25 {

    public static boolean isPrime(int n) {
        if (n > 1) {
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean isReversible(int n) {
        StringBuilder xau = new StringBuilder();
        String str = "" + n;
        xau.append(str);
        String check = "" + xau.reverse();
        if (str.equals(check)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isSumOfNumbersIsPrimeNumber(int n) {
        int T = 0;
        while (n != 0) {
            T += n % 10;
            if (!isPrime(T)) {
                return false;
            }
            n /= 10;
        }
        return true;
    }

    public static void main(String[] args) {
        int i, count = 0;
        System.out.println("cac so co 7 chu so thoa man dieu kien la: ");
        for (i = 1000001; i < 9999999; i += 2) {
            if (isPrime(i) && isSumOfNumbersIsPrimeNumber(i) && isReversible(i)) {
                System.out.println(" " + i);
                count++;
            }
        }
        System.out.println("\n Co " + count + " so thoa man");
    }
}
