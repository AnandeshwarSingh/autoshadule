package org.anand.service;

import java.time.LocalDate;
import java.util.List;

import org.anand.model.CandidateModule;
import org.anand.model.HRModel;

public interface InterviewscheduleService {
	public boolean scheduleInterview(int hrId, int candidateId, LocalDate interviewDate, int timeSlot);
	public List<CandidateModule> getCandidatesWithoutInterview();
	public List<HRModel> getHRWithoutInterview();
	public List<HRModel> getAvailableHRs(LocalDate interviewDate, int timeslot);
	boolean isSendNotification(int candidateId,String message,LocalDate dateset);

}
