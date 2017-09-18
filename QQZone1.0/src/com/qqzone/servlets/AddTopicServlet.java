package com.qqzone.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qqzone.dao.TopicDAO;
import com.qqzone.dao.impl.TopicDAOImpl;
import com.qqzone.pojo.Topic;
import com.qqzone.pojo.User;

public class AddTopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddTopicServlet() {
		super();
	}

	TopicDAO topicDAO = TopicDAOImpl.getInstance();
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		
		String head = request.getParameter("head");
		String content= request.getParameter("content");
		Object userObj = session.getAttribute("user");
		if(userObj!=null){
			User user = (User)userObj;
			Topic topic = new Topic(head, content, user, new Date());
			topicDAO.addTopic(topic);
			response.sendRedirect("topic.action");
		}
		
	}

}
