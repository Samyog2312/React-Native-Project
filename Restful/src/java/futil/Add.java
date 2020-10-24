package futil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.google.appengine.api.utils.SystemProperty;


import conn.Connections;
import data.CustomerDao;

public class Add {

  public static void addCustomer (CustomerDao custDao){
	  String sql = "insert into Customer(name, date,time) values(?,?,?)";
	  Connection conn=null;
	 try {
		 if(SystemProperty.environment.value() ==SystemProperty.Environment.Value.Production) {
			 conn = Connections.getProductionConnection();
	 }else {
		 conn = Connections.getDevConnection();
	 }
		 }catch (Exception e) {
		 //TODO Auto-generated catch block
		 e.printStackTrace();
	 }
	 PreparedStatement pstmt;
	 try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, custDao.getName());
		pstmt.setString(2, custDao.getDate());
		pstmt.setString(3, custDao.getTime());
		 
		 pstmt.execute();
		 }catch (SQLException e) {
			 e.printStackTrace ();
		 }
	 
   }
  
}