package asiantech.internship.summer.java_core.ex31;

import java.util.ArrayList;
import java.util.List;
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

        log.info("A: " + a.toString());
        log.info("B: " + a.toString());

        List<String> c = interSection(a, b);
        log.info("Giao: " + c.toString());

        List<String> d = union(a, b);
        log.info("Hợp: " + d.toString());

        List<String> e = difference(a, b);
        log.info("Hiệu: " + e.toString());

    }

    public static List<String> difference(List<String> a, List<String> b) {
        List<String> c = new ArrayList<>(a);
        for (String itemB : b) {
            if (a.contains(itemB)) {
                c.remove(itemB);
            }
        }

        return c;
    }

    public static List<String> union(List<String> a, List<String> b) {
        List<String> c = new ArrayList<>(a);
        for (String itemB : b) {
            if (!a.contains(itemB)) {
                c.add(itemB);
            }
        }

        return c;
    }

    public static List<String> interSection(List<String> a, List<String> b) {
        List<String> c = new ArrayList<>();
        for (String itemB : b) {
            if (a.contains(itemB)) {
                c.add(itemB);
            }
        }

        return c;
    }
}
