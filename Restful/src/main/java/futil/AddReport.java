package futil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.google.appengine.api.utils.SystemProperty;


import conn.Connections;

import data.ReportDao;

public class AddReport {

  public static void addReport (ReportDao RepoDao){
	  String sql = "insert into Report(name, email,problem) values(?,?,?)";
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
		pstmt.setString(1, RepoDao.getName());
		pstmt.setString(2, RepoDao.getEmail());
		pstmt.setString(3, RepoDao.getProblem());
		 
		 pstmt.execute();
		 }catch (SQLException e) {
			 e.printStackTrace ();
		 }
	 
   }
  
}