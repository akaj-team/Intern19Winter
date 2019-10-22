package asiantech.internship.summer.java_core.ex15;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import asiantech.internship.summer.java_core.common.Common;

public class ListNumberExistTwice {

    public static void main(String[] args) {
        Logger log = Logger.getLogger(ListNumberExistTwice.class.getName());
        List<Integer> listNumber = new ArrayList<>();
        int n = Common.input("Nhập vào độ dài mảng: ");
        for (int i = 0; i < n; i++) {
            listNumber.add(Common.input("Nhập phần tử thứ " + i + ": "));
        }

        log.info(getNumbers(listNumber).toString());
    }

    private static List<Integer> getNumbers(List<Integer> listNumber) {
        List<Integer> listNum = new ArrayList<>();
        int count;
        for (int i = 0; i < listNumber.size(); i++) {
            count = count(listNumber.get(i), listNumber, i);
            if (count == 2 && !isExist(listNumber.get(i), listNum)) {
                listNum.add(listNumber.get(i));
            }
        }
        return listNum;
    }

    private static boolean isExist(int n, List<Integer> listNumber) {
        for (int i = 0; i < listNumber.size(); i++) {
            if (n == listNumber.get(i)) {
                return true;
            }
        }
        return false;
    }

    private static int count(int n, List<Integer> listNumber, int index) {
        int count = 1;
        for (int i = 0; i < listNumber.size(); i++) {
            if (i != index) {
                if (n == listNumber.get(i)) {
                    count++;
                }
            }
        }
        return count;
    }
}
