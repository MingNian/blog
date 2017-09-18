package com.qqzone.servlets;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qqzone.dao.UserDAO;
import com.qqzone.dao.UserDetailDAO;
import com.qqzone.dao.impl.UserDAOImpl;
import com.qqzone.dao.impl.UserDetailDAOImpl;
import com.qqzone.pojo.User;
import com.qqzone.pojo.UserDetail;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}
	
	private UserDetailDAO userDetailDAO = UserDetailDAOImpl.getInstance();
	private UserDAO userDAO = UserDAOImpl.getInstance();
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		
		String loginId = request.getParameter("loginId");
		String pwd = request.getParameter("pwd");
		
		
		User user = userDAO.login(loginId, pwd);
		
		if(user==null){
			response.sendRedirect("login.jsp");
		}else{
			UserDetail detail = userDetailDAO.getUserDetail(user);
			Set<User> friends = userDAO.getFriendSet(user);
			session.setAttribute("friends", friends);
			session.setAttribute("user", user);
			session.setAttribute("detail", detail);
			response.sendRedirect("index.jsp");
		}
		
	}

}
