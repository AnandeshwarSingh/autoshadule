package org.anand.repository;

import java.util.List;
import java.util.Optional;

import org.anand.model.CandidateModule;

public interface CandidateRepository {
	public Optional<Integer> loginCandidate(String email, String password);
	public boolean isCandidateAdd(CandidateModule model);
	public boolean updateCandidateField(int candidateId, String field, String newValue);
	public boolean isRemiveCandidate(int candidateId);
	public Optional<List<CandidateModule>> getAllCandidate();
	public Optional<List<CandidateModule>> searchCandidatesByName(String name);
	
	

}
