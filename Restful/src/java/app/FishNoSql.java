package app;

import java.io.*;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;

@WebServlet(
    name = "customerNoSQL",
    urlPatterns = {"/customernosql"}
)
public class FishNoSql extends HttpServlet {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	  public void doGet(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException, ServletException {

		PrintWriter out=response.getWriter();  
		
	    response.setContentType("text/html");
	    response.setCharacterEncoding("UTF-8");

	    RequestDispatcher rd=request.getRequestDispatcher("/starthtml.html");
	    rd.include(request, response);
	    
	    out.print("<h2>Hello DataStore Example Application!</h2>");
	    
	    String removeId=request.getParameter("removeId");

	    String name=request.getParameter("name");
	    String date=request.getParameter("date");
	    String time=request.getParameter("time");
	   

	    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	    String sortOrder=request.getParameter("sortOrder");
	    if (removeId!=null) {
	    	Key key=KeyFactory.stringToKey(removeId);
	    	datastore.delete(key);
	    }
	    
	    /*See which datatypes are allowed in Entities
	     * https://cloud.google.com/appengine/docs/standard/java/datastore/entities */
	    else if (name!=null && date!=null && time!=null  ){
		    Entity entity=new Entity("CustomerDao");
		    entity.setProperty("name", name);
		    entity.setProperty("date", date);
		    entity.setProperty("time", time);
		   
		    
		    datastore.put(entity);
	    }
	    printDogs(out, datastore, sortOrder);
	    
	    rd=request.getRequestDispatcher("endhtml.html");
	    rd.include(request, response);
	  }
	  
		private void printDogs(PrintWriter out, DatastoreService datastore, String sortOrder) {
			Query query = new Query("CustomerDao");
			addSortingOrder(query, sortOrder);
			PreparedQuery pq = datastore.prepare(query);
			List<Entity> results = pq.asList(FetchOptions.Builder.withDefaults());

			out.println("<table>");
		    out.println("<tr><td>  <td>"+
		    		"<a href='/fishnosql?sortOrder=-name'>A-a</a> | "+
		    		"<a href='/fishnosql?sortOrder=name'>a-A</a>"+
		    		"<td>"+
		    		"<a href='/fishnosql?sortOrder=-date'>G-g</a> | "+
		    		"<a href='/fishnosql?sortOrder=date'>g-G</a>"+
		    		"<td>"+
		    		"<a href='/fishnosql?sortOrder=-time'>G-g</a> | "+
		    		"<a href='/fishnosql?sortOrder=time'>g-G</a>"
		    	);
			for (Entity entity : results) {
				String strFishID = KeyFactory.keyToString(entity.getKey());
				String  name= ""+entity.getProperty("name");
				String date= ""+entity.getProperty("date");
				String time = ""+entity.getProperty("time");
			
			    String output = "<tr><td>"+strFishID+"<td>"+name+ "<td>"+date+ "<td>"+time+ "<td>"+
			    		"<td><a href='/fishnosql?removeId="+strFishID+"'>Remove</a>";
				out.println(output);
			}		
			out.println("</table>");
			out.println("<a href='/index.html'>Back to form</a>");
		}
	  
	  private void addSortingOrder(Query query, String sortOrder) {
			// TODO Auto-generated method stub
			if (sortOrder==null) {
				return;
			}
			SortDirection direction=SortDirection.ASCENDING;
			if (sortOrder.startsWith("-")) {
				direction=SortDirection.DESCENDING;
				sortOrder=sortOrder.substring(1);
			}
			query.addSort(sortOrder, direction);
		}

	@Override
	  public void doPost(HttpServletRequest request, HttpServletResponse response) 
		      throws IOException, ServletException {
		  doGet(request, response);
	  }
	}