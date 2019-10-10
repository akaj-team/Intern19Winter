package asiantech.internship.winter.ex8;
 //liet ke all so thuan nghich 6 chu so
public class reversible {
    public static boolean process(int n) {
        StringBuilder k = new StringBuilder();
        String str = "" + n;
        k.append(str);
        String check = "" + k.reverse();
        if (str.equals(check))
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        int n, count = 0;
        for (n = 100000; n <= 999999; n++) {
            if (process(n)) {
                System.out.println(n);
                count++;
            }
        }
        System.out.println("Co " + count + " so thuan nghich co 6 chu so");
    }

}
