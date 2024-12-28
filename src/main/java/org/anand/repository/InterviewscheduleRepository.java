package org.anand.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.anand.model.*;

public interface InterviewscheduleRepository {
	public boolean scheduleInterview(int hrId, int candidateId, LocalDate interviewDate, int timeSlot);
	public List<CandidateModule> getCandidatesWithoutInterview();
	public List<HRModel> getHRWithoutInterview();
	boolean isCandidateScheduled(int candidateId);
	public List<HRModel> getAvailableHRs(LocalDate interviewDate, int timeslot);
	boolean isSendNotification(int candidateId,String message,LocalDate dateset);
	
	

}
