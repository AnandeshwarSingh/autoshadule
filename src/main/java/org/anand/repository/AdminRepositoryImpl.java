package org.anand.repository;

import java.util.List;

import org.anand.model.AdminModel;

public class AdminRepositoryImpl extends DBSTATE implements AdminRepository {

	int value=0;
	@Override
	public boolean loginAdmin(String adminemail, String password) {
		try
		{
			stmt=conn.prepareStatement("select * from admin where aemain=? and  apassword=?");
			stmt.setString(1, adminemail);
			stmt.setString(2, password);
			rs=stmt.executeQuery();
			if(rs.next())
			{
			  value++;
			}
			return value>0?true:null;
		}catch(Exception e)
		{
			System.out.println("Error is"+e);
			return false;
		}
		
	}
	

	

}
