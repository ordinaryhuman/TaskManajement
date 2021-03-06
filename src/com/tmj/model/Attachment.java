package com.tmj.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tmj.helper.DBQueryExecutor;
import com.tmj.helper.DBTable;

public class Attachment extends BaseModel {
	
	public Attachment(Integer ID, Integer taskID, String filename, String type) {
		mID = ID;
		mTaskID = taskID;
		mFilename = filename;
		mType = type;
	}
	
	public static Attachment[] getAllAttachment() {
		DBQueryExecutor executor = new DBQueryExecutor();
		Attachment[] retval = new Attachment[0];

		try {
			ResultSet result = executor.executeQuery(String.format("SELECT * FROM `%s`;", DBTable.ATTACHMENT));
			if (result != null) {
				ArrayList<Attachment> temp = new ArrayList<Attachment>();
				while (result.next()) {
					Integer attachmentID= result.getInt("attachmentID");
					Integer taskID		= result.getInt("taskID");
					String filename 	= result.getString("filename");
					String type 		= result.getString("type");
					Attachment attachment = new Attachment(attachmentID, taskID, filename, type);
					temp.add(attachment);
				}
				
				retval = new Attachment[temp.size()];
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
	
	public static Attachment getAttachmentFromAttachmentID(Integer attachmentID) {
		DBQueryExecutor executor = new DBQueryExecutor();
		Attachment retval = null;

		try {
			ResultSet result = executor.executeQuery(String.format("SELECT * FROM `%s` WHERE `attachmentID` = '%d';", DBTable.ATTACHMENT, attachmentID));
			if (result != null) {
				while (result.next()) {
					Integer taskID		= result.getInt("taskID");
					String filename 	= result.getString("filename");
					String type 		= result.getString("type");
					retval = new Attachment(attachmentID, taskID, filename, type);
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
	
	public static Attachment[] getAttachmentFromTaskID(Integer taskID) {
		DBQueryExecutor executor = new DBQueryExecutor();
		Attachment[] retval = new Attachment[0];

		try {
			ResultSet result = executor.executeQuery(String.format("SELECT * FROM `%s` WHERE `taskID` = '%d';", DBTable.ATTACHMENT, taskID));
			if (result != null) {
				ArrayList<Attachment> temp = new ArrayList<Attachment>();
				while (result.next()) {
					Integer attachmentID= result.getInt("attachmentID");
					String filename 	= result.getString("filename");
					String type 		= result.getString("type");
					Attachment attachment = new Attachment(attachmentID, taskID, filename, type);
					temp.add(attachment);
				}
				
				retval = new Attachment[temp.size()];
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
	
	public static Integer getAvailableAttachmentID() {
		// get all attachment
		Attachment[] attachments = Attachment.getAllAttachment();
		Integer i = 1;
		while(true) {
			boolean available = true;
			
			// traverse to all exist attachment, see if i available
			for(Attachment attachment:attachments) {
				if(attachment.getID() == i) {
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
		String stmt = String.format("INSERT INTO `%s` (`attachmentID`, `taskID`, `filename`, `type`)" +
				"VALUES ('%s', '%s', '%s', '%s');", DBTable.ATTACHMENT, mID, mTaskID, mFilename, mType);
		
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
		String stmt = String.format("UPDATE `%s` SET `taskID` = '%s', `filename` = '%s', `type` = '%s'" +
				"WHERE `%s`.`attachmentID` = '%s'", DBTable.ATTACHMENT, mTaskID, mFilename, mType, DBTable.ATTACHMENT, mID);
				
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
		String stmt = String.format("DELETE FROM `%s` WHERE `%s`.`attachmentID` = '%s'", DBTable.ATTACHMENT, DBTable.ATTACHMENT, mID);
				
		try {
			executor.executeQuery(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			executor.closeQuery();
			executor.closeConnection();
		}
	}
	
	public String getFilePath() {
		return "upload/attachments/".concat(getFilename());
	}
	
	private Integer mID;
	private Integer mTaskID;
	private String mFilename;
	private String mType;
	
	public Integer getID() {
		return mID;
	}
	public Integer getTaskID() {
		return mTaskID;
	}
	public String getFilename() {
		return mFilename;
	}
	public String getType() {
		return mType;
	}
	
	public void setID(Integer ID) {
		this.mID = ID;
	}
	public void setTaskID(Integer taskID) {
		this.mTaskID = taskID;
	}
	public void setFilename(String filename) {
		this.mFilename = filename;
	}
	public void setType(String type) {
		this.mType = type;
	}
}
