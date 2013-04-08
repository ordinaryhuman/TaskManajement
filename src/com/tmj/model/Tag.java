package com.tmj.model;

public class Tag extends BaseModel {
	
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
