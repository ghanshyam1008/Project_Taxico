package com.incapp.modal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
public class DAO {
	Connection c;
	public DAO() throws ClassNotFoundException,SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		c=DriverManager.getConnection("jdbc:mysql://localhost:3306/taxico","root","incapp");
	}
	public void closeConnection()throws SQLException {
		c.close();
	}
	
	public String setEnquiry(String name,String phone) throws SQLException{
		PreparedStatement p=c.prepareStatement("insert into enquiry (name,phone,status) values (?,?,'Pending')");
		p.setString(1, name);
		p.setString(2, phone);
		p.executeUpdate();
		return "Enquiry Send success!";
	}
	public String checkAdminLogin(String id,String password) throws SQLException{
		PreparedStatement p=c.prepareStatement("select * from admin where id=? and password=?");
		p.setString(1, id);
		p.setString(2, password);
		ResultSet rs=p.executeQuery();
		if(rs.next()) {
			return rs.getString("name");
		}else {
			return null;
		}
	}
	public ArrayList<HashMap> getAllEnquiry() throws SQLException{
		ArrayList<HashMap> allEnquiry=new ArrayList();
		PreparedStatement p=c.prepareStatement("select * from enquiry");
		ResultSet rs=p.executeQuery();
		while(rs.next()) {
			HashMap<String,String> enquiry=new HashMap();
			enquiry.put("name", rs.getString("name"));
			enquiry.put("phone", rs.getString("phone"));
			enquiry.put("status", rs.getString("status"));
			enquiry.put("id", rs.getString("id"));
			allEnquiry.add(enquiry);
		}
		return allEnquiry;
	}
	public String changeEnquiryStatus(int id,String status) throws SQLException{
		PreparedStatement p=c.prepareStatement("update enquiry set status=? where id=?");
		p.setString(1, status);
		p.setInt(2, id);
		p.executeUpdate();
		return "Enquiry Status Updation success!";
	}
}
