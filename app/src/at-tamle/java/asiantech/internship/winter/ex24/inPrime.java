package asiantech.internship.winter.ex24;
//Là số nguyên tố.
//Là số thuận nghịch.
//Mỗi chữ số đều là số nguyên tố
public class inPrime {
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
        StringBuilder New= new StringBuilder();
        String str= ""+n;
        New.append(str);
        String check= ""+New.reverse();
        if(str.equals(check)) return true;
        else return false;
    }
    public static boolean element(int n){
        while(n!=0){
            if(!incheck(n%10)) return false;
            n/= 10;
        }
        return true;
    }
    public static void main(String[] args) {
        int i,count= 0;
        System.out.println("cac so tu 5-7 chu so thoa man dieu kien la: ");
        for(i=22223 ; i<7777777 ; i+=2){
            if(incheck(i) && element(i) && testTotal(i)){
                System.out.println(" "+i); count++;
            }
        }
        System.out.println("\n Co "+count+" so thoa man");
    }
}
