package com.tmj.model;

public class Category extends BaseModel {
	
	public Category(Integer ID, String name, String creatorID) {
		mID = ID;
		mName = name;
		mCreatorID = creatorID;
	}

	@Override
	public void addOnDB() {
		DBQueryExecutor executor = new DBQueryExecutor();
		String stmt = String.format("INSERT INTO `%s` (`categoryID`, `categoryname`, `creatorID`)" +
				"VALUES ('%s', '%s', '%s');", DBTable.ATTACHMENT, mID, mName, mCreatorID);
		
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
		String stmt = String.format("UPDATE `%s` SET `categoryname` = '%s', `creatorID` = '%s'" +
				"WHERE `%s`.`categoryID` = '%s'", DBTable.ATTACHMENT, mTaskID, mFilename, mType, DBTable.ATTACHMENT, mID);
				
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
		String stmt = String.format("DELETE FROM `%s` WHERE `%s`.`categoryID` = '%s'", DBTable.ATTACHMENT, DBTable.ATTACHMENT, mID);
				
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
	private String mName;
	private String mCreatorID;
	
	public Integer getID() {
		return mID;
	}

	public String getName() {
		return mName;
	}

	public String getCreatorID() {
		return mCreatorID;
	}

	public void setID(Integer ID) {
		this.mID = ID;
	}

	public void setName(String name) {
		this.mName = name;
	}

	public void setCreatorID(String creatorID) {
		this.mCreatorID = creatorID;
	}
}
