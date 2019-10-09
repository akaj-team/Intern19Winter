package asiantech.internship.winter.javacore;

import java.util.HashSet;
import java.util.Scanner;

public class Bai31 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter n: ");
        int n = scanner.nextInt();
        HashSet<String> setA = new HashSet<>();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.print("Nhap phan tu thu " + (i + 1) + ": ");
            setA.add(scanner.nextLine());
        }


        System.out.println("Enter m: ");
        int m = scanner.nextInt();
        HashSet<String> setB = new HashSet<>();
        scanner.nextLine();
        for (int i = 0; i < m; i++) {
            System.out.print("Nhap phan tu thu " + (i + 1) + ": ");
            setB.add(scanner.nextLine());
        }
        System.out.println("A= " + setA);
        System.out.println("B= " + setB);

        HashSet<String> unionSet = (HashSet<String>) setB.clone();
        unionSet.addAll(setA);
        System.out.println("Kết quả sau khi thực hiện toán tử hợp: ");
        System.out.println(unionSet);

        HashSet<String> intersection = (HashSet<String>) setA.clone();
        intersection.retainAll(setB);
        System.out.println("Kết quả sau khi thực hiện toán tử giao: ");
        System.out.println(intersection);

        HashSet<String> setC = (HashSet<String>) setA.clone();
        setC.removeAll(intersection);
        System.out.println("Tap hop cac phan tu thuoc A nhung khong thuoc B");
        System.out.println(setC);

    }
}


