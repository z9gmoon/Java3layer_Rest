package threeLayerNoRest;

import java.util.Vector;

public class Sinhvien {
	private int mssv;
	private String hoTen;
	private int gt;
	private Khoa khoa;
	
	public int getMssv() {
		return mssv;
	}
	public void setMssv(int mssv) {
		this.mssv = mssv;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public int getGt() {
		return gt;
	}
	public void setGt(int gt) {
		this.gt = gt;
	}
	public Khoa getKhoa() {
		return khoa;
	}
	public void setKhoa(Khoa khoa) {
		this.khoa = khoa;
	}
	public Vector<String> getVectorData(){
		Vector<String> vt = new Vector<>();
		vt.add(String.valueOf(mssv));
		vt.add(hoTen);
		vt.add(String.valueOf(gt));
		vt.add(khoa.getTenKhoa());
		return vt;
	}
	
	
}
