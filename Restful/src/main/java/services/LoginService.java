package services;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
import data.CustomerDao;
import data.LoginDao;


@Path("/loginservice")

public class LoginService {
		
	@GET
	@Produces(MediaType.APPLICATION_JSON) // Method returns object as a JSON string
	@Path("/getAll/{username}/{password}")
	public ArrayList<LoginDao> getLogins(@PathParam("username") String userName, @PathParam("password") String pwd) {
		String sql = "select * from login where username='"+userName+"' and password='"+pwd+"'";
		ResultSet RS = null;
		ArrayList<LoginDao> list = new ArrayList<>();
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
				LoginDao LoDao = new LoginDao();
				LoDao.setId(RS.getInt("id"));
				LoDao.setUsername("");
				LoDao.setPassword("");
				list.add(LoDao);
			}
			
				conn.close();
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
