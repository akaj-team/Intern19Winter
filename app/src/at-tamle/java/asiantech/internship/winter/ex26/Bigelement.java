package asiantech.internship.winter.ex26;

import java.util.Arrays;
import java.util.Scanner;
//Tìm phần tử lớn nhất và lớn thứ 2 trong mảng cùng chỉ số của các số đó.
public class Bigelement {
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
                System.out.println("Ban phai nhap so! hay nhap lai...");
                input.nextLine();
            }
        }
        return (n);
    }
    public static int MaxInt(int a[], int n){ int max= a[0];
        int key= 0;
        for(int j=0 ; j<n ; j++){
            if(max<a[j]){
                max= a[j];
                key= j;
            }
        }
        return (key);
    }
    public static void inArray(int[] a, int begin , int end){
        System.out.println();
        int i;
        for(i=begin ; i<end ; i++){
            System.out.print(" "+a[i]);
        }
        System.out.println();
    }
    public static int Max2(int[] a,int n){
        int i,key=0,Max2=0;
        for(i=0 ; i<n ; i++){
            if(a[i]>Max2 && a[i]!= a[MaxInt(a, n)]){
                Max2= a[i];key= i;
            }
        }return (key);
    }
    public static void New(int[] a,int n,int pt){
        a[0]= pt;
        Arrays.sort(a);
    }
    public static void main(String[] args) {
        System.out.print("Nhap n= ");
        int n= process();
        int[] a= new int[n+1];
        int i;
        for(i=0 ; i<n ; i++){
            System.out.print("\n Nhap phan tu thu "+i+" = ");
            a[i]= process();
        }
        for(i=0 ; i<n ; i++){
            if(a[i]== a[Max2(a, n)]) System.out.println(" Phan tu thu "+i+" lon thu 2 trong mang a["+i+"]= "+a[i]);
        }
        Arrays.sort(a); inArray(a,1,n+1);
        System.out.print("Nhap phan tu muon them pt= "); int pt= process();
        New(a,n+1,pt); inArray(a,0,n);
    }
}
