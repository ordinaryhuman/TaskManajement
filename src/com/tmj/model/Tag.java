package com.tmj.model;

import java.sql.SQLException;

import com.tmj.helper.DBQueryExecutor;
import com.tmj.helper.DBTable;

public class Tag extends BaseModel {

	public Tag(Integer ID, String name) {
		mID = ID;
		mTagname = name;
	}
	
	@Override
	public void addOnDB() {
		DBQueryExecutor executor = new DBQueryExecutor();
		String stmt = String.format("INSERT INTO `%s` (`taskID`, `tagname`)" +
				"VALUES ('%s', '%s');", DBTable.TAG, mID, mTagname);
		
		try {
			executor.executeQuery(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			executor.closeQuery();
			executor.closeConnection();
		}
	}
	@Override
	public void editOnDB() {
		DBQueryExecutor executor = new DBQueryExecutor();
		String stmt = String.format("UPDATE `%s` SET `taskID` = '%s', `tagname` = '%s'" +
				"WHERE `%s`.`taskID` = '%s' AND `%s`.`tagname` = '%s'", DBTable.TAG, mID, mTagname, DBTable.TAG, mID, DBTable.TAG, mTagname);
				
		try {
			executor.executeQuery(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			executor.closeQuery();
			executor.closeConnection();
		}
	}
	@Override
	public void deleteOnDB() {
		DBQueryExecutor executor = new DBQueryExecutor();
		String stmt = String.format("DELETE FROM `%s` WHERE `%s`.`taskID` = '%s' AND `%s`.`tagname` = '%s'", DBTable.TAG, DBTable.TAG, mID, DBTable.TAG, mTagname);
				
		try {
			executor.executeQuery(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			executor.closeQuery();
			executor.closeConnection();
		}
	}
	
	private Integer mID;
	private String mTagname;
	
	public Integer getTaskID() {
		return mID;
	}
	public String getTagname() {
		return mTagname;
	}
	public void setTaskID(Integer ID) {
		this.mID = ID;
	}
	public void setTagname(String tagname) {
		this.mTagname = tagname;
	}
}
