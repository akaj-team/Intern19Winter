package asiantech.internship.summer.java_core.exoop;

public class HinhVuong extends Hinh {

	private int canh;

	public HinhVuong(int canh) {
		this.canh = canh;
	}

	@Override
	public double cv() {
		return canh * 4;
	}

	@Override
	public double dt() {
		return canh * canh;
	}

	@Override
	public String print() {
		return "HV";
	}
}
