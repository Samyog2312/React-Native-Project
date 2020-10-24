package util;


import java.io.PrintWriter;

import java.util.List;

import org.eclipse.persistence.internal.oxm.mappings.Login;

import data.LoginDao;
import data.ReportDao;


public class LoginHTML {
	public static void printStart(PrintWriter out) {
		out.println("<!DOCTYPE html><html><head><title>ReportList</title></head><body>");
	}

	public static void printEnd(PrintWriter out) {
		out.println("</body></html>");
	}
	public static void printTable(PrintWriter out, List<LoginDao> reportList) {
		out.println("<h1>Login DataData</h1>");
		out.println("<table border='1'>");
		for (int i=0;i<reportList.size();i++) {
			LoginDao cd=reportList.get(i);
			
			out.println("<tr><td>"+cd.getId()+"<td>"+cd.getUsername()+"<td>"+
			cd.getPassword()+" "
			);
		}
		out.println("</table>");
//		out.println("<a href='./index.html'>Back to form</a>");
	}

}
