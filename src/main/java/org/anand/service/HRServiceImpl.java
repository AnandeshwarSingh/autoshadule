package org.anand.service;
import java.util.List;
import java.util.Optional;

import org.anand.model.HRModel;
import org.anand.repository.*;

public class HRServiceImpl implements HRService{
	
	HRRepository hrrepo = new HRRepositoryImpl();
	
	@Override
	public Optional<Integer> loginHR(String email, String password) {
		// TODO Auto-generated method stub
		return hrrepo.loginHR(email, password);
	}

	@Override
	public boolean isAddHR(HRModel model) {
		// TODO Auto-generated method stub
		return hrrepo.isHRAdd(model);
	}

	@Override
	public Optional<List<HRModel>> getAllHr() {
		// TODO Auto-generated method stub
		
		return hrrepo.getAllHr();
//		return Optional.empty();
	}

	@Override
	public boolean updateHRField(int hrId, String field, String newValue) {
		// TODO Auto-generated method stub
		return hrrepo.updateHRField(hrId, field, newValue);
		
	}

	@Override
	public boolean isRemiveHR(int hrid) {
		// TODO Auto-generated method stub
		return hrrepo.isRemiveHR(hrid);
	}

	@Override
	public Optional<List<HRModel>> searchHrByName(String name) {
		// TODO Auto-generated method stub
		return hrrepo.searchHrByName(name);
	}

	
	
}
