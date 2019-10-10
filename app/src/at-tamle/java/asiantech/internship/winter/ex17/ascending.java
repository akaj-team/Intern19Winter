package asiantech.internship.winter.ex17;

import java.util.Scanner;
//day tang dan
public class ascending {
    public static int process(){
        Scanner input= new Scanner(System.in);
        boolean check= false;
        int n=0;
        while(!check){
            System.out.print(" ");
            try{
                n= input.nextInt();
                check= true;
            }catch(Exception e){
                System.out.println(" Ban phai nhap so! hay nhap lai");
                input.nextLine();
            }
        }
        return (n);
    }
    private static float innumber(){
        Scanner input= new Scanner(System.in);
        boolean check= false;
        float n=0;
        while(!check){
            System.out.print(" ");
            try{
                n= input.nextInt();
                check= true;
            }catch(Exception e){
                System.out.println(" Ban phai nhap so! hay nhap lai");
                input.nextLine();
            }
        }
        return (n);
    }
    private static int minnumber(float a[], int n){
        float min= a[0];
        int key= 0;
        for(int j=0 ; j<n ; j++){
            if(min>a[j]){
                min= a[j];
                key= j;
            }
        }
        return (key);
    }
    public static float maxnumber(float a[], int n){
        float max= a[0];
        for(int j=0 ; j<n ; j++){
            if(max<a[j]) max= a[j];
        }
        return (max);
    }
    public static void main(String[] args) {
        int n,i;
        System.out.println("Nhap n= ");
        n= process();
        float[] array= new float[n];
        for(i=0 ; i<n ; i++){
            System.out.println("Nhap phan tu thu " +(i+1)+" ");
            array[i]= innumber();
        }
        i =0;
        System.out.println("Sap xep theo thu tu tang dan");
        while(i<n){
            System.out.println(" "+array[minnumber(array, n)]);
            array[minnumber(array, n)]= maxnumber(array, n);
            i++;
        }
    }
}
