package com.servletStore.settings.section.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbconnect.DBConnection;

public class SectionImpl implements SectionDAO
{
	DBConnection dbconnect=new DBConnection();
	Connection connection;
	
	public SectionImpl() 
	{
	
		connection=dbconnect.getConnection();

	}

	@Override
	public int addSection(SectionPojo sectionPojo) 
	{
		int status=0;
		String query="insert into sections_master(`name`) values (?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, sectionPojo.getName());
			status = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public List<SectionPojo> getSectionDetails() 
	{
		List<SectionPojo> list=new ArrayList<SectionPojo>();
		String query="SELECT `id`, `name` FROM `sections_master";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				//System.out.println("id "+rs.getInt("id") + " "+ rs.getString("name"));
				SectionPojo pojo=new SectionPojo();
			    pojo.setId(rs.getInt("id"));
			    pojo.setName(rs.getString("name"));
				list.add(pojo);			
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return list;
		
	}
	
}
