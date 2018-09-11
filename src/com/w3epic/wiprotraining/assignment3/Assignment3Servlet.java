/*
a. Create an emp table in the database with fields name, id and designation.
b. Create a HTML file with fields name, id and designation. Make sure that the 
	textfields have  the same name as name, id and designation.
c. Create a javabean with fields name, id and designation.
d. On click of the submit button of the HTML, invoke a jsp page which will 
	extract the values that were given by the HTML page and it invokes a servlet.
e. The Servlet will make a connection to the database and store the value in the table.
f. Connection to the database should have been established using a separate java class.
g. You can enhance the program by including more features like deletion, updation and 
	selecting all records.

CREATE TABLE emp_test(
	name		VARCHAR2(30),
	id			NUMBER(11),
	designation	VARCHAR2(10)
);
 */

package com.w3epic.wiprotraining.assignment3;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Assignment3Servlet extends HttpServlet {
	Connection conn = null;
	PreparedStatement pstmt = null;
	
//	public Assignment3Servlet() {
//		conn = DBUtil.getConnection();
//	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		conn = DBUtil.getConnection();

		String sql = "INSERT INTO emp_test VALUES(?, ?, ?)";
		
//		if (conn == null)
//			out.println("conn is null");
//		else 
//			out.println("conn is not null");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("name"));
			pstmt.setInt(2, Integer.parseInt(request.getParameter("id")));
			pstmt.setString(3, request.getParameter("designation"));
			pstmt.executeUpdate();
			out.println("Success");
			request.setAttribute("result", "Success");
		} catch (SQLException e) {
			request.setAttribute("result", "Failed");
			out.println("Failed");
			e.printStackTrace();
		}
		
//		request.setAttribute("result", "Success");
		//out.print("test");
		
		//request.getRequestDispatcher("/assignment3.jsp").forward(request, response);
		
		out.close();
	}

}
