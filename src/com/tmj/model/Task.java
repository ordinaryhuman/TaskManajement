package com.tmj.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tmj.helper.DBQueryExecutor;
import com.tmj.helper.DBTable;

public class Task extends BaseModel {
	
	public Task(Integer ID, Integer categoryID, String username, String taskname, Boolean status, String deadline) {
		mID = ID;
		mCategoryID = categoryID;
		mUsername = username;
		mTaskname = taskname;
		mStatus = status;
		mDeadline = deadline;
	}
	
	public static Task[] getAllTask() {
		// TODO
		return null;
	}
	
	public static Task getTaskFromTaskID(Integer id) {
		// TODO
		return null;
	}
	
	public static Task[] getTasksFromCategoryID(Integer categoryID) {
		// TODO
		return null;
	}
	
	public static Task[] getTasksFromUsername(String username) {
		// TODO
		return null;
	}
	
	public static Task[] getTasksDoneFromUsername(String username) {
		// TODO
		return null;
	}
	
	public static Task[] getTasksNotDoneFromUsername(String username) {
		// TODO
		return null;
	}
	
	public static Task[] getTaskFromQuery(String query) {
		DBQueryExecutor executor = new DBQueryExecutor();
		Task[] retval = new Task[0];

		try {
			ResultSet result = executor.executeQuery(String.format("SELECT * FROM `%s` WHERE `taskname` LIKE '%s';", DBTable.TASK, "%" + query + "%"));
			if (result != null) {
				ArrayList<Task> temp = new ArrayList<Task>();
				
				while (result.next()) {
					Integer ID		 	= result.getInt("taskID");
					Integer categoryID 	= result.getInt("categoryID");
					String username 	= result.getString("username");
					String taskname 	= result.getString("taskname");
					Boolean status 		= result.getBoolean("email");
					String deadline		= result.getString("deadline");
					Task task = new Task(ID, categoryID, username, taskname, status, deadline);
					temp.add(task);
				}
				
				retval = new Task[temp.size()];
				retval = temp.toArray(retval);
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
		String stmt = String.format("INSERT INTO `%s` (`taskID`, `categoryID`, `username`, `taskname`, `status`, `deadline`)" +
				"VALUES ('%s', '%s', '%s', '%s', '%s', '%s');", DBTable.TASK, mID, mCategoryID, mUsername, mTaskname, mStatus, mDeadline);
		
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
		String stmt = String.format("UPDATE `%s` SET `categoryID` = '%s', `username` = '%s', `taskname` = '%s', `status` = '%s', `deadline` = '%s'" +
				"WHERE `%s`.`taskID` = '%s'", DBTable.TASK, mCategoryID, mUsername, mTaskname, DBTable.TASK, mID);
				
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
		String stmt = String.format("DELETE FROM `%s` WHERE `%s`.`taskID` = '%s'", DBTable.TASK, DBTable.TASK, mID);
				
		try {
			executor.executeQuery(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			executor.closeQuery();
			executor.closeConnection();
		}
	}
	
	public User getOwner() {
		return User.getUserFromUsername(mUsername);
	}
	
	private Integer mID;
	private Integer mCategoryID;
	private String mUsername;
	private String mTaskname;
	private Boolean mStatus;
	private String mDeadline;
	
	public Integer getID() {
		return mID;
	}

	public Integer getCategoryID() {
		return mCategoryID;
	}

	public String getUsername() {
		return mUsername;
	}

	public String getTaskname() {
		return mTaskname;
	}

	public Boolean gemStatus() {
		return mStatus;
	}

	public String getDeadline() {
		return mDeadline;
	}

	public void setID(Integer ID) {
		this.mID = ID;
	}

	public void setCategoryID(Integer categoryID) {
		this.mCategoryID = categoryID;
	}

	public void setUsername(String username) {
		this.mUsername = username;
	}

	public void setTaskname(String taskname) {
		this.mTaskname = taskname;
	}

	public void setStatus(Boolean status) {
		this.mStatus = status;
	}

	public void setmDeadline(String mDeadline) {
		this.mDeadline = mDeadline;
	}
}
