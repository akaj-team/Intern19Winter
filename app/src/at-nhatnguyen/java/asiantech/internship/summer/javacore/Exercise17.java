package asiantech.internship.summer.javacore;
import java.util.Scanner;
public class Exercise17 {
    public static int input(){
        Scanner scan= new Scanner(System.in);
        boolean check= false;
        int n=0;
        while(!check){
            System.out.print(" ");
            try{
                n= scan.nextInt();
                check= true;
            }catch(Exception e){
                System.out.println("Ban phai nhap so ");
                scan.nextLine();
            }
        }
        return (n);
    }
    private static float inputFloat(){
        Scanner input= new Scanner(System.in);
        boolean check= false;
        float n=0;
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
    private static int minFloat(float[] a, int n){
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
    private static float maxFloat(float[] a, int n){
        float max= a[0];
        for(int j=0 ; j<n ; j++){
            if(max<a[j]) max= a[j];
        }
        return (max);
    }
    public static void main(String[] args) {
        int n,i;
        System.out.println("Nhap n= ");
        n= input();
        float[] array= new float[n];
        for(i=0 ; i<n ; i++){
            System.out.println("Nhap phan tu thu " +(i+1)+" ");
            array[i]= inputFloat();
        }
        i =0;
        System.out.println("Sap xep theo thu tu tang dan");
        while(i<n){
            System.out.println(" "+array[minFloat(array, n)]);
            array[minFloat(array, n)]= maxFloat(array, n);
            i++;
        }
    }
}