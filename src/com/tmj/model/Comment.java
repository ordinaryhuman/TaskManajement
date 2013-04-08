package com.tmj.model;

public class Comment extends BaseModel {
	
	public Comment(Integer ID, Integer taskID, String username, String content, String timestamp) {
		mID = ID;
		mTaskID = taskID;
		mUsername = username;
		mContent = content;
		mTimestamp = timestamp;
	}

	@Override
	public void addOnDB() {
		DBQueryExecutor executor = new DBQueryExecutor();
		String stmt = String.format("INSERT INTO `%s` (`commentID`, `taskID`, `username`, `content`, `timestamp`)" +
				"VALUES ('%s', '%s', '%s', '%s', '%s');", DBTable.ATTACHMENT, mID, mTaskID, mUsername, mContent, mTimestamp);
		
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
		String stmt = String.format("UPDATE `%s` SET `taskID` = '%s', `username` = '%s', `content` = '%s', `timestamp` = '%s'" +
				"WHERE `%s`.`commentID` = '%s'", DBTable.ATTACHMENT, mTaskID, mUsername, mContent, mTimestamp, DBTable.ATTACHMENT, mID);
				
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
		String stmt = String.format("DELETE FROM `%s` WHERE `%s`.`commentID` = '%s'", DBTable.ATTACHMENT, DBTable.ATTACHMENT, mID);
				
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
	private String mUsername;
	private String mContent;
	private String mTimestamp;
	
	public Integer getID() {
		return mID;
	}

	public Integer getTaskID() {
		return mTaskID;
	}

	public String getUsername() {
		return mUsername;
	}

	public String getContent() {
		return mContent;
	}

	public String getTimestamp() {
		return mTimestamp;
	}

	public void setID(Integer ID) {
		this.mID = ID;
	}

	public void setTaskID(Integer taskID) {
		this.mTaskID = taskID;
	}

	public void setUsername(String username) {
		this.mUsername = username;
	}

	public void setContent(String content) {
		this.mContent = content;
	}

	public void setTimestamp(String timestamp) {
		this.mTimestamp = timestamp;
	}
}
