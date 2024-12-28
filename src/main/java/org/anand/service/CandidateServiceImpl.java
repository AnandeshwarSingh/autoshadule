package org.anand.service;

import java.util.List;
import java.util.Optional;

import org.anand.model.CandidateModule;
import org.anand.repository.*;

public class CandidateServiceImpl implements CandidateService{
	CandidateRepository candidaterepo = new CandidateRepositoryImpl();

	@Override
	public Optional<Integer> loginCandidate(String email, String password) {
		// TODO Auto-generated method stub
		return candidaterepo.loginCandidate(email, password);
	}

	@Override
	public boolean isCandidateAdd(CandidateModule model) {
		// TODO Auto-generated method stub
		return candidaterepo.isCandidateAdd(model);
	}

	@Override
	public boolean updateCandidateField(int candidateId, String field, String newValue) {
		// TODO Auto-generated method stub
		return candidaterepo.updateCandidateField(candidateId, field, newValue);
	}

	@Override
	public boolean isRemiveCandidate(int candidateId) {
		// TODO Auto-generated method stub
		return candidaterepo.isRemiveCandidate(candidateId);
	}

	@Override
	public Optional<List<CandidateModule>> getAllCandidate() {
		// TODO Auto-generated method stub
		return candidaterepo.getAllCandidate();
	}

	@Override
	public Optional<List<CandidateModule>> searchCandidatesByName(String name) {
		// TODO Auto-generated method stub
		return candidaterepo.searchCandidatesByName(name);
	}

}
