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
	
	public static Task[] getTasksFromUsernameAssignee(String username) {
		DBQueryExecutor executor = new DBQueryExecutor();
		Task[] retval = new Task[0];

		try {
			ResultSet result = executor.executeQuery(String.format("SELECT * FROM `%s` WHERE `userID` = '%s';", DBTable.TASK_USER, username));
			if (result != null) {
				ArrayList<Task> temp = new ArrayList<Task>();
				while (result.next()) {
					Integer taskID		= result.getInt("taskID");
					Task task = Task.getTaskFromTaskID(taskID);
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
		Task[] tasks = Task.getTasksFromUsernameAssignee(username);
		ArrayList<Task> ALT = new ArrayList<Task>();
		for	(Task task : tasks) {
			if(task.getStatus()) {
				ALT.add(task);
			}
		}
		
		tasks = new Task[ALT.size()];
		tasks = ALT.toArray(tasks);
		
		return tasks;
	}
	
	public static Task[] getTasksNotDoneFromUsername(String username) {
		Task[] tasks = Task.getTasksFromUsernameAssignee(username);
		ArrayList<Task> ALT = new ArrayList<Task>();
		for	(Task task : tasks) {
			if(!task.getStatus()) {
				ALT.add(task);
			}
		}
		
		tasks = new Task[ALT.size()];
		tasks = ALT.toArray(tasks);
		
		return tasks;
	}
	
	public static Task[] getTaskFromQueryComplete(String query) {
		Task[] a = Task.getTaskFromQuery(query);
		Task[] b = Tag.getAllTaskFromTagname(query);
		ArrayList<Task> AL = new ArrayList<Task>();
		
		for(Task u : a)		
			AL.add(u);
		for(Task u : b) {
			boolean x = true;
			for(Task uAL: AL) {
				if(uAL.getUsername().equals(u.getUsername())) {
					x = false;
				}
			}
			if(x)
				AL.add(u);
		}
		
		Task[] retval = new Task[AL.size()];
		retval = AL.toArray(retval);
		
		return retval;
	}
	
	public static Task[] getTaskFromQuery(String query) {
		Task[] a = Task.getTaskFromQueryComment(query);
		Task[] b = Task.getTaskFromQueryTaskname(query);
		ArrayList<Task> AL = new ArrayList<Task>();
		
		for(Task u : a)		
			AL.add(u);
		for(Task u : b) {
			boolean x = true;
			for(Task uAL: AL) {
				if(uAL.getUsername().equals(u.getUsername())) {
					x = false;
				}
			}
			if(x)
				AL.add(u);
		}
		
		Task[] retval = new Task[AL.size()];
		retval = AL.toArray(retval);
		
		return retval;
	}
	
	public static Task[] getTaskFromQueryTaskname(String query) {
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
	
	public static Task[] getTaskFromQueryComment(String query) {
		DBQueryExecutor executor = new DBQueryExecutor();
		Task[] retval = new Task[0];

		try {
			ResultSet result = executor.executeQuery(String.format("SELECT * FROM `%s` WHERE `taskID` IN (SELECT `taskID` FROM `%s` WHERE `content` LIKE `%s`);", DBTable.TASK, DBTable.COMMENT, "%" + query + "%"));
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
	
	public Attachment[] getAttachments() {
		return Attachment.getAttachmentFromTaskID(mID);
	}
	
	public Comment[] getComments() {
		return Comment.getCommentFromTaskID(mID);
	}
	
	public User[] getAssignees() {
		DBQueryExecutor executor = new DBQueryExecutor();
		User[] retval = new User[0];

		try {
			ResultSet result = executor.executeQuery(String.format("SELECT * FROM `%s` WHERE `taskID` = '%s';", DBTable.TASK_USER, mID));
			if (result != null) {
				ArrayList<User> temp = new ArrayList<User>();
				while (result.next()) {
					User user = User.getUserFromUsername(result.getString("userID"));
					temp.add(user);
				}
				
				retval = new User[temp.size()];
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
	
	public void addAssignee(String username) {
		DBQueryExecutor executor = new DBQueryExecutor();
		String stmt = String.format("INSERT IGNORE INTO `%s` (`taskID`, `userID`) VALUES ('%d', '%s')", DBTable.TASK_USER, mID, username);
		
		try {
			executor.executeQuery(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			executor.closeQuery();
			executor.closeConnection();
		}
	}
	
	public void deleteAssignee(String username) {
		DBQueryExecutor executor = new DBQueryExecutor();
		String stmt = String.format("DELETE FROM `%s` WHERE `taskID` = '%d' AND `userID` = '%s'", DBTable.TASK_USER, mID, username);
				
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

	public void setDeadline(String deadline) {
		this.mDeadline = deadline;
	}
}
