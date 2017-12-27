package threeLayerNoRest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {
	
	Statement stm;
	private static DBHelper instance;
	
	public static synchronized DBHelper getInstace(){
		if(instance==null) instance = new DBHelper();
		return instance;
	}
	
	
	private DBHelper() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlsv","root","");
			stm = conn.createStatement();
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean executeUpdate(String s){
		try {
			int affectedRow = stm.executeUpdate(s);
			if(affectedRow>0) return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public ResultSet query(String s){
		ResultSet rs = null;
		try {
			rs=stm.executeQuery(s);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
}
