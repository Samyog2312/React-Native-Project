package util;

import java.io.PrintWriter;

import java.util.List;

import data.ReportDao;


public class ReportHTML {
	public static void printStart(PrintWriter out) {
		out.println("<!DOCTYPE html><html><head><title>ReportList</title></head><body>");
	}

	public static void printEnd(PrintWriter out) {
		out.println("</body></html>");
	}
	public static void printTable(PrintWriter out, List<ReportDao> reportList) {
		out.println("<h1>Report Data</h1>");
		out.println("<table border='1'>");
		for (int i=0;i<reportList.size();i++) {
			ReportDao cd=reportList.get(i);
			
			out.println("<tr><td>"+cd.getId()+"<td>"+cd.getName()+"<td>"+
			cd.getEmail()+"<td>"+cd.getProblem());
		}
		out.println("</table>");
//		out.println("<a href='./index.html'>Back to form</a>");
	}

}
