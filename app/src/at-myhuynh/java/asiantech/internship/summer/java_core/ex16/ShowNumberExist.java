package asiantech.internship.summer.java_core.ex16;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import asiantech.internship.summer.java_core.common.Common;

public class ShowNumberExist {

    public static void main(String[] args) {
        List<Integer> listNumber = new ArrayList<>();
        int n = Common.input("Nhập vào độ dài mảng: ");
        for (int i = 0; i < n; i++) {
            listNumber.add(Common.input("Nhập phần tử thứ " + i + ": "));
        }

        showNumberExist(listNumber);
    }

    public static void showNumberExist(List<Integer> listNumber) {
        Logger log = Logger.getLogger(ShowNumberExist.class.getName());
        for (int i = 0; i < listNumber.size(); i++) {
            if (isExist(listNumber.get(i), listNumber, i)) {
                log.info(listNumber.get(i) + ": " + countNumberExist(listNumber.get(i), listNumber, i));
            }
        }
    }

    public static int countNumberExist(int n, List<Integer> listNumber, int index) {
        int count = 1;
        for (int i = index + 1; i < listNumber.size(); i++) {
            if (n == listNumber.get(i) && isExist(n, listNumber, index)) {
                count++;
            }
        }
        return count;
    }

    public static boolean isExist(int n, List<Integer> listNumber, int index) {
        for (int i = 0; i < index; i++) {
            if (n == listNumber.get(i)) {
                return false;
            }
        }
        return true;
    }

}
