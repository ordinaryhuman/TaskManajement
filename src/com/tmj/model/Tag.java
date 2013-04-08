package com.tmj.model;

public class Tag extends BaseModel {

	public Tag(Integer ID, String name) {
		mID = ID;
		mName = name;
	}
	
	@Override
	public void addOnDB() {
		DBQueryExecutor executor = new DBQueryExecutor();
		String stmt = String.format("INSERT INTO `%s` (`taskID`, `tagname`)" +
				"VALUES ('%s', '%s');", DBTable.ATTACHMENT, mID, mName);
		
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
				"WHERE `%s`.`taskID` = '%s' AND `%s`.`tagname` = '%s'", DBTable.ATTACHMENT, mID, mName, DBTable.ATTACHMENT, mID, DBTable.ATTACHMENT, mName);
				
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
		String stmt = String.format("DELETE FROM `%s` WHERE `%s`.`taskID` = '%s' AND `%s`.`tagname` = '%s'", DBTable.ATTACHMENT, DBTable.ATTACHMENT, mID, DBTable.ATTACHMENT, mName);
				
		try {
			executor.executeQuery(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			executor.closeQuery();
			executor.closeConnection();
		}
	}
	
	private Integer mTaskID;
	private String mTagname;
	
	public Integer getTaskID() {
		return mTaskID;
	}
	public String getTagname() {
		return mTagname;
	}
	public void setTaskID(Integer taskID) {
		this.mTaskID = taskID;
	}
	public void setTagname(String tagname) {
		this.mTagname = tagname;
	}
}
