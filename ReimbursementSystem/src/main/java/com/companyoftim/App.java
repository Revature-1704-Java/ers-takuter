package com.companyoftim;

import java.util.Scanner;

import com.companyoftim.dao.EmployeeDAO;
import com.companyoftim.users.Employee;
import com.companyoftim.users.Manager;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Employee emp = null;
    	EmployeeDAO dao = new EmployeeDAO();
    	Scanner sc = new Scanner(System.in);
    	boolean loggedin=false;
    	
    	while(!loggedin) {
    	try {
    	
    		System.out.println( "Please enter Employee ID:" );
    		int id = Integer.parseInt(sc.nextLine());
    		
    		System.out.print("Please enter password" );
    		String password = sc.nextLine().replaceAll("(\\r|\\n)", "");
    		
    		if(dao.empExists(id, password)){
        		loggedin=true;
        		emp = dao.getEmployeeInfo(id);
    		}
    		
    		} catch(NumberFormatException ex) {
    			System.out.println("Invalid form of id, please try again");
    		}
    		
    	}
    	
    	if(dao.isManager(emp.getId())) {
    		Manager mngr = new Manager(emp, dao.getSubEmployee(emp.getId()));
    		mngr.main(sc);
    	}
    	
    	else {
    		emp.main(sc);
    	}
    }
}
