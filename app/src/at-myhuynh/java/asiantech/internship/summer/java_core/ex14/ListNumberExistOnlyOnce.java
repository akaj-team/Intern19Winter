package asiantech.internship.summer.java_core.ex14;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import asiantech.internship.summer.java_core.common.Common;

public class ListNumberExistOnlyOnce {

    public static void main(String[] args) {
        Logger log = Logger.getLogger(ListNumberExistOnlyOnce.class.getName());
        List<Integer> listNumber = new ArrayList<>();
        int n = Common.input("Nhập vào độ dài mảng: ");
        for (int i = 0; i < n; i++) {
            listNumber.add(Common.input("Nhập phần tử thứ " + i + ": "));
        }

        log.info(getNumbers(listNumber).toString());
    }

    public static List<Integer> getNumbers(List<Integer> listNumber) {
        List<Integer> listNum = new ArrayList<>();
        for (int i = 0; i < listNumber.size(); i++) {
            if (isExist(listNumber.get(i), listNumber, i)) {
                listNum.add(listNumber.get(i));
            }
        }
        return listNum;
    }

    public static boolean isExist(int n, List<Integer> listNumber, int index) {
        for (int i = 0; i < listNumber.size(); i++) {
            if (i != index) {
                if (n == listNumber.get(i)) {
                    return false;
                }
            }
        }
        return true;
    }
}
