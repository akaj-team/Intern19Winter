package asiantech.internship.summer.java_core.ex31;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FindCombination {

    public static void main(String[] args) {
        Logger log = Logger.getLogger(FindCombination.class.getName());
        List<String> a = new ArrayList<>();
        a.add("Lan");
        a.add("Hằng");
        a.add("Minh");
        a.add("Thuỷ");

        List<String> b = new ArrayList<>();
        b.add("Nghĩa");
        b.add("Trung");
        b.add("Minh");
        b.add("Thuỷ");
        b.add("Đức");

        if (log.isLoggable(Level.INFO)) {
            log.info("A: " + a.toString());
            log.info("B: " + a.toString());
        }

        List<String> c = interSection(a, b);
        List<String> d = union(a, b);
        List<String> e = difference(a, b);

        if (log.isLoggable(Level.INFO)) {
            log.info("Giao: " + c.toString());
            log.info("Hợp: " + d.toString());
            log.info("Hiệu: " + e.toString());
        }

    }

    private static List<String> difference(List<String> a, List<String> b) {
        List<String> c = new ArrayList<>(a);
        for (String itemB : b) {
            if (a.contains(itemB)) {
                c.remove(itemB);
            }
        }

        return c;
    }

    private static List<String> union(List<String> a, List<String> b) {
        List<String> c = new ArrayList<>(a);
        for (String itemB : b) {
            if (!a.contains(itemB)) {
                c.add(itemB);
            }
        }

        return c;
    }

    private static List<String> interSection(List<String> a, List<String> b) {
        List<String> c = new ArrayList<>();
        for (String itemB : b) {
            if (a.contains(itemB)) {
                c.add(itemB);
            }
        }

        return c;
    }
}
