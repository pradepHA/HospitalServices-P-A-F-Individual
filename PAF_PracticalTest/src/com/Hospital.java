package com;

import java.sql.*;

public class Hospital {
	private Connection connect()
	{
		Connection con = null;
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcaredb?serverTimezone=UTC", "root", "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return con;
	}
	
	public String insertHospital(String MOHcode,String ManagerName,String HospitalName,String Address,String TPnumber,String Location)
	{
		String output = "";
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for inserting.";
			}
			
			// create a prepared statement
			String query = "insert into hospital"
					+"(`HospitalID`,`MOHcode`,`ManagerName`,`HospitalName`,`Address`,`TPnumber`,`Location`)"
					 + " values (?, ?, ?, ?, ?, ?, ?)";
					
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, MOHcode);
			preparedStmt.setString(3, ManagerName);
			preparedStmt.setString(4, HospitalName);
			preparedStmt.setString(5, Address);
			preparedStmt.setString(6, TPnumber); 
			preparedStmt.setString(7, Location);  
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newHospitals = readHospital();
			
			output = "{\"status\":\"success\", \"data\": \"" +newHospitals + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the hospital.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	
	
}
