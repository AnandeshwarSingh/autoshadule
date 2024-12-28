package org.anand.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.anand.model.CandidateModule;
import org.anand.model.HRModel;

public class InterviewscheduleRepositoryImpl extends DBSTATE implements InterviewscheduleRepository {

	@Override
	public boolean scheduleInterview(int hrId, int candidateId, LocalDate interviewDate, int timeSlot) {
		// TODO Auto-generated method stub
		try {
			if (isCandidateScheduled(candidateId)) {
				System.out.println("Candidate interview already scheduled.");
				return false;
			}
			stmt = conn
					.prepareStatement("SELECT COUNT(*) FROM interviewschedule WHERE hr_id = ? AND interviewdate = ?");
			stmt.setInt(1, hrId);
			stmt.setDate(2, java.sql.Date.valueOf(interviewDate));
			rs = stmt.executeQuery();
			if (rs.next() && rs.getInt(1) >= 8) {
				System.out.println("HR has reached the daily limit of 8 interviews.");
				return false;
			}

			stmt = conn.prepareStatement(
					"INSERT INTO interviewschedule (hr_id, candidate_id, interviewdate, timeslot, sataus) VALUES (?, ?, ?, ?, ?)");
			stmt.setInt(1, hrId);
			stmt.setInt(2, candidateId);
			stmt.setDate(3, java.sql.Date.valueOf(interviewDate));
			stmt.setInt(4, timeSlot);
			stmt.setString(5, "Scheduled");
			int value = stmt.executeUpdate();
			return value > 0 ? true : false;

		} catch (Exception e) {
			System.out.println("Exception is " + e);
		}

		return false;
	}

//--------------candidate Without Interview Schadule-----------------------------------------
	@Override
	public List<CandidateModule> getCandidatesWithoutInterview() {
		List<CandidateModule> candidates = new ArrayList<>();
		try {
			stmt = conn.prepareStatement("SELECT c.candidate_id, c.name, c.email, c.phone " + "FROM candidate c "
					+ "LEFT JOIN interviewschedule i ON c.candidate_id = i.candidate_id "
					+ "WHERE i.candidate_id IS NULL");
			rs = stmt.executeQuery();
			while (rs.next()) {
				candidates.add(new CandidateModule(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
			return candidates;

		} catch (Exception e) {
			System.out.println("Exception is");
		}
		return null;
	}

//---------------------------HR Without Interview Schadule-----------------------------------
	@Override
	public List<HRModel> getHRWithoutInterview() {
		// TODO Auto-generated method stub
		List<HRModel> hrdata = new ArrayList<>();
		try {
			stmt = conn.prepareStatement("SELECT h.hr_id, h.name, h.email, h.phone " + "FROM hr h "
					+ "LEFT JOIN interviewschedule i ON h.hr_id = i.hr_id " + "WHERE i.hr_id IS NULL");
			rs = stmt.executeQuery();
			while (rs.next()) {
				hrdata.add(new HRModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
			return hrdata;
		} catch (Exception e) {
			System.out.println("Exception is " + e);
		}
		return null;
	}

	@Override
	public boolean isCandidateScheduled(int candidateId) {
		// TODO Auto-generated method stub
		try {
			stmt = conn.prepareStatement("SELECT COUNT(*) FROM interviewschedule WHERE candidate_id = ?");
			stmt.setInt(1, candidateId);
			rs = stmt.executeQuery();
			if (rs.next() && rs.getInt(1) > 0) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("Exception is " + e);
		}
		return false;
	}

	@Override
	public List<HRModel> getAvailableHRs(LocalDate interviewDate, int timeslot) {
		// TODO Auto-generated method stub
		List<HRModel> availableHRs = new ArrayList<>();
		try {
			stmt = conn.prepareStatement("SELECT hr.hr_id, hr.name, hr.email, hr.phone FROM hr WHERE hr.hr_id NOT IN (SELECT hr_id FROM interviewschedule WHERE interviewdate = ? AND timeslot = ?)");
			stmt.setDate(1, java.sql.Date.valueOf(interviewDate));
	        stmt.setInt(2, timeslot);
	        rs = stmt.executeQuery();
	        while(rs.next()) {
	        	availableHRs.add(new HRModel(rs.getInt("hr_id"),rs.getString("name"),rs.getString("email"),rs.getString("phone")));
	        }
	        return availableHRs;
		} catch (Exception e) {
			System.out.println("Exception is " + e);
		}

		return null;
	}

	@Override
	public boolean isSendNotification(int candidateId, String message, LocalDate dateset) {
		// TODO Auto-generated method stub
		try {
			
			stmt = conn.prepareStatement("INSERT INTO notification (candidate_id, message, dateset) VALUES (?, ?, ?)");
			stmt.setInt(1, candidateId);
	        stmt.setString(2, message);
	        stmt.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
	        stmt.executeUpdate();
	        
	        stmt = conn.prepareStatement("select email from candidate where candidate_id = ?");
	        stmt.setInt(1, candidateId);
	        rs = stmt.executeQuery();
	        String email = "";
	        if(rs.next()) {
	        	email = rs.getString("email");
	        }
	        String from = "anandeshwar.chadnel@gmail.com";
	        String subject="Interview Shaduled for AUTOSEND";
	        GemailSender gEmailSender = new GemailSender();
	        
	        boolean b = gEmailSender.sendEmail(email,from,subject,message);
	        if(b) {
	        	System.out.println("Mail is send to candidate");
	        }else {
	        	System.out.println("There is a problem to sending mail");
	        }
	        
	        return true;
		}catch(Exception e) {
			System.out.println("Exception is "+e);
		}
		
		return false;
	}

}
