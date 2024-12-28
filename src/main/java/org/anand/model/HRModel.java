package org.anand.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class HRModel {
	@NonNull
	private int hrid;
	@NonNull
	private String hname;
	@NonNull
	private String hemail;
	@NonNull
	private String hphone;
	private String hpassword;
	
}
