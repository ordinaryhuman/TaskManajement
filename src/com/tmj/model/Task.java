package com.tmj.model;

public class Task extends BaseModel {
	
	public Task(Integer ID, Integer categoryID, String username, String taskname, Boolean status, String deadline) {
		mID = ID;
		mCategoryID = categoryID;
		mUsername = username;
		mTaskname = taskname;
		mStatus = status;
		mDeadline = deadline;
	}

	@Override
	public void addOnDB() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editOnDB() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteOnDB() {
		// TODO Auto-generated method stub
		
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
