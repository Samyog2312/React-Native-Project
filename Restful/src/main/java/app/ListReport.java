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

import futil.AddReport;
import conn.Connections;
import data.ReportDao;

@WebServlet(
    name = "ListCustomer",
    urlPatterns = {"/hello"}
)
public class ListReport extends HttpServlet {

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
	    String email = request.getParameter("email");
	    String problem = request.getParameter("problem");
	    
	    ReportDao RepoDao = new   ReportDao();
	    RepoDao.setName(name);
	    RepoDao.setEmail(email);
	    RepoDao.setProblem(problem);
	    AddReport.addReport(RepoDao);
//	    out.println("<tr><td>"+fish.getId()+"<td>"+fish.getBreed()+"<td>"+fish.getWeight()+"<td>"+fish.getLength()+"<td>"+fish.getCity()+"<td>"+fish.getWater());

	     
	    ArrayList<ReportDao> reportList=new ArrayList<>();
		util.ReportHTML.printStart(out);
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
				ResultSet RS=stmt.executeQuery("select * from Report");
				
				while (RS.next()) {
				ReportDao repoDao1 = new ReportDao();
					repoDao1.setId(RS.getInt("id"));
					repoDao1.setName(RS.getString("name"));
					repoDao1.setEmail(RS.getString("email"));
					repoDao1.setProblem(RS.getString("problem"));
					reportList.add(RepoDao);
				}
				conn.close();
				util.ReportHTML.printTable(out, reportList);
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