package org.anand.service;

import org.anand.model.CandidateModule;
import org.anand.model.candidateskills;
import org.anand.repository.*;

public class CandidateskillsServiceImpl implements CandidateskillsService{
	
	CandidateskillsRepository skillrepo = new CandidateskillsRepositoryImpl();

	@Override
	public boolean isAddSkilll(candidateskills module) {
		// TODO Auto-generated method stub
		return skillrepo.isAddSkilll(module);
	}

}
