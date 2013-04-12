package com.tmj.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tmj.helper.DBQueryExecutor;
import com.tmj.helper.DBTable;

public class Category extends BaseModel {
	
	public Category(Integer ID, String name, String creatorID) {
		mID = ID;
		mName = name;
		mCreatorID = creatorID;
	}
	
	public static Category[] getAllCategory() {
		DBQueryExecutor executor = new DBQueryExecutor();
		Category[] retval = new Category[0];

		try {
			ResultSet result = executor.executeQuery(String.format("SELECT * FROM `%s`;", DBTable.CATEGORY));
			if (result != null) {
				ArrayList<Category> temp = new ArrayList<Category>();
				while (result.next()) {
					Integer categoryID	= result.getInt("categoryID");
					String categoryname	= result.getString("categoryname");
					String creatorID 	= result.getString("creatorID");
					Category category = new Category(categoryID, categoryname, creatorID);
					temp.add(category);
				}
				
				retval = new Category[temp.size()];
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
	
	public static Category getCategoryFromCategoryID(Integer categoryID) {
		DBQueryExecutor executor = new DBQueryExecutor();
		Category retval = null;

		try {
			ResultSet result = executor.executeQuery(String.format("SELECT * FROM `%s` WHERE `categoryID` = '%d';", DBTable.CATEGORY, categoryID));
			if (result != null) {
				while (result.next()) {
					String categoryname	= result.getString("categoryname");
					String creatorID 	= result.getString("creatorID");
					retval = new Category(categoryID, categoryname, creatorID);
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
	
	public static Category[] getCategoryFromUsername(String username) {
		DBQueryExecutor executor = new DBQueryExecutor();
		Category[] retval = new Category[0];

		try {
			ResultSet result = executor.executeQuery(String.format("SELECT * FROM `%s` WHERE `creatorID` = '%s';", DBTable.CATEGORY, username));
			if (result != null) {
				ArrayList<Category> temp = new ArrayList<Category>();
				while (result.next()) {
					Integer categoryID	= result.getInt("categoryID");
					String categoryname	= result.getString("categoryname");
					Category category = new Category(categoryID, categoryname, username);
					temp.add(category);
				}
				
				retval = new Category[temp.size()];
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
	
	public static Category[] getCategoryFromQuery(String query) {
		DBQueryExecutor executor = new DBQueryExecutor();
		Category[] retval = new Category[0];

		try {
			ResultSet result = executor.executeQuery(String.format("SELECT * FROM `%s` WHERE `categoryname` LIKE '%s';", DBTable.CATEGORY, "%" + query + "%"));
			if (result != null) {
				ArrayList<Category> temp = new ArrayList<Category>();
				while (result.next()) {
					Integer categoryID 	= result.getInt("categoryID");
					String name 		= result.getString("categoryname");
					String creatorID 	= result.getString("creatorID");
					Category category = new Category(categoryID, name, creatorID);
					temp.add(category);
				}
				
				retval = new Category[temp.size()];
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
	
	public static Integer getAvailableCategoryID() {
		// get all category
		Category[] categories = Category.getAllCategory();
		Integer i = 1;
		while(true) {
			boolean available = true;
			
			// traverse to all exist category, see if i available
			for(Category category:categories) {
				if(category.getID() == i) {
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
		String stmt = String.format("INSERT INTO `%s` (`categoryID`, `categoryname`, `creatorID`)" +
				"VALUES ('%d', '%s', '%s');", DBTable.CATEGORY, mID, mName, mCreatorID);
		
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
		String stmt = String.format("UPDATE `%s` SET `categoryname` = '%s', `creatorID` = '%s'" +
				"WHERE `%s`.`categoryID` = '%d'", DBTable.CATEGORY, mID, mName, mCreatorID, DBTable.CATEGORY, mID);
				
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
		String stmt = String.format("DELETE FROM `%s` WHERE `%s`.`categoryID` = '%d'", DBTable.CATEGORY, DBTable.CATEGORY, mID);
				
		try {
			executor.executeQuery(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			executor.closeQuery();
			executor.closeConnection();
		}
	}
	
	/**
	 * get member of this category
	 * @return
	 */
	public void setMembers(String usernameMembers) {
		DBQueryExecutor executor = new DBQueryExecutor();
		String[] usernames = usernameMembers.split(";");

		try {
			executor.executeQuery(String.format("DELETE FROM `%s` WHERE `%s`.`categoryID` = '%d'", DBTable.CATEGORY_USER, DBTable.CATEGORY_USER, mID));
			for(String username : usernames) {
				executor.executeQuery(String.format("INSERT INTO `%s` (`categoryID`, `username`) VALUES ('%d', '%s')",
						DBTable.CATEGORY_USER, mID, username));
			}
		} catch (SQLException sEx) {
			sEx.printStackTrace();
		} finally {
			executor.closeQuery();
			executor.closeConnection();
		}
	}
	
	public void addMember(String username) {
		DBQueryExecutor executor = new DBQueryExecutor();
		
		try {
			executor.executeQuery(String.format("INSERT IGNORE INTO `%s` (`categoryID`, `username`) VALUES ('%d', '%s')",
					DBTable.CATEGORY_USER, mID, username));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			executor.closeQuery();
			executor.closeConnection();
		}
	}

	/**
	 * get member of this category
	 * @return
	 */
	public User[] getMembers() {
		DBQueryExecutor executor = new DBQueryExecutor();
		User[] retval = new User[0];

		try {
			ResultSet result = executor.executeQuery(String.format("SELECT * FROM `%s` WHERE `username` IN (SELECT `username` FROM `%s` WHERE `categoryID` = '%s');", DBTable.USER, DBTable.CATEGORY_USER, this.getID()));
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
	
	public Boolean isMember(String username) {
		User[] users = this.getMembers();
		for(User user : users) {
			if(user.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	public User getCreator() {
		return User.getUserFromUsername(mCreatorID);
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
