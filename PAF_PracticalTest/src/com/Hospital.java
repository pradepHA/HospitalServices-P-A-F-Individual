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
	
	
	public String readHospital()
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
			
			// Prepare the html table to be displayed
			output = "<table border=1 >"
					+ "<tr><th>MOH Registration Code</th>"
					+ "<th>Manager Name</th>"
					+ "<th>Hospital Name</th>"
					+ "<th>Address</th>"
					+ "<th>Telephone Number</th>"
					+ "<th>Location</th>"
					+ "<th>Update</th>"
					+ "<th>Remove</th></tr>";
	
			String query = "select * from hospital";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next())
			{
				String HospitalID = Integer.toString(rs.getInt("HospitalID"));
				String MOHcode = rs.getString("MOHcode");
				String ManagerName = rs.getString("ManagerName");
				String HospitalName = rs.getString("HospitalName");
				String Address = rs.getString("Address");
				String TPnumber = rs.getString("TPnumber");
				String Location = rs.getString("Location");
				
				// Add into the html table
				output += "<tr><td><input id='hidHospitalIDUpdate'name='hidHospitalIDUpdate' type='hidden' value='" + HospitalID+ "'>" + MOHcode + "</td>";
				output += "<td>" + ManagerName + "</td>";
				output += "<td>" + HospitalName + "</td>";
				output += "<td>" + Address + "</td>";
				output += "<td>" + TPnumber + "</td>";
				output += "<td>" + Location + "</td>";
			
				// buttons
				output += "<td><input name='btnUpdate'type='button' "
						+ "value='Update'class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove'type='button' "
						+ "value='Remove'class='btnRemove btn btn-danger'data-hospitalid='"+ HospitalID + "'>" + "</td></tr>";
			}
			
			con.close();
			
			// Complete the html table
			output += "</table>";
			
		}
		catch (Exception e)
		{
			output = "Error while reading the hospital.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	
	public String updateHospital(String HospitalID, String MOHcode,String ManagerName,String HospitalName,String Address,String TPnumber,String Location)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for updating.";
			}
			
			// create a prepared statement
			String query = "UPDATE hospital SET MOHcode = ?,ManagerName = ?,HospitalName = ?,Address = ?,TPnumber = ?,Location = ? WHERE HospitalID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
		
			preparedStmt.setString(1, MOHcode);
			preparedStmt.setString(2, ManagerName);
			preparedStmt.setString(3, HospitalName);
			preparedStmt.setString(4, Address);
			preparedStmt.setString(5, TPnumber);
			preparedStmt.setString(6, Location);
			preparedStmt.setInt(7, Integer.parseInt(HospitalID));;
			
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newHospitals = readHospital();
			
			output = "{\"status\":\"success\", \"data\": \"" + newHospitals	 + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while updating the hospital.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	
	
	
}
