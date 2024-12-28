package org.anand.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.anand.model.HRModel;

public class HRRepositoryImpl extends DBSTATE implements HRRepository{
	
	List<HRModel> list;
//------------------------------Login HR---------------------------------------------------
	@Override
	public Optional<Integer> loginHR(String email, String password) {
		int value = 0;
		
		try {
			stmt = conn.prepareStatement("select hr_id from hr where email = ? and password = ?");
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			if(rs.next()) {
				return Optional.of(rs.getInt("hr_id"));
			}
			
			
		}catch(Exception e) {
			System.out.println("Exception is "+e);
		}
		return Optional.empty();
	}
//---------------------------------------Add HR-----------------------------------------------
	@Override
	public boolean isHRAdd(HRModel model) {
		try {
			stmt = conn.prepareStatement("insert into hr values('0',?,?,?,?)");
			stmt.setString(1, model.getHname());
			stmt.setString(2, model.getHemail());
			stmt.setString(3, model.getHphone());
			stmt.setString(4, model.getHpassword());
			int value = stmt.executeUpdate();
			return value > 0 ? true:false;
			
		}catch(Exception e) {
			System.out.println("Exception is :  "+e);
			
		}
		return false;
	}
//-----------------------------------View HR---------------------------------------------
	@Override
	public Optional<List<HRModel>> getAllHr(){
		try {
			list = new ArrayList<HRModel>();
			stmt = conn.prepareStatement("select * from hr");
			rs = stmt.executeQuery();
			while(rs.next()) {
				list.add( new HRModel(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
				
			}
			return list.size() > 0 ? Optional.ofNullable(list) : Optional.empty();
			
		}catch(Exception e) {
			System.out.println("Exception is  "+e );
			return Optional.empty();
		}
		
		
		
	}
	
//---------------------------------Update HR Details--------------------------------	
	@Override
	public boolean updateHRField(int hrId, String field, String newValue) {
		// TODO Auto-generated method stub
		
		try {
			if(!field.equals("name") && !field.equals("email") && !field.equals("phone") && !field.equals("password")) {
				System.out.println("Invalid field. Please choose a valid field to update.");
				return false;
			}else {
				stmt = conn.prepareStatement("Update hr set "+field+" = ? where hr_id = ?");
				stmt.setString(1, newValue);
				stmt.setInt(2, hrId);
				int value = stmt.executeUpdate();
				return value> 0 ? true:false;
			}
			
		}catch(Exception e) {
			System.out.println("Exception is : "+e);
		}
		return false;
	}
	
// -----------------------------------Remove HR----------------------------------------------	
	@Override
	public boolean isRemiveHR(int hrid) {
		// TODO Auto-generated method stub
		
		try {
			stmt = conn.prepareStatement("Delete from hr where hr_id = ?");
			stmt.setInt(1, hrid);
			int value = stmt.executeUpdate();
			return value > 0 ? true:false;
		}catch(Exception e) {
			System.out.println("Exception is "+e);
		}
		return false;
	}
//-------------------------------Search HR By name------------------------------------------
	@Override
	public Optional<List<HRModel>> searchHrByName(String name) {
		// TODO Auto-generated method stub
		List<HRModel> hrdata = new ArrayList<>();
		try {
			stmt = conn.prepareStatement("Select * from hr where name like ?");
			stmt.setString(1, "%"+name+"%");
			rs = stmt.executeQuery();
			while(rs.next()) {
				hrdata.add(new HRModel(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
			}
			return hrdata.size()> 0 ? Optional.ofNullable(hrdata) : Optional.empty();
		}catch(Exception e) {
			System.out.println("Exception is "+e);
		}
		return Optional.empty();
	}

}
