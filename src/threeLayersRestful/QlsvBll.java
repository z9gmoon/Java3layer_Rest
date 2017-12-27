package threeLayersRestful;

import java.util.List;
import java.util.Vector;

public class QlsvBll {
	
	QlsvDal mDal;
	
	public QlsvBll() {
		// TODO Auto-generated constructor stub
		mDal = new QlsvDal();
	}
	
	public List<Sinhvien> show(){
		return mDal.show();
	}
	
	public void addSv(Sinhvien sv){
		mDal.addSv(sv);
	}
	
	public void editSv(Sinhvien sv){
		mDal.editSv(sv);
	}
	
	public void delSv(int mssv){
		mDal.delSv(mssv);
	}
	
	public List<Lop> getAllLop(){
		return mDal.getAllLop();
	}
	
	public Vector<String> getNameCols(){
		return mDal.getNameCols();
	}
}
