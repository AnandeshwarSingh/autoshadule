package org.anand.service;
import org.anand.repository.*;

public class AdminServiceImpl implements AdminService {

	AdminRepository arepo=new AdminRepositoryImpl();
	@Override
	public boolean loginAdmin(String adminemail, String password) {
		return arepo.loginAdmin(adminemail, password);
	}
	
}
