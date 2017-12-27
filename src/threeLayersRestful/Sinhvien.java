package threeLayersRestful;

import java.util.Vector;

public class Sinhvien {
	private int mssv;
	private String hoTen;
	private String DoB;
	private float ave;
	private Lop	lop;
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
	public String getDoB() {
		return DoB;
	}
	public void setDoB(String doB) {
		DoB = doB;
	}
	public float getAve() {
		return ave;
	}
	public void setAve(float ave) {
		this.ave = ave;
	}
	public Lop getLop() {
		return lop;
	}
	public void setLop(Lop lop) {
		this.lop = lop;
	}
	public Vector<String> getVectorData(){
		Vector<String> vt = new Vector<>();
		vt.add(String.valueOf(mssv));
		vt.add(hoTen);
		vt.add(String.valueOf(DoB));
		vt.add(String.valueOf(ave));
		vt.add(lop.getTenLop());
		return vt;
	}
	
}
