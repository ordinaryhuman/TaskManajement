package com.tmj.model;

import java.sql.SQLException;

import com.tmj.helper.DBQueryExecutor;
import com.tmj.helper.DBTable;

public class Tag extends BaseModel {

	public Tag(Integer taskID, String name) {
		mTaskID = taskID;
		mTagname = name;
	}
	
	public static String[] getAllTagname() {
		DBQueryExecutor executor = new DBQueryExecutor();
		String[] retval = new String[0];

		try {
			ResultSet result = executor.executeQuery(String.format("SELECT DISTINCT `tagname` FROM `%s`;", DBTable.TAG));
			if (result != null) {
				ArrayList<String> temp = new ArrayList<String>();
				while (result.next()) {
					String tagname 	= result.getString("tagname");
					temp.add(tagname);
				}
				
				retval = new String[temp.size()];
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
	
	public static Task[] getAllTaskFromTagname(String tagname) {
		DBQueryExecutor executor = new DBQueryExecutor();
		int[] tasks = new int[0];
		Task[] retval = new Task[0];

		try {
			// ambil taskID
			ResultSet result = executor.executeQuery(String.format("SELECT `taskID` FROM `%s` WHERE `tagname` = '%s';", DBTable.TAG, tagname));
			if (result != null) {
				ArrayList<int> temp = new ArrayList<int>();
				while (result.next()) {
					int taskID 	= result.getInt("taskID");
					temp.add(taskID);
				}
				
				tasks = new int[temp.size()];
				tasks = temp.toArray(retval);
			}
			// ambil task
			ArrayList<Task> temp2 = new ArrayList<Task>();
			for (int i = 0; i < tasks.length; i++) {
				ResultSet rs = executor.executeQuery(String.format("SELECT * FROM `%s` WHERE `taskID` = '%s';", DBTable.TASK, tasks[i]));
				rs.first();
				Integer categoryID 	= rs.getInt("categoryID");
				String username 	= rs.getString("username");
				String taskname 	= rs.getString("taskname");
				Boolean status 		= rs.getBoolean("email");
				String deadline		= rs.getString("deadline");
				Task task = new Task(tasks[i], categoryID, username, taskname, status, deadline);
				temp2.add(task);
			}
			
			retval = new Task[temp2.size()];
			retval = temp2.toArray(retval);
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
		String stmt = String.format("INSERT INTO `%s` (`taskID`, `tagname`)" +
				"VALUES ('%s', '%s');", DBTable.TAG, mTaskID, mTagname);
		
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
				"WHERE `%s`.`taskID` = '%s' AND `%s`.`tagname` = '%s'", DBTable.TAG, mTaskID, mTagname, DBTable.TAG, mTaskID, DBTable.TAG, mTagname);
				
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
		String stmt = String.format("DELETE FROM `%s` WHERE `%s`.`taskID` = '%s' AND `%s`.`tagname` = '%s'", DBTable.TAG, DBTable.TAG, mTaskID, DBTable.TAG, mTagname);
				
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
