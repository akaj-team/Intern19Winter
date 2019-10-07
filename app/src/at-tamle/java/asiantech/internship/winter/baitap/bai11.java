package baitap;
import java.util.*;
public class bai11 {
	 public static int nhap(){
		 Scanner input= new Scanner(System.in); 
		                boolean check= false;
		 int n=0; 
		                while(!check){
		System.out.print(" "); 
		                       try{
		 n= input.nextInt(); 
		                             check= true;
		                       }catch(Exception e){
		 System.out.println("Ban phai nhap so! hay nhap lai..."); 
		                              input.nextLine();
		                       }
		                }
		                return (n);
		      }
		 public static void main(String[] args) { 
		    System.out.print("Nhap n");
		            int n= nhap();
		 int[] array= new int[n+2]; 
		 int i,j,k=n-1,temp,check=1; 
		            for(i=0 ; i<n ; i++){
		                      array[i]= i+1;
		            }
		 System.out.println("Cac hoan vi ke la: "); 
		            try{
		 i= n-2; 
		                    while(check>0){
	
		                          for(j=0 ; j<n ; j++){
		      System.out.print(" "+array[j]);
		                          }
		 for(i= n-2 ; i>=0 ; i--){ 
		                                       check= 1;
		                          if(array[i] < array[i+1]){
		                                  if(i==n-2){
		 temp= array[i]; 
		 array[i]= array[n-1]; 
		 array[n-1]= temp; 
		                                        break;
		                                  }
		   else{
		  
		   k= i+1;
for(j=i+1 ; j<n ; j++){
		   if(array[i+1]>array[j] && array[j]>array[i]) k=j;
		                                            }
		  
		 temp= array[i]; 
		 array[i]= array[k]; 
		          array[k]= temp;
		
		      for(j=i+1 ; j<n ; j++){
		 for(int m= i+1 ; m<n ; m++){ 
		           if(array[j]<array[m]){
		 temp= array[j]; 
		 array[j]= array[m]; 
		                                                               array[m]= temp;
		                                                       }
		 }
		 }
		 break;
		 }
		            }
		   else {
		    check=0;

		                   }
		                   }
		                  
		              }
		          }catch(Exception e){}
		  }
}
