package asiantech.internship.winter.test;

import java.util.Scanner;

//   in ra xuat hien so lan cua phan tu
public class number16 {
    public static int Input(){
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
    public static int countElement(int a[], int n, int i){
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
        n= Input();
        int[] array= new int[n];
        for(i=0 ; i<n ; i++){
            System.out.println("Nhap phan tu thu " +(i+1)+" ");
            array[i]= Input();
        }
        for(i=0 ; i<n ; i++){
            if(countElement(array, i, array[i])==0){
                System.out.println("Phan tu "+array[i]+ " xuat hien "+countElement(array, n,
                        array[i])+" lan");
            }
        }
    }
}
