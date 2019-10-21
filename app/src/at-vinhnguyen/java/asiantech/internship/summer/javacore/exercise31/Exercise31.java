package asiantech.internship.summer.javacore.exercise31;

import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Exercise31 {

    private static final String TAG = Exercise31.class.getSimpleName();

    public static void main(String[] args) throws IOException {
        File f = new File("asiantech/internship/summer/javacore/Excercise31.txt");
        try (InputStream inputStream = new FileInputStream(f)) {
            StringBuilder builder;
            try (BufferedReader bufferReader = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
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
    }

    private static void initArray(StringBuilder str) {
        ArrayList<String> a1, b1;
        String a = str.substring(str.indexOf("[") + 1, str.indexOf("]", str.indexOf("[")));
        String b = str.substring(str.indexOf("[", str.indexOf(a) + a.length() + 1) + 1,
                str.lastIndexOf("]"));
        a1 = standardized(a);
        b1 = standardized(b);
        intersection(a1, b1);
        synthesis(a1, b1);
        difference(a1, b1);
    }

    private static ArrayList<String> standardized(String str) {
        ArrayList<String> a = new ArrayList<>();
        for (String strspl : str.split(", ")) {
            a.add(strspl.substring(1, strspl.length() - 1));
        }
        return a;
    }

    private static void intersection(ArrayList<String> A, ArrayList<String> B) {
        ArrayList<String> C = new ArrayList<>();
        for (String A1 : A) {
            for (String B1 : B) {
                if (A1.equals(B1)) {
                    C.add(A1);
                }
            }
        }
        Log.d(TAG, "C = A∪B : ");
        for (String C1 : C) {
            Log.d(TAG, " " + C1);
        }
        Log.d(TAG, "\n");
    }

    private static void synthesis(ArrayList<String> A, ArrayList<String> B) {
        ArrayList<String> C = new ArrayList<>(B);
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
        Log.d(TAG, "C = A∩B : ");
        for (String C1 : C) {
            Log.d(TAG, " " + C1);
        }
        Log.d(TAG, "\n");
    }

    private static void difference(ArrayList<String> A, ArrayList<String> B) {
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
        Log.d(TAG, "C = A\\B : ");
        for (String C1 : C) {
            Log.d(TAG, " " + C1);
        }
        Log.d(TAG, "\n");
    }
}
