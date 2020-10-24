package services;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import data.CustomerDao;


@Path("/customerservice")

public class CustomerService {
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addcustomer")
	@Consumes("application/x-www-form-urlencoded")

	public CustomerDao addCustomer(@FormParam("name") String name, @FormParam("date") String date, @FormParam("time") String time) {
		
		CustomerDao custDao = new CustomerDao();
		custDao.setName(name);
		custDao.setDate(date);
		custDao.setTime(time);
		String sql = "insert into Customers(name,date,time) values(?,?,?)";

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
			pstmt.setString(1, custDao.getName());
			pstmt.setString(2, custDao.getDate());
			pstmt.setString(3, custDao.getTime());

			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return custDao;
	}

	/*
	 * This method reveives values breed and weight from an html form which sends a
	 * POST type request.
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON) // Method returns object as a JSON string
	@Consumes(MediaType.APPLICATION_JSON) // Method receives object as a JSON string
	@Path("/addjsoncustomer")
	public CustomerDao receiveJsonCustomer(CustomerDao custDao) {
		String sql = "insert into customer(name, date, time) values(?,?,?)";

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
			pstmt.setString(1, custDao.getName());
			pstmt.setString(2, custDao.getDate());
			pstmt.setString(3, custDao.getTime());

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
		
		return custDao;
	}

//		@DELETE
	@GET
	@Produces(MediaType.APPLICATION_JSON) // Method returns object as a JSON string
//		@Consumes(MediaType.APPLICATION_JSON)//Method receives object as a JSON string
	@Path("/deletecustomer/{p1}")
	public int deleteCustomer(@PathParam("p1") int id) {
		String sql = "delete from customer where id=?";

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
			pstmt.setInt(1, id);
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

		return id;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON) // Method returns object as a JSON string
	@Path("/getAll")
	public ArrayList<CustomerDao> getAllCustomer() {
		String sql = "select * from Customer";
		ResultSet RS = null;
		ArrayList<CustomerDao> list = new ArrayList<>();
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
				CustomerDao custDao = new CustomerDao();
				custDao.setId(RS.getInt("id"));
				custDao.setName(RS.getString("name"));
				custDao.setDate(RS.getString("date"));
				custDao.setTime(RS.getString("time"));
				list.add(custDao);
			}
				conn.close();
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

}
