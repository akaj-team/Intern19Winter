package asiantech.internship.summer.java_core.ex22;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import asiantech.internship.summer.java_core.common.Common;

public class Divisor {

    public static void main(String[] args) {
        Logger log = Logger.getLogger(Divisor.class.getName());
        int n = Common.input("Nhập vào số n:");
        List<Integer> listDiv = getListDiv(n);

        log.info("Danh sach uoc so: " + listDiv.toString());
        log.info("So luong uoc so: " + listDiv.size());
        log.info("Danh sach uoc so la so nguyen to: " + getListDivPrime(listDiv).toString());
    }

    public static List<Integer> getListDivPrime(List<Integer> listDiv) {
        List<Integer> lisDivPrime = new ArrayList<>();
        for (int i = 0; i < listDiv.size(); i++) {
            if (Common.isPrime(listDiv.get(i))) {
                lisDivPrime.add(listDiv.get(i));
            }
        }
        return lisDivPrime;
    }

    public static List<Integer> getListDiv(int n) {
        List<Integer> listDiv = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                listDiv.add(i);
            }
        }
        return listDiv;
    }

}
