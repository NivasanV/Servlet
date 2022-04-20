package com.servlet.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
	
	//create the query
	private static final String INSERT_QUERY ="INSERT INTO USER(NAME,CITY,MOBILE,DOB) VALUES(?,?,?,?)";
	
	@Override
	 protected void doGet(HttpServletRequest req,HttpServletResponse res)
			 throws ServletException, IOException
	{
		//Get Rrintwriter
		PrintWriter pw = res.getWriter();
		//set Content type
		res.setContentType("text/hmt1");
		//read the form values
		String name = req.getParameter("name");
		String city = req.getParameter("city");
		String mobile = req.getParameter("mobile");
		String dob = req.getParameter("dob");
		
		//load the jdbc driver
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		 } catch (ClassNotFoundException e) {
		   // TODO Auto-generated catch blockducat
		    e.printStackTrace();
		 }
		//create the connection
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///firstdb","root","");
				PreparedStatement ps = con.prepareStatement(INSERT_QUERY);){
			
			//set the values I
			ps.setString(1, name);
			ps.setString(2, city);
			ps.setString(3, mobile);
			ps.setString(4, dob);
			
			//excute the query
			int count= ps.executeUpdate();
			if(count==0) {
			    pw.println("Record not Stored into Database");
			}else {
			    pw. println("Record Stored into Database");
			}
			
		}catch(SQLException se) {
			   pw.println(se.getMessage());
			   se.printStackTrace();
			}catch(Exception e) {
			   pw.println(e.getMessage());
			   e.printStackTrace();
		}
		//close the stream
		pw.close();
		 
	 }
	
	
	protected void doPost(HttpServletRequest req,HttpServletResponse res)
			throws ServletException, IOException
    {
        doGet(req, res);
    }
	
}
