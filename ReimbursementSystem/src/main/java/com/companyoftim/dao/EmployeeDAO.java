package com.companyoftim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.companyoftim.users.Employee;

import jdbc.ConnectionUtil;

public class EmployeeDAO {
	public Employee getEmployeeInfo(int eid) {
		PreparedStatement ps = null;
		Employee emp = null;

		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM EMPLOYEE WHERE EMP_ID= ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, eid);

			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				int id = eid;
				int dept = rs.getInt("EMP_DPT");
				String name = rs.getString("EMP_NAME");
				emp = new Employee (id, dept, name);

			}

			else {
				System.out.println("Invalid Employee");
			}
			rs.close();
			ps.close();

		} catch (Exception ex) {
			ex.getMessage();
		}

		return emp;
	}

	public ArrayList<Integer> getSubEmployee(int id) {
		PreparedStatement ps = null;
		ArrayList<Integer> emps = new ArrayList<Integer>();

		try(Connection conn = ConnectionUtil.getConnection()) {

			String emp_retrieve = "SELECT * FROM EMPLOYEE WHERE EMP_DPT = ?";

			ps = conn.prepareStatement(emp_retrieve);
			ResultSet rs=ps.executeQuery();

			while (rs.next()) {

				emps.add(rs.getInt("EMP	_ID"));

			}

			rs.close();
			ps.close();

		} catch (Exception ex) {
			ex.getMessage();
		}

		return emps;
	}

	public boolean empExists(int eid, String pass) {
		boolean exists=false;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT 1 FROM EMPLOYEE WHERE EXISTS EMP_ID = ? AND EMP_PASSWORD = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, eid);
			statement.setString(2, pass);
			ResultSet rs = statement.executeQuery();
			if (rs.next()){
				exists=true;
			}
			else {
				System.out.println("Invalud ID, ");
			}
			
			rs.close();
			statement.close();

		} catch (Exception ex) {
			ex.getMessage();
		}
		
		return exists;
	}

	public boolean isManager(int eid) {
		boolean mngr=false;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT 1 FROM DEPARTMENT WHERE EXISTS MNG_ID= ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, eid);
			
			ResultSet rs = statement.executeQuery();
			if (rs.next()){
				mngr=true;
			}
			
			rs.close();
			statement.close();

		} catch (Exception ex) {
			ex.getMessage();
		}
		
		return mngr;
	}
}
