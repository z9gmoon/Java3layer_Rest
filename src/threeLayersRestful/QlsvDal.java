package threeLayersRestful;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class QlsvDal {
	private String prefixUrl = "http://localhost:8888/api/";
	private List<Lop> lops= null;
	public QlsvDal() {
		// TODO Auto-generated constructor stub
		lops = getAllLop();
	}
	
	public void addSv(Sinhvien sv){
//		String sql = "Insert into sv values(NULL,'%s','%s','%f','%d')";
//		sql = String.format(sql, sv.getHoTen(),sv.getDoB(),sv.getAve(),sv.getLop().getIdLop());
//		mDBHelper.executeUpdate(sql);
		String json_string = UtilRest.ObjToJson(sv);
		System.out.println(json_string);
		UtilRest.postMethod(prefixUrl+"sinhvien", json_string);
	}
	
	public void editSv(Sinhvien sv){
//		String sql = "Update sv set hoten = '%s',ngaysinh = '%s',diemTB = '%f',id_lop = '%d' where mssv = '%d'";
//		sql = String.format(sql, sv.getHoTen(),sv.getDoB(),sv.getAve(),sv.getLop().getIdLop(),sv.getMssv());
//		mDBHelper.executeUpdate(sql);
		String json_string = UtilRest.ObjToJson(sv);
		System.out.println(json_string);
		UtilRest.putMethod(prefixUrl+"sinhvien/"+Integer.toString(sv.getMssv()), json_string);
	}
	
	public void delSv(int mssv){
//		String sql = "Delete from sv where mssv = '%d'";sql=String.format(sql, mssv);
//		mDBHelper.executeUpdate(sql);
		UtilRest.delMethod(prefixUrl+"sinhvien/"+Integer.toString(mssv));
	}
	
	public List<Sinhvien> show(){
		try{
			String json = UtilRest.getMethod(prefixUrl+"sinhvien");
			if(json.equals("null")) return null;
			List<Sinhvien> svs = new ArrayList<>();
			if(json.charAt(0)!='[') {svs.add(UtilRest.JsonToSv(json));return svs;}
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(json);
	         JSONArray array = (JSONArray)obj;
	        for(int i=0;i<array.size();i++){
//	        	svs.add(UtilRest.JsonToSv(array.get(i).toString()));
	        	Sinhvien sv = UtilRest.JsonToSv(array.get(i).toString());
	        	sv.getLop().setTenLop(idToTen(sv.getLop().getIdLop()));
	        	svs.add(sv);
	        }
		return svs;
		}
		catch(ParseException pe){
		return null;}
	}
	
	public List<Sinhvien> show(int id){//show by id
		try{
//			lops = getAllLop();
			String json = UtilRest.getMethod(prefixUrl+"sinhvien/"+id);
			if(json.equals("null")) return null;
			List<Sinhvien> svs = new ArrayList<>();
			if(json.charAt(0)!='[') {svs.add(UtilRest.JsonToSv(json));return svs;}
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(json);
	         JSONArray array = (JSONArray)obj;
	        for(int i=0;i<array.size();i++){
//	        	svs.add(UtilRest.JsonToSv(array.get(i).toString()));
	        	Sinhvien sv = UtilRest.JsonToSv(array.get(i).toString());
	        	sv.getLop().setTenLop(idToTen(sv.getLop().getIdLop()));
	        	svs.add(sv);
	        }
		return svs;
		}
		catch(ParseException pe){
		return null;}
	}
	
	public Vector<String> getNameCols(){
		Vector<String> colTitles = new Vector<>();
			colTitles.add("Maso");
			colTitles.add("Ten");
			colTitles.add("Ngay sinh");
			colTitles.add("Diem");
			colTitles.add("Lop");
			
		return colTitles;
	}
	
	public List<Lop> getAllLop(){
		try{
			String json = UtilRest.getMethod(prefixUrl+"lop");
			List<Lop> lops = new ArrayList<>();
			if(json.charAt(0)!='[') {lops.add(UtilRest.JsonToLop(json));return lops;}
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(json);
	         JSONArray array = (JSONArray)obj;
	        for(int i=0;i<array.size();i++){
//	        	System.out.println(array.get(i));
	        	lops.add(UtilRest.JsonToLop(array.get(i).toString()));
	        }
		return lops;
		}
		catch(ParseException pe){
		return null;}
	}
	
	public String idToTen(int id){
		for (int i = 0; i < lops.size(); i++) {
			if(lops.get(i).getIdLop()==id)return lops.get(i).getTenLop();
		}
		return null;
	}
//	public static void main(String[] args) {
//		QlsvDal dal = new QlsvDal();
////		List<Sinhvien> lops = dal.show();
////		for (int i = 0; i < lops.size(); i++) {
////			System.out.println(lops.get(i).getLop().getTenLop());
////		}
//		Sinhvien sv = new Sinhvien();
//		sv.setMssv(6);
//		sv.setHoTen("aaaa");
//		sv.setDoB("2012-11-11");
//		sv.setAve(9);
//		Lop lop = new Lop();lop.setIdLop(3);lop.setTenLop(dal.idToTen(lop.getIdLop()));
//		sv.setLop(lop);
//		dal.addSv(sv);
////		dal.delSv(12);
//	}
}
