package com.companyoftim.users;

import java.util.ArrayList;
import java.util.Scanner;

import com.companyoftim.beans.Reimbursement;
import com.companyoftim.dao.ReimbursementDAO;

public class Employee {
	private int id;
	private int dept;
	private String name;
	private ReimbursementDAO reimdao = new ReimbursementDAO();
	protected ArrayList<Reimbursement> reim = new ArrayList<Reimbursement>();
	
	public Employee() {};
	
	public Employee(int id, int dept, String name) {
		super();
		this.id = id;
		this.dept = dept;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDept() {
		return dept;
	}
	public void setDept(int dept) {
		this.dept = dept;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", dept=" + dept + ", name=" + name + "]";
	}
	
	public boolean main(Scanner sc) {
		boolean finished = false;
		while(!finished) {
			System.out.println("Welcome "+ name + ".\n Would you like to view your reimbursements, request a new reimbursement? Enter exit to quit");
			String option = sc.nextLine().replaceAll("(\\r|\\n)", "");
			switch (option) {
			case "request": requestReimbursement(sc); break;
			case "view": for(int i=0;i< reim.size();i++) reim.get(i).toString(); break;
			case "exit": finished=true; break;
			default: System.out.println("invalid option, please enter view, request or exit");
			}
		}
		
		return false;
		
	}
	
	private void requestReimbursement(Scanner sc) {
	try{
		System.out.println("Enter amount you would like to request: ");
		double req = Double.parseDouble(sc.nextLine());
		
		System.out.println("Enter reason for reimbursement request");
		String reason =sc.nextLine().replaceAll("(\\r|\\n)", "");
		reimdao.insertReimbursement(req, reason, id, dept);
		
		}catch(NumberFormatException ex) {
			System.out.println("Please enter a double.");
		}catch(NullPointerException ex){
			System.out.println("Please enter a double.");
		};
		
		
	}
}
