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
		DBQueryExecutor executor = new DBQueryExecutor();
		Task[] retval = new Task[0];

		try {
			ResultSet result = executor.executeQuery(String.format("SELECT * FROM `%s`;", DBTable.TASK));
			if (result != null) {
				ArrayList<Task> temp = new ArrayList<Task>();
				while (result.next()) {
					Integer taskID		= result.getInt("taskID");
					Integer categoryID	= result.getInt("categoryID");
					String username = result.getString("username");
					String taskname = result.getString("taskname");
					Boolean status	= result.getBoolean("status");
					String deadline = result.getString("deadline");
					Task task = new Task(taskID, categoryID, username, taskname, status, deadline);
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
	
	public static Task getTaskFromTaskID(Integer id) {
		DBQueryExecutor executor = new DBQueryExecutor();
		Task retval = null;

		try {
			ResultSet result = executor.executeQuery(String.format("SELECT * FROM `%s` WHERE `taskID` = '%s';", DBTable.TASK, id));
			if (result != null) {
				while (result.next()) {
					Integer categoryID	= result.getInt("categoryID");
					String username = result.getString("username");
					String taskname = result.getString("taskname");
					Boolean status	= result.getBoolean("status");
					String deadline = result.getString("deadline");
					retval = new Task(id, categoryID, username, taskname, status, deadline);
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
	
	public static Task[] getTasksFromCategoryID(Integer categoryID) {
		DBQueryExecutor executor = new DBQueryExecutor();
		Task[] retval = new Task[0];

		try {
			ResultSet result = executor.executeQuery(String.format("SELECT * FROM `%s` WHERE `categoryID` = '%s';", DBTable.TASK, categoryID));
			if (result != null) {
				ArrayList<Task> temp = new ArrayList<Task>();
				while (result.next()) {
					Integer taskID		= result.getInt("taskID");
					String username = result.getString("username");
					String taskname = result.getString("taskname");
					Boolean status	= result.getBoolean("status");
					String deadline = result.getString("deadline");
					Task task = new Task(taskID, categoryID, username, taskname, status, deadline);
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
	
	public static Task[] getTasksFromUsername(String username) {
		DBQueryExecutor executor = new DBQueryExecutor();
		Task[] retval = new Task[0];

		try {
			ResultSet result = executor.executeQuery(String.format("SELECT * FROM `%s` WHERE `username` = '%s';", DBTable.TASK, username));
			if (result != null) {
				ArrayList<Task> temp = new ArrayList<Task>();
				while (result.next()) {
					Integer taskID		= result.getInt("taskID");
					Integer categoryID	= result.getInt("categoryID");
					String taskname = result.getString("taskname");
					Boolean status	= result.getBoolean("status");
					String deadline = result.getString("deadline");
					Task task = new Task(taskID, categoryID, username, taskname, status, deadline);
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
	
	public static Task[] getTasksDoneFromUsername(String username) {
		DBQueryExecutor executor = new DBQueryExecutor();
		Task[] retval = new Task[0];

		try {
			ResultSet result = executor.executeQuery(String.format("SELECT * FROM `%s` WHERE `username` = '%s' AND `status` = '%s';", DBTable.TASK, username, 1));
			if (result != null) {
				ArrayList<Task> temp = new ArrayList<Task>();
				while (result.next()) {
					Integer taskID		= result.getInt("taskID");
					Integer categoryID	= result.getInt("categoryID");
					String taskname = result.getString("taskname");
					String deadline = result.getString("deadline");
					Task task = new Task(taskID, categoryID, username, taskname, true, deadline);
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
	
	public static Task[] getTasksNotDoneFromUsername(String username) {
		DBQueryExecutor executor = new DBQueryExecutor();
		Task[] retval = new Task[0];

		try {
			ResultSet result = executor.executeQuery(String.format("SELECT * FROM `%s` WHERE `username` = '%s' AND `status` = '%s';", DBTable.TASK, username, 0));
			if (result != null) {
				ArrayList<Task> temp = new ArrayList<Task>();
				while (result.next()) {
					Integer taskID		= result.getInt("taskID");
					Integer categoryID	= result.getInt("categoryID");
					String taskname = result.getString("taskname");
					String deadline = result.getString("deadline");
					Task task = new Task(taskID, categoryID, username, taskname, false, deadline);
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
					Boolean status 		= result.getBoolean("status");
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

	public static Integer getAvailableTaskID() {
		// get all task
		Task[] tasks = Task.getAllTask();
		Integer i = 1;
		while(true) {
			boolean available = true;
			
			// traverse to all exist task, see if i available
			for(Task task:tasks) {
				if(task.getID() == i) {
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
		String stmt = String.format("INSERT INTO `%s` (`taskID`, `categoryID`, `username`, `taskname`, `status`, `deadline`)" +
				"VALUES ('%s', '%s', '%s', '%s', '%s', '%s');", DBTable.TASK, mID, mCategoryID, mUsername, mTaskname, mStatus ? 1 : 0, mDeadline);
		
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
				"WHERE `%s`.`taskID` = '%s'", DBTable.TASK, mCategoryID, mUsername, mTaskname, mStatus ? 1 : 0, mDeadline, DBTable.TASK, mID);
				
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
	
	public Tag[] getTags() {
		DBQueryExecutor executor = new DBQueryExecutor();
		Tag[] retval = new Tag[0];

		try {
			ResultSet result = executor.executeQuery(String.format("SELECT * FROM `%s` WHERE `taskID` = '%s';", DBTable.TAG, this.getID()));
			if (result != null) {
				ArrayList<Tag> temp = new ArrayList<Tag>();
				while (result.next()) {
					Integer taskID	= result.getInt("taskID");
					String tagname	= result.getString("tagname");
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
	
	public String getTagsString() {
		Tag[] tags = getTags();
		StringBuilder sb = new StringBuilder();
		
		for(Tag tag : tags) {
			sb.append('#');
			sb.append(tag.getTagname());
			sb.append(", ");
		}
		return sb.toString();
	}
	
	public Category getCategory() {
		return Category.getCategoryFromCategoryID(mCategoryID);
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

	public Boolean getStatus() {
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
