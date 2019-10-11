package asiantech.internship.summer.java_core.ex29;

import java.util.logging.Level;
import java.util.logging.Logger;

import asiantech.internship.summer.java_core.common.Common;

public class FindWord {

    public static void main(String[] args) {
        Logger log = Logger.getLogger(FindWord.class.getName());
        String str = Common.inputString("Nhập vào chuỗi kí tự:");
        str = str.replaceAll("\\s", " ");
        String[] arrWords = str.split("[ ]+");

        int[] maxLengthWord = getMaxLengthWord(arrWords);
        if (log.isLoggable(Level.INFO)) {
            log.info("Từ: " + arrWords[maxLengthWord[1]]
                    + "\nVị trí: " + (maxLengthWord[1] + 1)
                    + "\nĐộ dài: " + maxLengthWord[0]);
        }
    }

    public static int[] getMaxLengthWord(String[] arrWords) {
        int[] arr = new int[]{0, 0};
        for (int i = 0; i < arrWords.length; i++) {
            if (arr[0] < arrWords[i].length()) {
                arr[0] = arrWords[i].length();
                arr[1] = i;
            }
        }
        return arr;
    }

}
