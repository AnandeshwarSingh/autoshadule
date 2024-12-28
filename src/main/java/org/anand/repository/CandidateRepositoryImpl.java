package org.anand.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.anand.model.CandidateModule;
import org.anand.model.HRModel;

public class CandidateRepositoryImpl extends DBSTATE implements CandidateRepository {

	List<CandidateModule> list;

//----------------------------------------Login Candidate------------------------------------
	@Override
	public Optional<Integer> loginCandidate(String email, String password) {
		try {
			stmt = conn.prepareStatement("Select candidate_id from candidate where email = ? and password = ?");
			stmt.setString(1, email);
			stmt.setString(2, password);

			rs = stmt.executeQuery();
			if (rs.next()) {
				return Optional.of(rs.getInt("candidate_id"));
			}
		} catch (Exception e) {
			System.out.println("Exception is" + e);
		}
		return Optional.empty();
	}

//-------------------------Add Candidate-------------------------------------------------------

	@Override
	public boolean isCandidateAdd(CandidateModule model) {
		// TODO Auto-generated method stub
		try {
			stmt = conn.prepareStatement("insert into candidate values('0',?,?,?,?,?,?)");
			stmt.setString(1, model.getName());
			stmt.setString(2, model.getEmail());
			stmt.setString(3, model.getPhone());
			stmt.setString(4, model.getAddress());
			stmt.setString(5, model.getEducation());
			stmt.setString(6, model.getPassword());

			int value = stmt.executeUpdate();
			return value > 0 ? true : false;

		} catch (Exception e) {
			System.out.println("Exception is " + e);
		}
		return false;
	}

//----------------------------Update Candidate Detail----------------------------------------	

	@Override
	public boolean updateCandidateField(int candidateId, String field, String newValue) {
		// TODO Auto-generated method stub

		try {
			if (!field.equals("name") && !field.equals("email") && !field.equals("phone") && !field.equals("address")
					&& !field.equals("education") && !field.equals("password")) {
				System.out.println("Invalid field. Please choose a valid field to update.");
				return false;
			} else {
				stmt = conn.prepareStatement("Update candidate set " + field + " = ? where candidate_id = ?");
				stmt.setString(1, newValue);
				stmt.setInt(2, candidateId);
				int value = stmt.executeUpdate();
				return value > 0 ? true : false;
			}

		} catch (Exception e) {
			System.out.println("Exception is : " + e);
		}
		return false;
	}

// -----------------------------------------------Remove candidate ---------------------------------
	@Override
	public boolean isRemiveCandidate(int candidateId) {
		// TODO Auto-generated method stub

		try {
			stmt = conn.prepareStatement("Delete from candidate where candidate_id = ?");
			stmt.setInt(1, candidateId);
			int value = stmt.executeUpdate();
			return value > 0 ? true : false;
		} catch (Exception e) {
			System.out.println("Exception is " + e);
		}
		return false;
	}
//--------------------------------------View Candidate--------------------------------------

	@Override
	public Optional<List<CandidateModule>> getAllCandidate() {
		// TODO Auto-generated method stub
		try {
			list = new ArrayList<CandidateModule>();
			stmt = conn.prepareStatement("select * from candidate");
			rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new CandidateModule(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7)));
			}
			return list.size() > 0 ? Optional.ofNullable(list) : Optional.empty();

		} catch (Exception e) {
			System.out.println("Exception is  " + e);

		}
		return Optional.empty();
	}
//--------------------------------Search Candidate By name-------------------------------
	@Override
	public Optional<List<CandidateModule>> searchCandidatesByName(String name) {
		// TODO Auto-generated method stub
		List<CandidateModule> candidates = new ArrayList<>();
		try {
			stmt = conn.prepareStatement("select * from candidate where name like ?");
			stmt.setString(1, "%" + name + "%");
			rs = stmt.executeQuery();
			while (rs.next()) {
				candidates.add(new CandidateModule(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7)));
			}
			return candidates.size() > 0 ? Optional.ofNullable(candidates) : Optional.empty();

		} catch (Exception e) {
			System.out.println("Exception is " + e);
		}
		return Optional.empty();
	}

}
