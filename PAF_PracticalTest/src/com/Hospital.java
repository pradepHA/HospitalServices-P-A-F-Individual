package com;

import java.sql.*;

import com.mysql.cj.xdevapi.Statement;

public class Hospital {
	
	
	private Connection connect() {

        Connection con = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3308/healthcaredb?serverTimezone=UTC", "root", "");

        } catch (Exception e) {

            e.printStackTrace();

        }

        return con;

    }
	
	//insert hospitals-------------------------------------------------------------------------------------------------------------------------
	
	public String insertHospital(String MOHCode,String Managername,String Hospitalname,String address,String TPNumber,String location,String username,String password)
    {
			String output = "";


			try {

	            Connection con = connect();

	            if (con == null) {

	                return "Error while connecting to the database for inserting.";

	            }

	            
				// create a prepared statement
				String query = "insert into hospital"
						+"(`HospitalID`,`MOHcode`,`ManagerName`,`HospitalName`,`Address`,`TPnumber`,`Location`,`Username`,`Password`)"
						 + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
						PreparedStatement preparedStmt = con.prepareStatement(query); 
				
						
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, MOHCode);
				preparedStmt.setString(3, Managername);
				preparedStmt.setString(4, Hospitalname);
				preparedStmt.setString(5, address);
				preparedStmt.setString(6, TPNumber); 
				preparedStmt.setString(7, location); 
				preparedStmt.setString(8, username); 
				preparedStmt.setString(9, password); 
	
				// execute the statement
				preparedStmt.execute();
				con.close();
				String newHospitals = readHospital();

	            output = "{\"status\":\"success\", \"data\": \"" + newHospitals + "\"}";

	        } catch (Exception e) {

	            output = "{\"status\":\"error\", \"data\":\"Error while inserting the hospital.\"}";
	            System.err.println(e.getMessage());

	        }

	        return output;

	    }
	
	//Read hospitals-------------------------------------------------------------------------------------------------------------------------
	
	public String readHospital()
	{
		String output = "";
		
		try {

            Connection con = connect();

            if (con == null) {

                return "Error while connecting to the database for reading. lol";

            }

			
			// Prepare the html table to be displayed
			output = "<table border=1 padding=10 ><tr style=\"text-align:center;\">"
					+ "<tr>"
					+ "<th>MOH Registration Code</th>"
					+ "<th>Manager Name</th>"
					+ "<th>Hospital Name</th>"
					+ "<th>Address</th>"
					+ "<th>Telephone Number</th>"
					+ "<th>Location</th>"
					+ "<th>Update</th>"
					+ "<th>Remove</th>"
					+ "</tr>";
			
			String query = "select * from hospital";
			Statement stmt = (Statement) con.createStatement();
            ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			
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
				output += "<tr><td><input id='hidHospitalIDSave' name='hidHospitalIDSave' type='hidden' value = '" + HospitalID + "'>" + HospitalID + "</td>";
				output += "<tr><td>" + MOHcode + "</td>";
				output += "<td>" + ManagerName + "</td>";
				output += "<td>" + HospitalName + "</td>";
				output += "<td>" + Address + "</td>";
				output += "<td>" + TPnumber + "</td>";
				output += "<td>" + Location + "</td>";
				
				
				// buttons
				output += "<td><input name='btnUpdate' type = 'button' value = 'Update' class='btnUpdate btn btn-secondary' ></td > " + "<td><input name='btnRemove' type = 'button' value = 'Remove' class='btnRemove btn btn-danger' data-hospitalid = '" + HospitalID + "'>" + "</td></tr>";

			}
			con.close();
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the Hospital.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//update hospitals-------------------------------------------------------------------------------------------------------------------------
	
	public String updateHospital(String HospitalID, String MOHCode,String Managername,String Hospitalname,String address,String TPNumber,String location,String username,String password)
	{
	
		String output = "";
		
		try {

            Connection con = connect();

            if (con == null) {

                return "Error while connecting to the database for updating.";

            }
			
			// create a prepared statement
				String query = "UPDATE hospital SET MOHcode = ?,ManagerName = ?,HospitalName = ?,Address = ?,TPnumber = ?,Location = ?,Username = ?,Password = ? WHERE HospitalID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, MOHCode);
			preparedStmt.setString(2, Managername);
			preparedStmt.setString(3, Hospitalname);
			preparedStmt.setString(4, address);
			preparedStmt.setString(5, TPNumber);
			preparedStmt.setString(6, location);
			preparedStmt.setString(7, username);
			preparedStmt.setString(8, password);
			preparedStmt.setString(9, HospitalID);
			
			
			// execute the statement
			preparedStmt.execute();
			con.close();

			String newHospitals = readHospital();
            output = "{\"status\":\"success\", \"data\": \"" + newHospitals + "\"}";

		} catch (Exception e) {

            output = "{\"status\":\"error\", \"data\":\"Error while updating the hospital.\"}";
            System.err.println(e.getMessage());

        }

        return output;

    }

	//Delete hospitals-------------------------------------------------------------------------------------------------------------------------
	
	public String deleteHospital(String HospitalID) {
		
		String output = "";
		
		try {

            Connection con = connect();

            if (con == null) {

                return "Error while connecting to the database for deleting.";

            }
            
			// create a prepared statement
			String query = "delete from hospital where HospitalID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(HospitalID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newHospitals = readHospital();
            output = "{\"status\":\"success\", \"data\": \"" + newHospitals + "\"}";

        } catch (Exception e) {

            output = "{\"status\":\"error\", \"data\":\"Error while deleting the hospital.\"}";
            System.err.println(e.getMessage());

        }

        return output;

    }

}
