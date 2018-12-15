package com.revature;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EmployeeTest {
	@Test
	public void canInstantiate() {
		Employee employee = new Employee();
	}
	
	@Test
	public void aBaselineEmployeeCanBeAddedToTheSystemToAssistWithLogicalFileStructure() {
		Employee employee = new Employee();
		employee.makeBaselineEmployee();
	}
	
	@Test
	public void anEmployeesFirstNameCanBeStoredAndRetrievedFromTheSystem() {
		Employee employee = new Employee();
		employee.setEmployeeFirstName("John");
		assertEquals("John",employee.getEmployeeFirstName());
	}
	
	@Test
	public void anEmployeesLastNameCanBeStoredAndRetrievedFromTheSystem() {
		Employee employee = new Employee();
		employee.setEmployeeLastName("Smith");
		assertEquals("Smith",employee.getEmployeeLastName());
	}
	
	@Test
	public void anEmployeesAddressCanBeStoredAndRetrievedFromTheSystem() {
		Employee employee = new Employee();
		employee.setEmployeeAddress("123 Wallaby Way");
		assertEquals("123 Wallaby Way",employee.getEmployeeAddress());
	}
	
	@Test
	public void anEmployeesBirthDateCanBeStoredAndRetrievedFromTheSystem() {
		Employee employee = new Employee();
		employee.setEmployeeBirthDate(1,1,1980);
		assertEquals("1/1/1980",employee.getEmployeeBirthDate());
	}
	
	@Test
	public void anEmployeesEmailAddressCanBeStoredAndRetrievedFromTheSystem() {
		Employee employee = new Employee();
		employee.setEmployeeEmailAddress("John.Smith@revature.com");
		assertEquals("John.Smith@revature.com",employee.getEmployeeEmailAddress());
	}
	
	@Test
	public void anEmployeesPhoneNumberCanBeStoredAndRetrievedFromTheSystem() {
		Employee employee = new Employee();
		employee.setEmployeePhoneNumber("303-000-0000");
		assertEquals("303-000-0000",employee.getEmployeePhoneNumber());
	}
	
	@Test
	public void anEmployeesStatusCanBeStoredAndRetrievedFromTheSystem() {
		Employee employee = new Employee();
		employee.setEmployeeIsActive(1);
		assertEquals(1,employee.getEmployeeIsActive());
		employee.setEmployeeIsActive(0);
		assertEquals(0,employee.getEmployeeIsActive());
	}

}
