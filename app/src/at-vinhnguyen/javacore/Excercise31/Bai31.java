package Excercise31;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Bai31 {

    static ArrayList<String> A, B;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        File f = new File("D:/Excercise31.txt");
        StringBuilder builder;
        try (BufferedReader bufferReader = new BufferedReader(new FileReader(f))) {
            builder = new StringBuilder();
            String line = bufferReader.readLine();
            while (line != null) {
                builder.append(line);
                builder.append(System.lineSeparator());
                line = bufferReader.readLine();
            }
        }
        initArray(builder);
    }

    public static void initArray(StringBuilder str) {
        A = new ArrayList<>();
        B = new ArrayList<>();
        String a = str.substring(str.indexOf("[") + 1, str.indexOf("]", str.indexOf("[")));
        String b = str.substring(str.indexOf("[", str.indexOf(a) + a.length() + 1) + 1, str.lastIndexOf("]"));
        A = standardized(a);
        B = standardized(b);
        intersection(A, B);
        synthesis(A, B);
        difference(A, B);
    }

    public static ArrayList<String> standardized(String str) {
        ArrayList<String> a = new ArrayList<>();
        for (String strspl : str.split(", ")) {
            a.add(strspl.toString().substring(1, strspl.length() - 1));
        }
        return a;
    }

    public static void intersection(ArrayList<String> A, ArrayList<String> B) {
        ArrayList<String> C = new ArrayList<>();
        for (String A1 : A) {
            for (String B1 : B) {
                if (A1.equals(B1)) {
                    C.add(A1);
                }
            }
        }
        System.out.print("C = A∪B : ");
        for (String C1 : C) {
            System.out.print(" " + C1);
        }
        System.out.println("\n");
    }

    public static void synthesis(ArrayList<String> A, ArrayList<String> B) {
        ArrayList<String> C = new ArrayList<>();
        C.addAll(B);
        for (String subA : A) {
            boolean duplicate = false;
            for (String subB : B) {
                if (subB.equals(subA)) {
                    duplicate = true;
                    break;
                }
            }

            if (!duplicate) {
                C.add(subA);
            }
        }
        System.out.print("C = A∩B : ");
        for (String C1 : C) {
            System.out.print(" " + C1);
        }
        System.out.println("\n");
    }

    public static void difference(ArrayList<String> A, ArrayList<String> B) {
        ArrayList<String> C = new ArrayList<>();
        for (String subA : A) {
            boolean duplicate = false;
            for (String subB : B) {
                if (subB.equals(subA)) {
                    duplicate = true;
                    break;
                }
            }
            if (!duplicate) {
                C.add(subA);
            }
        }
        System.out.print("C = A\\B : ");
        for (String C1 : C) {
            System.out.print(" " + C1);
        }
        System.out.println("\n");
    }
}
