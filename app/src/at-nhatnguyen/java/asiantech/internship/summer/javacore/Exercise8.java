package asiantech.internship.summer.javacore;
public class Exercise8 {
        public static boolean isReversibleNumber(int n){
            StringBuilder x = new StringBuilder();
            String str= ""+n;
            x.append(str);
            String check= ""+x.reverse();
            if(str.equals(check)) return true;
            else return false;
        }
        public static void main(String[] args) {
            int n,count=0;
            for(n=100000 ; n<= 999999 ; n++){
                if(isReversibleNumber(n)){
                    System.out.println(n);count++;
                }
            }
            System.out.println("Co "+count+" so thuan nghich co 6 chu so");
        }
    }

