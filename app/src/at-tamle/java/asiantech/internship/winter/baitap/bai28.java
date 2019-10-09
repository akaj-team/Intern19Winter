package asiantech.internship.winter.baitap;
import java.util.*;
//Viết chương trình thực hiện chuẩn hoá một xâu ký tự nhập từ bàn phím
//loại bỏ các dấu cách thừa, chuyển ký tự đầu mỗi từ thành chữ hoa, các ký tự khác thành chữ thường)
public class bai28 {
	
	
	 public static String chuyenInHoa(String str){ 
	                String s,b;
	                s= str.substring(0, 1);
	 b= str.replaceFirst(s,s.toUpperCase()); 
	                return (b);
	        }
	 public static String chuanHoa(String strInput){ 
	                String b="";
	                StringTokenizer strToken= new StringTokenizer(strInput," ,\t,\r"); 
	                b+=""+chuyenInHoa(strToken.nextToken()); while(strToken.hasMoreTokens()){
	                   b+=" "+chuyenInHoa(strToken.nextToken());
	                }
	               return(b);
	       }
	       public static void main(String[] args) {
	 
	 Scanner input= new Scanner(System.in); 
	 System.out.println("Nhap vao 1 xau: "); 
	            String strInput= input.nextLine();
	            System.out.println("Xau duoc chuan hoa la: "+chuanHoa(strInput));
	      } 
	} 

