package exoop;

public class HinhChuNhat extends Hinh {

	private int cd;
	private int cr;
	
	public HinhChuNhat(int cd, int cr) {
		this.cd = cd;
		this.cr = cr;
	}

	@Override
	public double cv() {
		return (cd + cr) * 2;
	}

	@Override
	public double dt() {
		return cd * cr;
	}

	@Override
	public String print() {
		if (cd == cr) {
			return "HV";
		} else {
			return "HCN";
		}
	}

}
