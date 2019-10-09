package asiantech.internship.winter.test;
//liet ke all so thuan nghich 6 chu so
public class number8 {
    public static boolean test(int n) {
        StringBuilder xau = new StringBuilder();
        String str = "" + n;
        xau.append(str);
        String check = "" + xau.reverse();
        if (str.equals(check))
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        int n, count = 0;
        for (n = 100000; n <= 999999; n++) {
            if (test(n)) {
                System.out.println(n);
                count++;
            }
        }
        System.out.println("Co " + count + " so thuan nghich co 6 chu so");
    }

}
