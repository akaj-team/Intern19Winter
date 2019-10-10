package asiantech.internship.summer.javacore;
import java.util.Scanner;
public class Exercise15 {
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
                System.out.println("Ban phai nhap so:");
                scan.nextLine();
            }
        }
        return (n);
    }
    private static int countElement(int[] a, int n, int i){
        int count= 0;
        for(int j=0 ; j<n ; j++){
            if(a[j]== i)
                count ++;
        }
        return (count);
    }
    public static void main(String[] args) {
        int n,i;
        System.out.println("Nhap n= ");
        n= input();
        int[] array= new int[n];
        for(i=0 ; i<n ; i++){
            System.out.println("Nhap phan tu thu " +(i+1)+" ");
            array[i]= input();
        }
        System.out.print("Cac phan tu trong day xuat hien 2 lan: ");
        for(i=0 ; i<n ; i++){
            if(countElement(array, n, array[i])==2 && countElement(array, i, array[i])==0){
                System.out.print(" "+array[i]);
            }
        }
    }
}