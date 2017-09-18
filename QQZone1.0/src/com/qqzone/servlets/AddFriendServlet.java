package com.qqzone.servlets;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qqzone.dao.UserDAO;
import com.qqzone.dao.impl.UserDAOImpl;
import com.qqzone.pojo.User;
import com.qqzone.util.StringUtil;

public class AddFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO = UserDAOImpl.getInstance();
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String nickName = request.getParameter("nickName");
		if(StringUtil.isNotEmpty(nickName)){
			User user = userDAO.addFriend(nickName);
			
			boolean flag = user==null;
			if(flag){
				response.sendRedirect("addFriendFailed.jsp");
			}else{
				User currUser = (User)request.getSession().getAttribute("user");
				userDAO.beFriend(currUser, user);
				Set<User> friends = userDAO.getFriendSet(currUser);
				request.getSession().setAttribute("friends", friends);
				request.getSession().setAttribute("user", currUser);
				response.sendRedirect("index.jsp");
			}
		}else{
			response.sendRedirect("addFriendFailed.jsp");
		}
		
	}

}
