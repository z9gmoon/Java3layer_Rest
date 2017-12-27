package threeLayerNoRest;

import java.util.List;
import java.util.Vector;

public class QlsvBll {
	private QlsvDal mQlsvDal;
	public QlsvBll() {
		// TODO Auto-generated constructor stub
		mQlsvDal = new QlsvDal();
	}
	
	public List<Sinhvien> show(){
		return mQlsvDal.show();
	}
	
	public void addSv(Sinhvien sv){
		mQlsvDal.addSV(sv);
	}
	
	public void editSv(Sinhvien sv){
		mQlsvDal.editSV(sv);
	}
	
	public void delSv(int mssv){
		mQlsvDal.delSv(mssv);
	}
	
	public List<Khoa> getAllKhoa(){
		return mQlsvDal.getAllKhoa();
	}
	
	public Vector<String> getNameCols(){
		return mQlsvDal.getNameCols();
	}
	
	
	
}
