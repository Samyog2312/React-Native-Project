package futil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.google.appengine.api.utils.SystemProperty;


import conn.Connections;

import data.LoginDao;

public class AddLogin {

  public static void addLogin (LoginDao loginDao){
	  String sql = "insert into Login(username, password) values(?,?)";
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
		pstmt.setString(1, loginDao.getUsername());
		pstmt.setString(2, loginDao.getPassword());
		
		 
		 pstmt.execute();
		 }catch (SQLException e) {
			 e.printStackTrace ();
		 }
	 
   }
  
}