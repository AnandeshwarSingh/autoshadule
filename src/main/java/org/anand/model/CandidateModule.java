package org.anand.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CandidateModule {
	@NonNull
	private int candidate_id;
	@NonNull
	private String name;
	@NonNull
	private String email;
	@NonNull
	private String phone;
	private String address;
	private String Education;
	private String password;
}
