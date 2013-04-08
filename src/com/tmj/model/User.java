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
		DBQueryExecutor executor = new DBQueryExecutor();
		String stmt = String.format("INSERT INTO `%s` (`username`, `password`, `fullname`, `birthplace`, `birthdate`, `email`, `avatar`)" +
				"VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s');", DBTable.ATTACHMENT, mUsername, mPassword, mFullname, mBirthplace, mBirthdate, mEmail, mAvatarPath);
		
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
		String stmt = String.format("UPDATE `%s` SET `password` = '%s', `fullname` = '%s', `birthplace` = '%s', `birthdate` = '%s', `email` = '%s', `avatar` = '%s'" +
				"WHERE `%s`.`username` = '%s'", DBTable.ATTACHMENT, mPassword, mFullname, mBirthplace, mBirthdate, mEmail, mAvatarPath, DBTable.ATTACHMENT, mID);
				
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
		String stmt = String.format("DELETE FROM `%s` WHERE `%s`.`username` = '%s'", DBTable.ATTACHMENT, DBTable.ATTACHMENT, mID);
				
		try {
			executor.executeQuery(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			executor.closeQuery();
			executor.closeConnection();
		}
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
