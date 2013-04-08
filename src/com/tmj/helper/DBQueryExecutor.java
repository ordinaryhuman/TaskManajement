package com.tmj.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.jdbc.Driver;;

public class DBQueryExecutor {

	public DBQueryExecutor() {
		this(DB_ADDRESS, DB_NAME, DB_USERNAME, DB_PASSWORD);
	}

	/**
	 * make a DB wrapper to the given address and DB name. Require username and password
	 * @param address
	 * @param dbName
	 * @param dbUsername
	 * @param dbPassword
	 */
	public DBQueryExecutor(String address, String dbName, String dbUsername, String dbPassword) {
		mDBAddress 	= address;
		mDBName		= dbName;
		mDBUsername	= dbUsername;
		mDBPassword	= dbPassword;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			//System.out.println(mDBAddress + " " + mDBUsername + " " + mDBPassword);
			connectToDatabase(mDBAddress, mDBName, mDBUsername, mDBPassword);
			//System.out.println("success");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void connectToDatabase(String address, String dbName, String dbUsername, String dbPassword) throws SQLException {
		mConnection = DriverManager.getConnection(String.format(
			"jdbc:mysql://%s/%s?user=%s&password=%s",
			address, dbName, dbUsername, dbPassword));
	}

	/**
	 * Execute a given SQL query
	 * @param query
	 * @return result of the query from predefined database
	 * @throws SQLException
	 */
	public ResultSet executeQuery(String query) throws SQLException {
		// if connection is null/closed, try to make connection
		if (mConnection == null || mConnection.isClosed()) {
			connectToDatabase(mDBAddress, mDBName, mDBUsername, mDBPassword);
		}
		mStatement = mConnection.createStatement();
		if (mStatement.execute(query)) {
			return mStatement.getResultSet();
		} else {
			// query without result (or invalid query), return null
			return null;
		}
	}

	public PreparedStatement makePreparedStatement(String query) throws SQLException{
		if (mConnection == null || mConnection.isClosed()) {
			connectToDatabase(mDBAddress, mDBName, mDBUsername, mDBPassword);
		}
		
		return mConnection.prepareStatement(query);
	}

	/**
	 * close the query statement.
	 * We must call this function after calling executeQuery to close statement
	 * @throws SQLException
	 */
	public void closeQuery() {
		if (mStatement != null) {
			try {
				mStatement.close();
			} catch (SQLException sEx) {
				// do nothing
				sEx.printStackTrace();
			}
			mStatement = null;
		}
	}

	public void closeConnection() {
		if (mConnection != null) {
			try {
				mConnection.close();
			} catch (SQLException sEx) {
				// do nothing
				sEx.printStackTrace();
			}
			mConnection = null;
			//System.gc();
		}
	}

	public boolean isConnected() throws SQLException {
		return mConnection != null && !mConnection.isClosed();
	}

	/**
	 * singleton of DBQueryExecutor which uses default DB configuration
	 * it makes wrapper for local DB connection
	 * @return
	 */
	private static DBQueryExecutor instance() {
		if (sInstance == null) {
			sInstance = new DBQueryExecutor();
		}
		return sInstance;
	}

	public static String makeInsertPlaceHolder(int placeHolderCount) {
        final StringBuilder builder = new StringBuilder("(");
        for ( int i = 0; i < placeHolderCount; i++ ) {
            if ( i != 0 ) {
                builder.append(",");
            }
            builder.append("?");
        }
        return builder.append(")").toString();
    }

	private Statement mStatement;
	private Connection mConnection;

	protected String mDBAddress;
	protected String mDBName;
	protected String mDBUsername;
	protected String mDBPassword;

	/** singleton */
	private static DBQueryExecutor sInstance;
	private final static String DB_ADDRESS	= "localhost";
	public final static String DB_NAME		= "task_manajement";
	private final static String DB_USERNAME = "root";
	private final static String DB_PASSWORD = "";
}
