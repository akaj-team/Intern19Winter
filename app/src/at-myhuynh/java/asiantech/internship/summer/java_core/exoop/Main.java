package asiantech.internship.summer.java_core.exoop;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {

		Hinh hinhCN = new HinhChuNhat(3, 4);
		Hinh hinhV = new HinhVuong(5);
		Hinh hinhTron = new HinhTron(3);

		List<Hinh> hinhs = new ArrayList<>();
		hinhs.add(hinhTron);
		hinhs.add(hinhV);
		hinhs.add(hinhCN);

		for (Hinh hinh : hinhs) {
			System.out.println(hinh.print() + ": " + hinh.cv() + " - " + hinh.dt());
		}
	}
}
