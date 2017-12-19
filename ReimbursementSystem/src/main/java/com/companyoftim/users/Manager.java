package com.companyoftim.users;

import java.util.ArrayList;
import java.util.Scanner;

public class Manager extends Employee{

	private ArrayList<Integer> emps= new ArrayList<Integer>();

	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Manager(int id, int dept, String name) {
		super(id, dept, name);
		// TODO Auto-generated constructor stub
	}

	public Manager(Employee emp) {
		super(emp.getId(), emp.getDept(), emp.getName());
	}

	public Manager(Employee emp, ArrayList<Integer> subs) {
		super(emp.getId(), emp.getDept(), emp.getName());
		emps=subs;
	}

	public ArrayList<Integer> getEmps() {
		return emps;
	}

	public void setEmps(ArrayList<Integer> emps) {
		this.emps = emps;
	}

	@Override
	public boolean main(Scanner sc) {

		boolean finished=false;
		boolean logout = false;
		while(!finished) {
			System.out.println("Perform actions as Employee or Manager? Enter exit to quit");
			String option= sc.nextLine().toLowerCase().replaceAll("(\\r|\\n)", "");
			switch (option)
			{
			case "employee": finished = super.main(sc); break;
			case "manager": finished=true;{
				boolean managing=true;
				while(managing)
					System.out.println("Would you like to view all reimbursement or only pending? Enter exit to return to changing mode");
				option = sc.nextLine().toLowerCase().replaceAll("(\\r|\\n)", "");
				switch(option) {
				case "all": for(int i=0;i<reim.size();i++) System.out.println(reim.get(i).toString());
				case "pending": for (int i=0;i<reim.size();i++) {
					if(reim.get(i).isComplete()) System.out.print(reim.get(i).toString());
				}
				case "exit": managing=false; break;
				default: System.out.println("Invalid option, please all, pending, or exit\"");
				}

			} break;
			case "exit": finished=false; logout=true; break;
			default: System.out.println("invalid option, please enter employee, manager or exit");
			}
		}

		return logout;
	}

}
