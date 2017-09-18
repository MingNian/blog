package com.qqzone.servlets;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qqzone.dao.ReplyDAO;
import com.qqzone.dao.TopicDAO;
import com.qqzone.dao.UserDAO;
import com.qqzone.dao.impl.ReplyDAOImpl;
import com.qqzone.dao.impl.TopicDAOImpl;
import com.qqzone.dao.impl.UserDAOImpl;
import com.qqzone.pojo.Topic;
import com.qqzone.pojo.User;
import com.qqzone.util.StringUtil;

public class TopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TopicDAO topicDAO = TopicDAOImpl.getInstance();
	private UserDAO userDAO = UserDAOImpl.getInstance();
	private ReplyDAO replyDAO = ReplyDAOImpl.getInstance();

	public TopicServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();

		int currPage = 1;
		int pageSize = 5;
		User dispalyUser = null;
		
		String displayUserIdStr = request.getParameter("displayUserId");
		
		if (StringUtil.isEmpty(displayUserIdStr)) {
			Object obj = session.getAttribute("user");
			if (obj == null) {
				response.sendRedirect("login.jsp");
			} else {
				User user = (User) obj;
				dispalyUser = user;
			}
		} else {
			int displayUserId = Integer.parseInt(displayUserIdStr);
			dispalyUser = userDAO.getUserById(displayUserId);
		}
		String currPageStr = request.getParameter("currPage");
		if (StringUtil.isNotEmpty(currPageStr)) {
			currPage = Integer.parseInt(currPageStr);
		}
		Set<Topic> topicSet = topicDAO.getTopicSet(dispalyUser, currPage,
				pageSize);
		int maxPage = topicDAO.getMaxPage(dispalyUser);
		maxPage = (maxPage + 4) / pageSize;
		
		for(Topic t:topicSet){
			t.setReplyCount(replyDAO.getReplyCountByTopicId(t.getId()));
		}
		
		session.setAttribute("maxPage", maxPage);
		session.setAttribute("topicSet", topicSet);
		session.setAttribute("currPage", currPage);
		session.setAttribute("pageSize", pageSize);
		session.setAttribute("displayUser", dispalyUser);
		response.sendRedirect("index.jsp");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
