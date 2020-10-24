package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.utils.SystemProperty;

import futil.AddCustomer;
import conn.Connections;
import data.CustomerDao;

@WebServlet(
    name = "ListCustomer",
    urlPatterns = {"/hello"}
)
public class ListCustomer extends HttpServlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {
	  doGet(request, response);}
  
  public void doGet (HttpServletRequest request, HttpServletResponse response)
		  throws IOException {
	    response.setContentType("text/html");
	    response.setCharacterEncoding("UTF-8");
	    PrintWriter out=response.getWriter();
	    
	    String name = request.getParameter("name");
	    String date = request.getParameter("date");
	    String time = request.getParameter("time");
	    
	    CustomerDao custDao = new CustomerDao();
		custDao.setName(name);
		custDao.setDate(date);
		custDao.setTime(time);
	    AddCustomer.addCustomer(custDao);
//	    out.println("<tr><td>"+fish.getId()+"<td>"+fish.getBreed()+"<td>"+fish.getWeight()+"<td>"+fish.getLength()+"<td>"+fish.getCity()+"<td>"+fish.getWater());

	     
	    ArrayList<CustomerDao> customerList=new ArrayList<>();
		util.CustomerHTML.printStart(out);
	    Connection conn=null;
	    
	    if (SystemProperty.environment.value() ==SystemProperty.Environment.Value.Production) {  
	    	out.println("Production version");
	    	try {
				conn=Connections.getProductionConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    else {    
	    	out.println("Development version");
			try {
				conn=Connections.getDevConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    try {
	    	if (conn!=null) {
				Statement stmt=conn.createStatement();
//				ResultSet RS=stmt.executeQuery("select * from data");
				ResultSet RS=stmt.executeQuery("select * from customer");
				
				while (RS.next()) {
					CustomerDao custDao1 = new CustomerDao();
					custDao1.setId(RS.getInt("id"));
					custDao1.setName(RS.getString("name"));
					custDao1.setDate(RS.getString("date"));
					custDao1.setTime(RS.getString("time"));
					customerList.add(custDao);
				}
				conn.close();
				util.CustomerHTML.printTable(out, customerList);
	    	}
	    	else {
	    		out.println("No connection to database!");
	    	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		util.CustomerHTML.printEnd(out);
  }
}