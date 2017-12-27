package threeLayerNoRest;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class QlsvDal {
	
	private DBHelper mDBHelper;
	public QlsvDal() {
		// TODO Auto-generated constructor stub
		mDBHelper = DBHelper.getInstace();
	}
	
	public void addSV(Sinhvien sv){
		String sql = "Insert into sv values(NULL,'%s','%d','%d')";
		sql = String.format(sql, sv.getHoTen(),sv.getGt(),sv.getKhoa().getIdKhoa());
		mDBHelper.executeUpdate(sql);
	}
	
	public void editSV(Sinhvien sv){
		String sql = "Update sv set ten = '%s',gioitinh = '%d',id_khoa = '%d' where mssv = '%d'";
		sql = String.format(sql, sv.getHoTen(),sv.getGt(),sv.getKhoa().getIdKhoa(),sv.getMssv());
		mDBHelper.executeUpdate(sql);
	}
	
	public void delSv(int mssv){
		String sql = "Delete from sv where mssv = '%d'";sql=String.format(sql, mssv);
		mDBHelper.executeUpdate(sql);
	}
	
	public List<Sinhvien> show(){
		String sql = "select sv.*,khoa.ten_khoa from sv join khoa on sv.id_khoa = khoa.id_khoa order by sv.mssv ASC";
		ResultSet rst = mDBHelper.query(sql);
		List<Sinhvien> students = new ArrayList<>();
		try {
			while(rst.next()){
				Sinhvien sv = new Sinhvien();
				sv.setMssv(rst.getInt(1));
				sv.setHoTen(rst.getString(2));
				sv.setGt(rst.getInt(3));
				Khoa khoa = new Khoa();
				khoa.setIdKhoa(rst.getInt(4));
				khoa.setTenKhoa(rst.getString(5));
				sv.setKhoa(khoa);
				students.add(sv);
			}
			
			return students;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Vector<String> getNameCols(){
		Vector<String> colTitles = new Vector<>();
		int num_column;
		try {
			ResultSet rst = mDBHelper.query("select sv.*,khoa.ten_khoa from sv join khoa on sv.id_khoa = khoa.id_khoa limit 1");
			ResultSetMetaData rstMeta = rst.getMetaData();
			num_column = rstMeta.getColumnCount();
			for(int i=1;i<=num_column;i++){
				if(i==4) continue;
				colTitles.add(rstMeta.getColumnName(i));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return colTitles;
	}
	
	public List<Khoa> getAllKhoa(){
		String sql = "select * from khoa";
		ResultSet rst = mDBHelper.query(sql);
		List<Khoa> khoas = new ArrayList<>();
		try {
			while(rst.next()){
				Khoa khoa = new Khoa();
				khoa.setIdKhoa(rst.getInt(1));
				khoa.setTenKhoa(rst.getString(2));
				khoas.add(khoa);
			}
			
			return khoas;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
