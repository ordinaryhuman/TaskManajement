package com.tmj.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmj.helper.DBQueryExecutor;

public class TestServlet extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6907668313636219837L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		try {
			DBQueryExecutor executor = new DBQueryExecutor();
			ResultSet result = executor.executeQuery("SELECT * FROM `user` WHERE 1");
			request.setAttribute("result", result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		mRD = request.getRequestDispatcher("test.jsp");
		mRD.forward(request, response);
	}

}
