package com.tmj.model;

public class User extends BaseModel {
	
	public User(String username, String password, String fullname, String birthplace, String birthdate,
			String email, String avatarPath) {
		mUsername = username;
		mPassword = password;
		mFullname = fullname;
		mBirthplace = birthplace;
		mBirthdate = birthdate;
		mEmail = email;
		mAvatarPath = avatarPath;
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

	private String mUsername;
	private String mPassword;
	private String mFullname;
	private String mBirthplace;
	private String mBirthdate;
	private String mEmail;
	private String mAvatarPath;
	
	public String getUsername() {
		return mUsername;
	}

	public String getPassword() {
		return mPassword;
	}

	public String getFullname() {
		return mFullname;
	}

	public String getBirthplace() {
		return mBirthplace;
	}

	public String getBirthdate() {
		return mBirthdate;
	}

	public String getEmail() {
		return mEmail;
	}

	public String getAvatarPath() {
		return mAvatarPath;
	}

	public void setUsername(String username) {
		this.mUsername = username;
	}

	public void setPassword(String password) {
		this.mPassword = password;
	}

	public void setFullname(String fullname) {
		this.mFullname = fullname;
	}

	public void setBirthplace(String birthplace) {
		this.mBirthplace = birthplace;
	}

	public void setBirthdate(String birthdate) {
		this.mBirthdate = birthdate;
	}

	public void setEmail(String email) {
		this.mEmail = email;
	}

	public void setAvatarPath(String avatarPath) {
		this.mAvatarPath = avatarPath;
	}
}
