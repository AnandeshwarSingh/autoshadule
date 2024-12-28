package org.anand.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class InterviewscheduleModel {
	private int schedule_id;
	private int hr_id;
	private int candidate_id;
	private LocalDate interviewDate;
	private int timeslot;
	private String status;

}
