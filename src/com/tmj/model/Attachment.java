package com.tmj.model;

import java.sql.SQLException;

import com.tmj.helper.DBQueryExecutor;
import com.tmj.helper.DBTable;

public class Attachment extends BaseModel {
	
	public Attachment(Integer ID, Integer taskID, String filename, String type) {
		mID = ID;
		mTaskID = taskID;
		mFilename = filename;
		mType = type;
	}
	
	@Override
	public void addOnDB() {
		DBQueryExecutor executor = new DBQueryExecutor();
		String stmt = String.format("INSERT INTO `%s` (`attachmentID`, `taskID`, `filename`, `type`)" +
				"VALUES ('%s', '%s', '%s', '%s');", DBTable.ATTACHMENT, mID, mTaskID, mFilename, mType);
		
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
		String stmt = String.format("UPDATE `%s` SET `taskID` = '%s', `filename` = '%s', `type` = '%s'" +
				"WHERE `%s`.`attachmentID` = '%s'", DBTable.ATTACHMENT, mTaskID, mFilename, mType, DBTable.ATTACHMENT, mID);
				
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
		String stmt = String.format("DELETE FROM `%s` WHERE `%s`.`attachmentID` = '%s'", DBTable.ATTACHMENT, DBTable.ATTACHMENT, mID);
				
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
	private Integer mTaskID;
	private String mFilename;
	private String mType;
	
	public Integer getID() {
		return mID;
	}
	public Integer getTaskID() {
		return mTaskID;
	}
	public String getFilename() {
		return mFilename;
	}
	public String getType() {
		return mType;
	}
	
	public void setID(Integer ID) {
		this.mID = ID;
	}
	public void setTaskID(Integer taskID) {
		this.mTaskID = taskID;
	}
	public void setFilename(String filename) {
		this.mFilename = filename;
	}
	public void setType(String type) {
		this.mType = type;
	}
}
