package com.tmj.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tmj.helper.DBQueryExecutor;
import com.tmj.helper.DBTable;

public class User extends BaseModel {
	
	public User(String username, String password) {
		mUsername = username;
		mPassword = password;
	}
	
	public User(String username, String password, String fullname, String birthdate,
			String email, String avatarPath) {
		mUsername = username;
		mPassword = password;
		mFullname = fullname;
		mBirthdate = birthdate;
		mEmail = email;
		mAvatarPath = avatarPath;
	}
	
	/**
	 * Authenticate this object by checking at database 
	 * @return 1 if user not found, 2 if password mismatch, 0 if OK
	 */
	public Integer authenticate() {
		User user = User.getUserFromUsername(mUsername);
		if(user == null) {
			return 1;
		} else if(user.getPassword().equals(mPassword)) {
			mFullname = user.getFullname();
			mBirthdate = user.getBirthdate();
			mEmail = user.getEmail();
			mAvatarPath = user.getAvatarPath();
			return 0;
		}
		return 2;
	}
	
	public static User getUserFromEmail(String email) {
		DBQueryExecutor executor = new DBQueryExecutor();
		User retval = null;

		try {
			ResultSet result = executor.executeQuery(String.format("SELECT * FROM `%s` WHERE `email` = '%s';", DBTable.USER, email));
			if (result != null) {
				while (result.next()) {
					String username 	= result.getString("username");
					String password 	= result.getString("password");
					String fullname 	= result.getString("fullname");
					String birthdate 	= result.getString("birthdate");
					String avatarPath	= result.getString("avatar");
					retval = new User(username, password, fullname, birthdate, email, avatarPath);
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
	
	public static User getUserFromUsername(String username) {
		DBQueryExecutor executor = new DBQueryExecutor();
		User retval = null;

		try {
			ResultSet result = executor.executeQuery(String.format("SELECT * FROM `%s` WHERE `username` = '%s';", DBTable.USER, username));
			if (result != null) {
				while (result.next()) {
					String password 	= result.getString("password");
					String fullname 	= result.getString("fullname");
					String birthdate 	= result.getString("birthdate");
					String email 		= result.getString("email");
					String avatarPath	= result.getString("avatar");
					retval = new User(username, password, fullname, birthdate, email, avatarPath);
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
	
	public static User[] getAllUsers() {
		DBQueryExecutor executor = new DBQueryExecutor();
		User[] retval = new User[0];

		try {
			ResultSet result = executor.executeQuery(String.format("SELECT * FROM `%s`;", DBTable.USER));
			if (result != null) {
				ArrayList<User> temp = new ArrayList<User>();
				while (result.next()) {
					String username		= result.getString("username");
					String password 	= result.getString("password");
					String fullname 	= result.getString("fullname");
					String birthdate 	= result.getString("birthdate");
					String email 		= result.getString("email");
					String avatarPath	= result.getString("avatar");
					User user = new User(username, password, fullname, birthdate, email, avatarPath);
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
	
	public static User[] getUserFromQuery(String query) {
		User[] a = User.getUserFromQueryUsername(query);
		User[] b = User.getUserFromQueryEmail(query);
		User[] c = User.getUserFromQueryFullname(query);
		ArrayList<User> AL = new ArrayList<User>();
		
		for(User u : a)		
			AL.add(u);
		for(User u : b) {
			boolean x = true;
			for(User uAL: AL) {
				if(uAL.getUsername().equals(u.getUsername())) {
					x = false;
				}
			}
			if(x)
				AL.add(u);
		}
		for(User u : c) {
			boolean x = true;
			for(User uAL: AL) {
				if(uAL.getUsername().equals(u.getUsername())) {
					x = false;
				}
			}
			if(x)
				AL.add(u);
		}
		
		User[] retval = new User[AL.size()];
		retval = AL.toArray(retval);
		
		return retval;
	}
	
	public static User[] getUserFromQueryUsername(String query) {
		DBQueryExecutor executor = new DBQueryExecutor();
		User[] retval = new User[0];

		try {
			ResultSet result = executor.executeQuery(String.format("SELECT * FROM `%s` WHERE `username` LIKE '%s';", DBTable.USER, "%" + query + "%"));
			if (result != null) {
				ArrayList<User> temp = new ArrayList<User>();
				while (result.next()) {
					String username 	= result.getString("username");
					String password 	= result.getString("password");
					String fullname 	= result.getString("fullname");
					String birthdate 	= result.getString("birthdate");
					String email 		= result.getString("email");
					String avatarPath	= result.getString("avatar");
					User user = new User(username, password, fullname, birthdate, email, avatarPath);
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
	
	public static User[] getUserFromQueryEmail(String query) {
		DBQueryExecutor executor = new DBQueryExecutor();
		User[] retval = new User[0];

		try {
			ResultSet result = executor.executeQuery(String.format("SELECT * FROM `%s` WHERE `email` LIKE '%s';", DBTable.USER, "%" + query + "%"));
			if (result != null) {
				ArrayList<User> temp = new ArrayList<User>();
				while (result.next()) {
					String username 	= result.getString("username");
					String password 	= result.getString("password");
					String fullname 	= result.getString("fullname");
					String birthdate 	= result.getString("birthdate");
					String email 		= result.getString("email");
					String avatarPath	= result.getString("avatar");
					User user = new User(username, password, fullname, birthdate, email, avatarPath);
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
	
	public static User[] getUserFromQueryFullname(String query) {
		DBQueryExecutor executor = new DBQueryExecutor();
		User[] retval = new User[0];

		try {
			ResultSet result = executor.executeQuery(String.format("SELECT * FROM `%s` WHERE `fullname` LIKE '%s';", DBTable.USER, "%" + query + "%"));
			if (result != null) {
				ArrayList<User> temp = new ArrayList<User>();
				while (result.next()) {
					String username 	= result.getString("username");
					String password 	= result.getString("password");
					String fullname 	= result.getString("fullname");
					String birthdate 	= result.getString("birthdate");
					String email 		= result.getString("email");
					String avatarPath	= result.getString("avatar");
					User user = new User(username, password, fullname, birthdate, email, avatarPath);
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

	@Override
	public void addOnDB() {
		DBQueryExecutor executor = new DBQueryExecutor();
		String stmt = String.format("INSERT INTO `%s` (`username`, `password`, `fullname`, `birthdate`, `email`, `avatar`)" +
				"VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s');", DBTable.USER, mUsername, mPassword, mFullname, mBirthdate, mEmail, mAvatarPath);
		
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
		String stmt = String.format("UPDATE `%s` SET `password` = '%s', `fullname` = '%s', `birthdate` = '%s', `email` = '%s', `avatar` = '%s'" +
				"WHERE `%s`.`username` = '%s'", DBTable.USER, mPassword, mFullname, mBirthdate, mEmail, mAvatarPath, DBTable.USER, mUsername);
				
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
		String stmt = String.format("DELETE FROM `%s` WHERE `%s`.`username` = '%s'", DBTable.USER, DBTable.USER, mUsername);
				
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
