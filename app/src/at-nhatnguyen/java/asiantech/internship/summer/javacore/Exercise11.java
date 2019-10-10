package asiantech.internship.summer.javacore;
import java.util.Scanner;
public class Exercise11 {
    private static int input(){
        Scanner scan= new Scanner(System.in);
        boolean check= false;
        int n=0;
        while(!check){
            System.out.print(" ");
            try{
                n= scan.nextInt();
                check= true;
            }catch(Exception e){
                System.out.println("Ban phai nhap so");
                scan.nextLine();
            }
        }
        return (n);
    }
    public static void main(String[] args) {
        System.out.print("Nhap n");
        int n= input();
        int[] array= new int[n+2];
        int i,j,k = n-1,temp,check=1;
        for(i=0 ; i<n ; i++){
            array[i]= i+1;
        }
        System.out.println("Cac hoan vi ke la: ");
        try{
            i= n-2;
            while(check>0){
                //In ra hoan vi System.out.println(" ");
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
                            //Tim so a[k] nho nhat ma >a[i] trong cac so ben phai a[i]
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
        }
        catch(Exception e){}
    }
}