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
	
	public static Category getCategoryFromCategoryID(String categoryID) {
		DBQueryExecutor executor = new DBQueryExecutor();
		Category retval = null;

		try {
			ResultSet result = executor.executeQuery(String.format("SELECT * FROM `%s` WHERE `categoryID` = '%s';", DBTable.CATEGORY, categoryID));
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

	@Override
	public void addOnDB() {
		DBQueryExecutor executor = new DBQueryExecutor();
		String stmt = String.format("INSERT INTO `%s` (`categoryID`, `categoryname`, `creatorID`)" +
				"VALUES ('%s', '%s', '%s');", DBTable.CATEGORY, mID, mName, mCreatorID);
		
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
				"WHERE `%s`.`categoryID` = '%s'", DBTable.CATEGORY, mID, mName, mCreatorID, DBTable.CATEGORY, mID);
				
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
		String stmt = String.format("DELETE FROM `%s` WHERE `%s`.`categoryID` = '%s'", DBTable.CATEGORY, DBTable.CATEGORY, mID);
				
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
	public User[] getMembers() {
		// TODO : use table category_user
		return null;
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
