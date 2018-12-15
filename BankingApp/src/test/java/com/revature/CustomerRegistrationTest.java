package com.revature;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CustomerRegistrationTest {
	@Test
	public void canInstantiate() {
		CustomerRegistration customerRegistration = new CustomerRegistration();
	}
	
	@Test
	public void aBaselineCustomerUserCanBeAddedToTheSystemToAssistWithLogicalFileStructure() {
		CustomerRegistration customerRegistration = new CustomerRegistration();
		customerRegistration.makeBaselineUser();
	}
	
	@Test
	public void aCustomerUserCanBeRegisteredWithTheSystem() {
		CustomerRegistration customerRegistration = new CustomerRegistration();
		customerRegistration.registerUser("1","smithj","jacklefoot1");
	}
	
	@Test
	public void aCustomerUserCanBeCheckedForInTheSystem() {
		CustomerRegistration customerRegistration = new CustomerRegistration();
		int status = 1;
		assertEquals(status,customerRegistration.checkUserExists("smithj"));
		status = 0;
		assertEquals(status,customerRegistration.checkUserExists("smithjon"));
	}
	
	@Test
	public void aCustomerCanBeVerifiedThatTheyHaveAnAccountWithTheBank() {
		CustomerRegistration customerRegistration = new CustomerRegistration();
		int status = 1;
		assertEquals(status,customerRegistration.checkCustomerOnboarded("1"));
		status = 0;
		assertEquals(status,customerRegistration.checkCustomerOnboarded("30"));
	}
}
