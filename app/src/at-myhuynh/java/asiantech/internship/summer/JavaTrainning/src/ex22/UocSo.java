package ex22;

import java.util.ArrayList;
import java.util.List;

import common.Common;

public class UocSo {

	public static void main(String[] args) {
		int n = 30;
		List<Integer> listDiv = getListDiv(n);
		System.out.println("Danh sach uoc so: " + listDiv.toString());
		System.out.println("So luong uoc so: " + listDiv.size());
		System.out.println("Danh sach uoc so la so nguyen to: " + getListDivPrime(listDiv).toString());
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
