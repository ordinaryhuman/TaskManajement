package com.tmj.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tmj.helper.DBQueryExecutor;
import com.tmj.helper.DBTable;

public class Tag extends BaseModel {

	public Tag(Integer taskID, String name) {
		mTaskID = taskID;
		mTagname = name;
	}
	
	public static Tag[] getAllTag() {
		DBQueryExecutor executor = new DBQueryExecutor();
		Tag[] retval = new Tag[0];

		try {
			ResultSet result = executor.executeQuery(String.format("SELECT * FROM `%s`;", DBTable.TAG));
			if (result != null) {
				ArrayList<Tag> temp = new ArrayList<Tag>();
				while (result.next()) {
					Integer taskID	= result.getInt("taskID");
					String tagname 	= result.getString("tagname");
					Tag tag = new Tag(taskID, tagname);
					temp.add(tag);
				}
				
				retval = new Tag[temp.size()];
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
		Task[] retval = new Task[0];

		try {
			ResultSet result = executor.executeQuery(String.format("SELECT * FROM `%s` WHERE `taskID` IN (SELECT `taskID` FROM `%s` WHERE `tagname` = '%s');", DBTable.TASK, DBTable.TAG, tagname));
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
	
	public static Integer getAvailableTagID() {
		// get all tag
		Tag[] tags = Tag.getAllTag();
		Integer i = 1;
		while(true) {
			boolean available = true;
			
			// traverse to all exist tag, see if i available
			for(Tag tag:tags) {
				if(tag.getID() == i) {
					available = false;
					break;
				}
			}
			
			if(available)
				return i;
			
			i++;
		}
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
