package asiantech.internship.summer.javacore;
public class Exercise25 {
    private static boolean isPrimeNumber(int n){
        if(n>1){
            for(int i=2;i<=Math.sqrt(n);i++){
                if(n%i==0) return false;
            }
            return true;
        }
        else return false;
    }
    private static boolean isReversibleNumber(int n){
        StringBuilder x= new StringBuilder();
        String str= ""+n;
        x.append(str);
        String check= "" + x.reverse();
        return str.equals(check);
    }
    private static boolean primeNumber(int n){
        while(n!=0){
            if(!isPrimeNumber(n%10)) return false;
            n/= 10;
        }
        return true;
    }
    public static void main(String[] args) {
        int i,count= 0;
        System.out.println("cac so tu 5-7 chu so thoa man dieu kien la: ");
        for(i=22223 ; i<7777777 ; i+=2){
            if(isPrimeNumber(i) && primeNumber(i) && isReversibleNumber(i)){
                System.out.println(" "+i); count++;
            }
        }
        System.out.println(" Co "+count+" so thoa man");
    }
}