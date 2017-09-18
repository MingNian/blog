package com.qqzone.servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qqzone.dao.UserDAO;
import com.qqzone.dao.UserDetailDAO;
import com.qqzone.dao.impl.UserDAOImpl;
import com.qqzone.dao.impl.UserDetailDAOImpl;
import com.qqzone.pojo.User;
import com.qqzone.pojo.UserDetail;
import com.qqzone.util.StringUtil;

public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAOImpl = UserDAOImpl.getInstance();
	private UserDetailDAO userDetailDAOImpl = UserDetailDAOImpl.getInstance();

	public RegistServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String pwd = request.getParameter("pwd");
		String nickName = request.getParameter("nickName");
		String loginId = request.getParameter("loginId");

		if (StringUtil.isNotEmpty(loginId) && StringUtil.isNotEmpty(nickName)
				&& StringUtil.isNotEmpty(pwd)) {
			User user = new User(loginId, pwd, nickName, "imgs/default.jpg");
			int did = userDAOImpl.addUser(user);
			UserDetail userDetail = new UserDetail(did,"帅的被人砍", new Date(
					System.currentTimeMillis()), "水瓶座", "AB", "男");
			userDetailDAOImpl.addUserDetail(userDetail);
			response.sendRedirect("login.jsp");
		}else{
			response.sendRedirect("regist.jsp");
		}

	}

}
