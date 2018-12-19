package com.revature.dao;

import com.revature.Administrator;

public interface AdministratorDao {
	Administrator getAdministratorById(int id);
	void addAdministrator(Administrator administrator);
	int getAdministratorId(Administrator administrator);
	int getStatus(int id);
}
