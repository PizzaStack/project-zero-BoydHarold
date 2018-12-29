package com.revature.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.Administrator;
import com.revature.Approval;
import com.revature.Employee;
import com.revature.jdbcinfo.EstablishConnection;

public class ApprovalDao {
	
	public List<Approval> getAllCheckingAccounts(){
		List<Approval> checkingAccounts = new ArrayList<>();
		
		try {
			PreparedStatement preGetAllCheckingAccounts = null;
			preGetAllCheckingAccounts = EstablishConnection.connection.prepareStatement("SELECT ca.CustomerId, ca.Status, ca.ApprovalStatus, cus.FirstName, cus.LastName FROM CheckingAccount ca JOIN Customer cus ON ca.CustomerId = cus.CustomerId;");
			ResultSet rs = preGetAllCheckingAccounts.executeQuery();
			Approval approval;
			
			while(rs.next()) {
				approval = new Approval(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5));
				checkingAccounts.add(approval);
			}
		} catch (SQLException e) {
			
		}
		
		return checkingAccounts;
	}
	
	public List<Approval> getAllSavingsAccounts(){
		List<Approval> savingsAccounts = new ArrayList<>();
		
		try {
			PreparedStatement preGetAllSavingsAccounts = null;
			preGetAllSavingsAccounts = EstablishConnection.connection.prepareStatement("SELECT sa.CustomerId, sa.Status, sa.ApprovalStatus, cus.FirstName, cus.LastName FROM SavingsAccount sa JOIN Customer cus ON sa.CustomerId = cus.CustomerId;");
			ResultSet rs = preGetAllSavingsAccounts.executeQuery();
			Approval approval;
			
			while(rs.next()) {
				approval = new Approval(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5));
				savingsAccounts.add(approval);
			}
		} catch (SQLException e) {
			
		}
		
		return savingsAccounts;
	}
	
	public List<Approval> getAllJointAccounts(){
		List<Approval> jointAccounts = new ArrayList<>();
		
		try {
			PreparedStatement preGetAllJointAccounts = null;
			preGetAllJointAccounts = EstablishConnection.connection.prepareStatement("SELECT ja.CustomerId1, ja.CustomerId2, ja.Status, ja.ApprovalStatus, cus.FirstName, cus.LastName FROM Customer cus JOIN JointAccount ja ON cus.CustomerId = ja.CustomerId1 OR cus.CustomerId = ja.CustomerId2;");
			ResultSet rs = preGetAllJointAccounts.executeQuery();
			Approval approval;
			
			while(rs.next()) {
				int customerId1 = rs.getInt(1);
				int customerId2 = rs.getInt(2);
				int status = rs.getInt(3);
				String approvalStatus = rs.getString(4);
				String firstName1 = rs.getString(5);
				String lastName1 = rs.getString(6);
				rs.next();
				String firstName2 = rs.getString(5);
				String lastName2 = rs.getString(6);
				approval = new Approval(customerId1, customerId2, status, approvalStatus, firstName1, lastName1, firstName2, lastName2);
				jointAccounts.add(approval);
			}
		} catch (SQLException e) {
			
		}
		
		return jointAccounts;
	}
		
	public List<Employee> getAllEmployees(){
		List<Employee> employees = new ArrayList<>();
		try {
			PreparedStatement preGetAllEmployees = EstablishConnection.connection.prepareStatement("SELECT * FROM Employee;");
			ResultSet rs = preGetAllEmployees.executeQuery();
			Employee employee;
			while(rs.next()) {
				employee = new Employee(rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getInt(8),
						rs.getString(9));
				employee.setEmployeeId(rs.getInt(1));
				employees.add(employee);
			}
		} catch (SQLException e) {
			
		}
		
		
		return employees;
	}
	
	public List<Administrator> getAllAdministrators(){
		List<Administrator> administrators = new ArrayList<>();
		try {
		PreparedStatement preGetAllAdministrators = EstablishConnection.connection.prepareStatement("SELECT * FROM Administrator;");
		ResultSet rs = preGetAllAdministrators.executeQuery();
		Administrator administrator;
		while(rs.next()) {
			administrator = new Administrator(rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getString(5),
					rs.getString(6),
					rs.getString(7),
					rs.getInt(8),
					rs.getString(9));
			administrator.setAdministratorId(rs.getInt(1));
			administrators.add(administrator);
		}
		} catch (SQLException e) {
			
		}
		return administrators;
	}
	
	public void updateCheckingAccount(Approval approval) {
		try {
			PreparedStatement preUpdateCheckingAccount = null;
			preUpdateCheckingAccount = EstablishConnection.connection.prepareStatement("UPDATE CheckingAccount SET Status = ?, ApprovalStatus = ? WHERE CustomerId = ?;");
			preUpdateCheckingAccount.setInt(1, approval.getStatus());
			preUpdateCheckingAccount.setString(2, approval.getApprovalStatus());
			preUpdateCheckingAccount.setInt(3, approval.getCustomerId());
			preUpdateCheckingAccount.executeUpdate();
		} catch (SQLException e) {
		}
	}
	
	public void updateSavingsAccount(Approval approval) {
		try {
			PreparedStatement preUpdateSavingsAccount = null;
			preUpdateSavingsAccount = EstablishConnection.connection.prepareStatement("UPDATE SavingsAccount SET Status = ?, ApprovalStatus = ? WHERE CustomerId = ?;");
			preUpdateSavingsAccount.setInt(1, approval.getStatus());
			preUpdateSavingsAccount.setString(2, approval.getApprovalStatus());
			preUpdateSavingsAccount.setInt(3, approval.getCustomerId());
			preUpdateSavingsAccount.executeUpdate();
		} catch (SQLException e) {
			
		}
	}
	
	public void updateJointAccount(Approval approval) {
		try {
			PreparedStatement preUpdateJointAccount = null;
			preUpdateJointAccount = EstablishConnection.connection.prepareStatement("UPDATE JointAccount SET Status = ?, ApprovalStatus = ? WHERE CustomerId1 = ? OR CustomerId2 = ?;");
			preUpdateJointAccount.setInt(1, approval.getStatus());
			preUpdateJointAccount.setString(2, approval.getApprovalStatus());
			preUpdateJointAccount.setInt(3, approval.getCustomerId());
			preUpdateJointAccount.setInt(4, approval.getCustomerId());
			preUpdateJointAccount.executeUpdate();
		} catch (SQLException e) {
			
		}
	}
	
	public void deleteJointAccount(Approval approval) {
		try {
			PreparedStatement preDeleteJointAccount = null;
			preDeleteJointAccount = EstablishConnection.connection.prepareStatement("DELETE FROM JointAccount WHERE CustomerId1 = ? OR CustomerId2 = ?;");
			preDeleteJointAccount.setInt(1, approval.getCustomerId());
			preDeleteJointAccount.setInt(2, approval.getCustomerId());
			preDeleteJointAccount.executeUpdate();
		} catch (SQLException e) {
			
		}
	}
	
	public void updateEmployee(Employee employee) {
		try {
			PreparedStatement preUpdateEmployee = null;
			preUpdateEmployee = EstablishConnection.connection.prepareStatement("UPDATE Employee SET Status = ?, ApprovalStatus = ? WHERE EmployeeId = ?;");
			preUpdateEmployee.setInt(1, employee.getEmployeeIsActive());
			preUpdateEmployee.setString(2, employee.getApprovalStatus());
			preUpdateEmployee.setInt(3, employee.getEmployeeId());
			preUpdateEmployee.executeUpdate();
			preUpdateEmployee = EstablishConnection.connection.prepareStatement("UPDATE EmployeeUsers SET Status = ? WHERE EmployeeId = ?;");
			preUpdateEmployee.setInt(1, employee.getEmployeeIsActive());
			preUpdateEmployee.setInt(2, employee.getEmployeeId());
			preUpdateEmployee.executeUpdate();

		} catch (SQLException e) {
			
		}
	}
	
	public void deleteEmployee(Employee employee) {
		try {
			PreparedStatement preDeleteEmployee = null;
			preDeleteEmployee = EstablishConnection.connection.prepareStatement("DELETE FROM EmployeeUsers WHERE EmployeeId = ?;");
			preDeleteEmployee.setInt(1, employee.getEmployeeId());
			preDeleteEmployee.executeUpdate();
			preDeleteEmployee = EstablishConnection.connection.prepareStatement("DELETE FROM Employee WHERE EmployeeId = ?;");
			preDeleteEmployee.setInt(1, employee.getEmployeeId());
			preDeleteEmployee.executeUpdate();

		} catch (SQLException e) {
			
		}
	}
	
	public void updateAdministrator(Administrator administrator) {
		try {
			PreparedStatement preUpdateAdministrator = null;
			preUpdateAdministrator = EstablishConnection.connection.prepareStatement("UPDATE Administrator SET Status = ?, ApprovalStatus = ? WHERE AdministratorId = ?;");
			preUpdateAdministrator.setInt(1, administrator.getAdministratorIsActive());
			preUpdateAdministrator.setString(2, administrator.getApprovalStatus());
			preUpdateAdministrator.setInt(3, administrator.getAdministratorId());
			preUpdateAdministrator.executeUpdate();
			preUpdateAdministrator = EstablishConnection.connection.prepareStatement("UPDATE AdministratorUsers SET Status = ? WHERE AdministratorId = ?;");
			preUpdateAdministrator.setInt(1, administrator.getAdministratorIsActive());
			preUpdateAdministrator.setInt(2, administrator.getAdministratorId());
			preUpdateAdministrator.executeUpdate();
		} catch (SQLException e) {
			
		}
	}
	
	public void deleteAdministrator(Administrator administrator) {
		try {
			PreparedStatement preDeleteAdministrator = null;
			preDeleteAdministrator = EstablishConnection.connection.prepareStatement("DELETE FROM AdministratorUsers WHERE AdministratorId = ?;");
			preDeleteAdministrator.setInt(1, administrator.getAdministratorId());
			preDeleteAdministrator.executeUpdate();
			preDeleteAdministrator = EstablishConnection.connection.prepareStatement("DELETE FROM Administrator WHERE AdministratorId = ?;");
			preDeleteAdministrator.setInt(1, administrator.getAdministratorId());
			preDeleteAdministrator.executeUpdate();

		} catch (SQLException e) {
			
		}
	}
}
