package com.revature;

import static org.junit.Assert.assertEquals;

import java.util.Scanner;

import org.junit.Test;

public class CustomerTest {
	@Test
	public void canInstantiate() {
		Customer customer = new Customer();
	}
	
	@Test
	public void aBaselineCustomerCanBeAddedToAssistWithLogicalFileStructure() {
		Customer customer = new Customer();
		customer.makeBaselineCustomer();
	}
	
	@Test
	public void aCustomersFirstNameCanBeStoredAndRetrievedFromTheSystem() {
		Customer customer = new Customer();
		customer.setCustomerFirstName("John");
		assertEquals("John",customer.getCustomerFirstName());
	}
	
	@Test
	public void aCustomersLastNameCanBeStoredAndRetrievedFromTheSystem() {
		Customer customer = new Customer();
		customer.setCustomerLastName("Smith");
		assertEquals("Smith",customer.getCustomerLastName());
	}
	
	@Test
	public void aCustomersAddressCanBeStoredAndRetrievedFromTheSystem() {
		Customer customer = new Customer();
		customer.setCustomerAddress("123 Wallaby Way");
		assertEquals("123 Wallaby Way",customer.getCustomerAddress());
	}
	
	@Test
	public void aCustomersBirthDateCanBeStoredAndRetrievedFromTheSystem() {
		Customer customer = new Customer();
		customer.setCustomerBirthDate(1,1,1980);
		assertEquals("1/1/1980",customer.getCustomerBirthDate());
	}
	
	@Test
	public void aCustomersEmailAddressCanBeStoredAndRetrievedFromTheSystem() {
		Customer customer = new Customer();
		customer.setCustomerEmailAddress("John.Smith@revature.com");
		assertEquals("John.Smith@revature.com",customer.getCustomerEmailAddress());
	}
	
	@Test
	public void aCustomersPhoneNumberCanBeStoredAndRetrievedFromTheSystem() {
		Customer customer = new Customer();
		customer.setCustomerPhoneNumber("303-000-0000");
		assertEquals("303-000-0000",customer.getCustomerPhoneNumber());
	}
	
	@Test
	public void aCustomersStatusCanBeStoredAndRetrievedFromTheSystem() {
		Customer customer = new Customer();
		customer.setCustomerIsActive(1);
		assertEquals(1,customer.getCustomerIsActive());
		customer.setCustomerIsActive(0);
		assertEquals(0,customer.getCustomerIsActive());
	}

}
