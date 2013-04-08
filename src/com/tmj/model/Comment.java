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
