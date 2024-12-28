package org.anand.repository;

import org.anand.model.AdminModel;

public interface AdminRepository {
	
	public boolean loginAdmin(String adminemail,String password);

}
