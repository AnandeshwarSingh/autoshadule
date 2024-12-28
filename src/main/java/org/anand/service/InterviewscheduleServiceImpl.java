package org.anand.service;

import java.time.LocalDate;
import java.util.List;

import org.anand.model.CandidateModule;
import org.anand.model.HRModel;
import org.anand.repository.*;



public class InterviewscheduleServiceImpl implements InterviewscheduleService{
	
	//HRRepository hrrepo = new HRRepositoryImpl();
	InterviewscheduleRepository intvsche = new InterviewscheduleRepositoryImpl();

	@Override
	public boolean scheduleInterview(int hrId, int candidateId, LocalDate interviewDate, int timeSlot) {
		// TODO Auto-generated method stub
		return intvsche.scheduleInterview(hrId, candidateId, interviewDate, timeSlot);
	}

	@Override
	public List<CandidateModule> getCandidatesWithoutInterview() {
		// TODO Auto-generated method stub
		return intvsche.getCandidatesWithoutInterview();
	}

	@Override
	public List<HRModel> getHRWithoutInterview() {
		// TODO Auto-generated method stub
		return intvsche.getHRWithoutInterview();
	}

	@Override
	public List<HRModel> getAvailableHRs(LocalDate interviewDate, int timeslot) {
		// TODO Auto-generated method stub
		return intvsche.getAvailableHRs(interviewDate, timeslot);
	}

	@Override
	public boolean isSendNotification(int candidateId, String message, LocalDate dateset) {
		// TODO Auto-generated method stub
		System.out.println("I am in service");
		
		return intvsche.isSendNotification(candidateId, message, dateset);
	}
	
	
	

}
