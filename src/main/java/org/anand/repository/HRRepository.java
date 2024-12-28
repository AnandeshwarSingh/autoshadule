package org.anand.repository;

import java.util.List;
import java.util.Optional;

import org.anand.model.HRModel;


public interface HRRepository {
	public Optional<Integer> loginHR(String email, String password);
	public boolean isHRAdd(HRModel model);
	public Optional<List<HRModel>> getAllHr();
	public boolean updateHRField(int hrId, String field, String newValue);
	public boolean isRemiveHR(int hrid);
	public Optional<List<HRModel>> searchHrByName(String name);
	

}
