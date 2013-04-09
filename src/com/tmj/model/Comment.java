package com.tmj.model;

import java.sql.SQLException;

import com.tmj.helper.DBQueryExecutor;
import com.tmj.helper.DBTable;

public class Comment extends BaseModel {
	
	public Comment(Integer ID, Integer taskID, String username, String content, String timestamp) {
		mID = ID;
		mTaskID = taskID;
		mUsername = username;
		mContent = content;
		mTimestamp = timestamp;
	}
	
	public static Comment[] getAllComment() {
		DBQueryExecutor executor = new DBQueryExecutor();
		Comment[] retval;

		try {
			ResultSet result = executor.executeQuery(String.format("SELECT * FROM `%s`;", DBTable.COMMENT));
			if (result != null) {
				result.last();
				retval = new Comment[result.getRow()];
				result.beforeFirst();
				int i = 0;
				while (result.next()) {
					int commentID		= result.getInt("commentID");
					int taskID			= result.getInt("taskID");
					String username 	= result.getString("username");
					String content 		= result.getString("content");
					String timestamps 	= result.getString("timestamps");
					retval[i] = new Comment(commentID, taskID, username, content, timestamps);
					i++;
				}
			}
		} catch (SQLException sEx) {
			sEx.printStackTrace();
		} finally {
			executor.closeQuery();
			executor.closeConnection();
		}
		
		return retval;
	}
	
	public static Comment[] getCommentFromTaskID(String taskID) {
		DBQueryExecutor executor = new DBQueryExecutor();
		Comment[] retval;

		try {
			ResultSet result = executor.executeQuery(String.format("SELECT * FROM `%s` WHERE `taskID` = '%s';", DBTable.COMMENT, taskID));
			if (result != null) {
				result.last();
				retval = new Comment[result.getRow()];
				result.beforeFirst();
				int i = 0;
				while (result.next()) {
					int commentID		= result.getInt("commentID");
					String username 	= result.getString("username");
					String content 		= result.getString("content");
					String timestamps = result.getString("timestamps");
					retval[i] = new Comment(commentID, taskID, username, content, timestamps);
					i++;
				}
			}
		} catch (SQLException sEx) {
			sEx.printStackTrace();
		} finally {
			executor.closeQuery();
			executor.closeConnection();
		}
		
		return retval;
	}
	
	public static Comment[] getCommentFromUsername(String username) {
		DBQueryExecutor executor = new DBQueryExecutor();
		Comment[] retval;

		try {
			ResultSet result = executor.executeQuery(String.format("SELECT * FROM `%s` WHERE `username` = '%s';", DBTable.COMMENT, username));
			if (result != null) {
				result.last();
				retval = new Comment[result.getRow()];
				result.beforeFirst();
				int i = 0;
				while (result.next()) {
					int commentID		= result.getInt("commentID");
					int taskID			= result.getInt("taskID");
					String content 		= result.getString("content");
					String timestamps = result.getString("timestamps");
					retval[i] = new Comment(commentID, taskID, username, content, timestamps);
					i++;
				}
			}
		} catch (SQLException sEx) {
			sEx.printStackTrace();
		} finally {
			executor.closeQuery();
			executor.closeConnection();
		}
		
		return retval;
	}

	@Override
	public void addOnDB() {
		DBQueryExecutor executor = new DBQueryExecutor();
		String stmt = String.format("INSERT INTO `%s` (`commentID`, `taskID`, `username`, `content`, `timestamp`)" +
				"VALUES ('%s', '%s', '%s', '%s', '%s');", DBTable.COMMENT, mID, mTaskID, mUsername, mContent, mTimestamp);
		
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
				"WHERE `%s`.`commentID` = '%s'", DBTable.COMMENT, mTaskID, mUsername, mContent, mTimestamp, DBTable.ATTACHMENT, mID);
				
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
		String stmt = String.format("DELETE FROM `%s` WHERE `%s`.`commentID` = '%s'", DBTable.COMMENT, DBTable.COMMENT, mID);
				
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
