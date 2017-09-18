package com.qqzone.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qqzone.dao.TopicDAO;
import com.qqzone.dao.impl.TopicDAOImpl;
import com.qqzone.util.StringUtil;

public class DeleteTopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteTopicServlet() {
		super();
	}
	
	private TopicDAO topicDAO = TopicDAOImpl.getInstance();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String topicIdStr = request.getParameter("topicId");
		if(StringUtil.isNotEmpty(topicIdStr)){
			int topicId = Integer.parseInt(topicIdStr);
			topicDAO.deleteTopic(topicId);
			
		}
		
		response.sendRedirect("topic.action");
	}

}
