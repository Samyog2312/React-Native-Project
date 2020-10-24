package services;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.google.appengine.api.utils.SystemProperty;
import java.sql.Statement;

import conn.Connections;

import data.ReportDao;
import futil.AddReport;


@Path("/reportservice")

public class ReportService {
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addreport")
	@Consumes("application/x-www-form-urlencoded")

	public ReportDao addReport(@FormParam("name") String name, @FormParam("email") String email, @FormParam("problem") String problem) {
		
		ReportDao RepoDao = new   ReportDao();
	    RepoDao.setName(name);
	    RepoDao.setEmail(email);
	    RepoDao.setProblem(problem);
	  
		String sql = "insert into Report (name,email,problem) values(?,?,?)";

		Connection conn = null;
		try {
			if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
				conn = Connections.getProductionConnection();
			} else {
				conn = Connections.getDevConnection();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, RepoDao.getName());
			pstmt.setString(2, RepoDao.getEmail());
			pstmt.setString(3, RepoDao.getProblem());

			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return RepoDao;
	}
	

	/*
	 * This method reveives values breed and weight from an html form which sends a
	 * POST type request.
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON) // Method returns object as a JSON string
	@Consumes(MediaType.APPLICATION_JSON) // Method receives object as a JSON string
	@Path("/addjsonreport")
	public ReportDao receiveJsonReport(ReportDao RepoDao) {
		String sql = "insert into Report(name, email, problem) values(?,?,?)";

		Connection conn = null;
		try {
			if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
				conn = Connections.getProductionConnection();
			} else {
				conn = Connections.getDevConnection();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, RepoDao.getName());
			pstmt.setString(2, RepoDao.getEmail());
			pstmt.setString(3, RepoDao.getProblem());

			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//					e.printStackTrace();
			}
		}
		
		return RepoDao;
	}
	



	@GET
	@Produces(MediaType.APPLICATION_JSON) // Method returns object as a JSON string
	@Path("/getAll")
	public ArrayList<ReportDao> getAllReport() {
		String sql = "select * from Report";
		ResultSet RS = null;
		ArrayList<ReportDao> list = new ArrayList<>();
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
			while (RS.next()) {
				ReportDao RepoDao = new ReportDao();
				RepoDao.setId(RS.getInt("id"));
				RepoDao.setName(RS.getString("name"));
				RepoDao.setEmail(RS.getString("email"));
				RepoDao.setProblem(RS.getString("problem"));
				list.add(RepoDao);
			}
				conn.close();
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

}
