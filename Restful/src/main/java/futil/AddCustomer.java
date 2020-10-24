package futil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.appengine.api.utils.SystemProperty;


import conn.Connections;
import data.CustomerDao;

public class AddCustomer {

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
  public int checkCustomerEntry (CustomerDao custDao){
	  
	  String sql = "select count(*) from Customer where date='"+custDao.getDate()+"' and time='"+custDao.getTime()+"'";
	  
	  ResultSet RS = null;
	  int x=0;
	  Connection conn = null;
		try {
			if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
				conn = Connections.getProductionConnection();
			} else {
				conn = Connections.getDevConnection();
			}
			Statement stmt;
			stmt = conn.createStatement();
			RS = stmt.executeQuery(sql);
			x=RS.getInt(1);
			conn.close();
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return x;
   }
}