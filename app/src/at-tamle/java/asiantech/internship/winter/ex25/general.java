package asiantech.internship.winter.ex25;
//Là số nguyên tố.
//Là số thuận nghịch.
//Tổng  các chữ số của số đó là một số thuận nghịch
public class general {

    public static boolean incheck(int n){
        if(n>1){
            for(int i=2;i<=Math.sqrt(n);i++){
                if(n%i==0) return false;
            }
            return true;
        }
        else return false;
    }
    public static boolean testTotal(int n){
        StringBuilder test= new StringBuilder();
        String str= ""+n;
        test.append(str);
        String check= ""+test.reverse();
        if(str.equals(check)) return true;
        else return false;
    }
    public static boolean equal(int n){
        int T= 0;
        while(n!=0){
            T+= n%10;
            if(!incheck(T)) return false;
            n/= 10;
        }
        return true;
    }
    public static void main(String[] args) {
        int i,count= 0;
        System.out.println("cac so tu 7 chu so thoa man dieu kien la: ");
        for(i=22223 ; i<7777777 ; i+=2){
            if(incheck(i) && equal(i) && testTotal(i)){
                System.out.println(" "+i); count++;
            }
        }
        System.out.println("\n Co "+count+" so thoa man");
    }
}
