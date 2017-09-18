package com.qqzone.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qqzone.dao.ReplyDAO;
import com.qqzone.dao.impl.ReplyDAOImpl;
import com.qqzone.pojo.Reply;
import com.qqzone.pojo.Topic;
import com.qqzone.pojo.User;
import com.qqzone.util.StringUtil;

public class ReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReplyServlet() {
		super();
	}

	ReplyDAO replyDAO = ReplyDAOImpl.getInstance();
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		
		String replyContent = request.getParameter("reply");
		String topicIdStr = request.getParameter("topicId");
		Object object = session.getAttribute("user");
		
		if(StringUtil.isNotEmpty(topicIdStr) && object!=null ){
			int topicId = Integer.parseInt(topicIdStr);
			Date date = new Date();
			Reply reply = new Reply((User)object, replyContent, date, new Topic(topicId));
			replyDAO.addReply(reply);
			
			
			response.sendRedirect("topicItem.action?topicId="+topicIdStr);
			
		}else{
			response.sendRedirect("login.jsp");
		}
		
	}

}
