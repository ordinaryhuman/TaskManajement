package com.tmj.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmj.model.Attachment;
import com.tmj.model.Category;
import com.tmj.model.Comment;
import com.tmj.model.Tag;
import com.tmj.model.Task;
import com.tmj.model.User;

/**
 * Servlet implementation class TaskController
 */
@WebServlet("/task")
public class TaskController extends BaseController {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see BaseController#BaseController()
     */
    public TaskController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		if(checkLoggedIn(request, response))
			return;
		
		if(mAction == null) {
			Integer taskID = new Integer(request.getParameter("taskid"));
			Task task = Task.getTaskFromTaskID(taskID);
			Attachment[] attachments = task.getAttachments();
			User[] assignees = task.getAssignees();
			Tag[] tags = task.getTags();
			Comment[] comments = task.getComments();
			
			request.setAttribute("task", task);
			request.setAttribute("tags", tags);
			request.setAttribute("comments", comments);
			request.setAttribute("attachments", attachments);
			request.setAttribute("assignees", assignees);
			
			mRD = request.getRequestDispatcher("task/view.jsp");
			mRD.forward(request, response);
		} else if(mAction.equals("add")) {
			Integer categoryID = new Integer(request.getParameter("categoryID"));
			Category category = Category.getCategoryFromCategoryID(categoryID);
			
			request.setAttribute("category", category);
			
			mRD = request.getRequestDispatcher("task/add.jsp");
			mRD.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		if(checkLoggedIn(request, response))
			return;
		
		Integer taskID = Task.getAvailableTaskID();
		
		response.sendRedirect(String.format("task?taskID=%d", taskID));
	}

}
