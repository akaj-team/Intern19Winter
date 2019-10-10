package asiantech.internship.summer.java_core.ex26;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import asiantech.internship.summer.java_core.common.Common;

public class ArrayManipulation {

    public static void main(String[] args) {
        Logger log = Logger.getLogger(ArrayManipulation.class.getName());
        List<Integer> listNumber = new ArrayList<>();
        int n = Common.input("Nhập vào độ dài mảng: ");

        for (int i = 0; i < n; i++) {
            listNumber.add(Common.input("Nhập phần tử thứ " + i + ": "));
        }

        log.info(listNumber.toString());

        int[] maxNumbers = findMaxNumber(listNumber);
        log.info("Max number: " + maxNumbers[0] + " - " + maxNumbers[1]);

        int[] secondMaxNumber = findMaxNumberSecond(listNumber, maxNumbers[0]);
        log.info("Second max number: " + secondMaxNumber[0] + " - " + secondMaxNumber[1]);

        Collections.sort(listNumber, (Integer o1, Integer o2) -> o2 - o1);
        log.info(listNumber.toString());

        insertEle(listNumber, 3);
        log.info(listNumber.toString());

    }

    public static int[] findMaxNumber(List<Integer> listNumber) {
        int[] numbers = new int[]{0, 0};
        for (int i = 0; i < listNumber.size(); i++) {
            if (numbers[0] < listNumber.get(i)) {
                numbers[0] = listNumber.get(i);
                numbers[1] = i;
            }
        }
        return numbers;
    }

    public static int[] findMaxNumberSecond(List<Integer> listNumber, int maxNumber) {
        int[] numbers = new int[]{0, 0};
        for (int i = 0; i < listNumber.size(); i++) {
            int n = listNumber.get(i);
            if (numbers[0] < n && n != maxNumber) {
                numbers[0] = n;
                numbers[1] = i;
            }
        }
        return numbers;
    }

    public static void insertEle(List<Integer> listNumber, int n) {
        int max = listNumber.get(1);
        int min = listNumber.get(listNumber.size() - 1);
        if (n >= max) {
            listNumber.add(0, n);
        }

        if (n <= min) {
            listNumber.add(n);
        }

        if (n < max && n > min) {
            int index = 0;
            for (int i = 0; i < listNumber.size() - 1; i++) {
                if (n < listNumber.get(i) && n > listNumber.get(i + 1)) {
                    index = i + 1;
                }
            }
            listNumber.add(index, n);
        }
    }

}
