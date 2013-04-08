package com.tmj.model;

public class Category extends BaseModel {
	
	public Category(Integer ID, String name, String creatorID) {
		mID = ID;
		mName = name;
		mCreatorID = creatorID;
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
	private String mName;
	private String mCreatorID;
	
	public Integer getID() {
		return mID;
	}

	public String getName() {
		return mName;
	}

	public String getCreatorID() {
		return mCreatorID;
	}

	public void setID(Integer ID) {
		this.mID = ID;
	}

	public void setName(String name) {
		this.mName = name;
	}

	public void setCreatorID(String creatorID) {
		this.mCreatorID = creatorID;
	}
}
