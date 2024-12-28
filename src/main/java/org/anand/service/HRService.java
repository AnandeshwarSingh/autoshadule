package org.anand.service;

import java.util.List;
import java.util.Optional;

import org.anand.model.CandidateModule;
import org.anand.model.HRModel;

public interface HRService {
	public Optional<Integer> loginHR(String email, String password);
	public boolean isAddHR(HRModel model);
	public Optional<List<HRModel>> getAllHr();
	public boolean updateHRField(int hrId, String field, String newValue);
	public boolean isRemiveHR(int id);
	public Optional<List<HRModel>> searchHrByName(String name);
	
}
