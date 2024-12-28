package org.anand.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class AdminModel {
	
	private int id;
	private String aname;
	private String aemail;
	private String aPassword;

}
