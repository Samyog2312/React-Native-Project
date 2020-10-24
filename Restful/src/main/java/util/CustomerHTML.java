package util;

import java.io.PrintWriter;

import java.util.List;

import data.CustomerDao;


public class CustomerHTML {
	public static void printStart(PrintWriter out) {
		out.println("<!DOCTYPE html><html><head><title>CustomerList</title></head><body>");
	}

	public static void printEnd(PrintWriter out) {
		out.println("</body></html>");
	}
	public static void printTable(PrintWriter out, List<CustomerDao> customerList) {
		out.println("<h1>Customer Data</h1>");
		out.println("<table border='1'>");
		for (int i=0;i<customerList.size();i++) {
			CustomerDao cd=customerList.get(i);
			
			out.println("<tr><td>"+cd.getId()+"<td>"+cd.getName()+"<td>"+
			cd.getDate()+"<td>"+cd.getTime());
		}
		out.println("</table>");
//		out.println("<a href='./index.html'>Back to form</a>");
	}

}
