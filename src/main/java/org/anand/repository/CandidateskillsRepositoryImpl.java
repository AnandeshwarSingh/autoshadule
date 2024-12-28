package org.anand.repository;

import org.anand.model.CandidateModule;
import org.anand.model.candidateskills;

public class CandidateskillsRepositoryImpl extends DBSTATE implements  CandidateskillsRepository{

	@Override
	public boolean isAddSkilll(candidateskills module) {
		try {
			stmt = conn.prepareStatement("insert into candidateskills values('0',?,?,?)");
			stmt.setInt(1, module.getCandidate_id());
			stmt.setString(2, module.getSkill());
			stmt.setInt(3, module.getPassOutYear());
			int value=stmt.executeUpdate();
			return value > 0 ? true : false;
			
			
		}catch(Exception e) {
			System.out.println("Exception is e");
		}
		return false;
	}

}
