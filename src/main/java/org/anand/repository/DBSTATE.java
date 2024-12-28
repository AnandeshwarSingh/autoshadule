package org.anand.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBSTATE {
	DBConfig config = DBConfig.getInstance();
	protected Connection conn = config.getConn();
	protected PreparedStatement stmt = config.getStatement();
	protected ResultSet rs = config.getResult();
	protected CallableStatement cstmt = config.getCallStatement();
}
