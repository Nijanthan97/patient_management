package com;

import java.sql.*;

public class patient {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hospital", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public String getPatientDetails() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error dtabase don't have any data.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Patient Name</th><th>Gender</th><th>Phone</th><th>Address</th><th>Age</th><th>Blood Group</th><th>NextOfKin</th></tr>";
			String query = "select * from patientdetails";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String PID = rs.getString("PID");
				String pName = rs.getString("pName");
				String gender = rs.getString("gender");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				String age = rs.getString("age");
				String bloodGroup = rs.getString("bloodGroup");
				String nextOfKin = rs.getString("nextOfKin");

				// Add into the html table

				output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + PID + "'>" + pName + "</td>";
			
				output += "<td>" + gender + "</td>";
				output += "<td>" + phone + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + age + "</td>";
				output += "<td>" + bloodGroup + "</td>";
				output += "<td>" + nextOfKin + "</td>";

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td><td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-pid='"+PID+"'></td></tr>";
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Patient.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	

	public String addPatientDetails(String pName, String gender, String phone, String address, String age,String bloodGroup, String nextOfKin) {
		
		String output = "";
		try{
			Connection con = connect();
			
			if (con == null){
					return "Error while connecting to the database for inserting."; 
				}
	
			// create a prepared statement
			String query = " insert into patientdetails (`pName`,`gender`,`phone`,`address`,`age`,`bloodGroup`,`nextOfKin`)" + " values (?,?,?, ?, ?, ?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
			preparedStmt.setString(1, pName);
			preparedStmt.setString(2, gender);
			preparedStmt.setString(3, phone);
			preparedStmt.setString(4, address);
			preparedStmt.setString(5, age);
			preparedStmt.setString(6, bloodGroup);
			preparedStmt.setString(7, nextOfKin);
	
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newItems = getPatientDetails();
			output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
			
			
		}catch (Exception e){
			output = "{\"status\":\"error\", \"data\":\"Error while inserting thepatient details.\"}";
					System.err.println(e.getMessage());
			}
		return output;
	}

	
	
public String updatepatient(String PID,String pName, String gender, String phone, String address, 
		String age,String bloodGroup, String nextOfKin){
		
		String output = "";
		try{
			Connection con =connect();
			if (con == null){
				return "Error while connecting to the database for updating.";
			}
		// create a prepared statement
		String query = "UPDATE patientdetails SET pName=?, gender=?, phone=?, address=?, age=?, bloodGroup=?,nextOfKin=? WHERE PID=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values
		
		preparedStmt.setString(1, pName);
		preparedStmt.setString(2, gender);
		preparedStmt.setString(3, phone);
		preparedStmt.setString(4, address);
		preparedStmt.setString(5, age);
		preparedStmt.setString(6, bloodGroup);
		preparedStmt.setString(7, nextOfKin);
		
		preparedStmt.setInt(8, Integer.parseInt(PID));
		// execute the statement
		preparedStmt.execute();
		con.close();
		String newItems = getPatientDetails();
		output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
	}catch (Exception e){
		output = "{\"status\":\"error\", \"data\":\"Error while updating the payment.\"}";
				System.err.println(e.getMessage());

	
	}
		return output;

	}

	public String deletepatient(String PID) {
		String output = "";
		try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}
			// create a prepared statement
			String query = "delete from patientdetails where PID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(PID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newItems = getPatientDetails();
			output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the payment.\"}";
					System.err.println(e.getMessage());
		}
		return output;
	}

}
