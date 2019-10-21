package asiantech.internship.summer.javacore.exercise16;

import android.util.Log;
import java.util.Map;
import java.util.TreeMap;

import static asiantech.internship.summer.javacore.InputUtils.inputIntNumber;

public class TheNumberOfOccurrencesOfTheElement {

    private static final String TAG = TheNumberOfOccurrencesOfTheElement.class.getSimpleName();

    public static void main(String[] args) {
        Log.d(TAG, "Nhập số phần tử của mảng: ");
        int n = inputIntNumber(TAG);
        int[] arr = new int[n];
        Log.d(TAG, "Nhập các phần tử của mảng: \n");
        for (int i = 0; i < n; i++) {
            Log.d(TAG, "a[" + i + "] = ");
            arr[i] = inputIntNumber(TAG);
        }
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            addElement(map, arr[i]);
        }
        for (Integer key : map.keySet()) {
            Log.d(TAG, key + " xuất hiện " + map.get(key) + " lần.\n");
        }
    }

    private static void addElement(Map<Integer, Integer> map, int element) {
        if (map.containsKey(element)) {
            int count = map.get(element) + 1;
            map.put(element, count);
        } else {
            map.put(element, 1);
        }
    }
}
