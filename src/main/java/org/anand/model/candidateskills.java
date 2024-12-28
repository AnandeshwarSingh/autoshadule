package org.anand.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class candidateskills {
	private int skillId;
	private int candidate_id;
	private String skill;
	private int passOutYear;
}
