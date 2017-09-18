package com.qqzone.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qqzone.dao.ReplyDAO;
import com.qqzone.dao.impl.ReplyDAOImpl;
import com.qqzone.pojo.Topic;
import com.qqzone.util.StringUtil;

public class DeleteReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteReplyServlet() {
		super();
	}

	private ReplyDAO replyDAO = ReplyDAOImpl.getInstance();
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String replyIdStr = request.getParameter("replyId");
		
		if(StringUtil.isNotEmpty(replyIdStr)){
			int replyId = Integer.parseInt(replyIdStr);
			replyDAO.deleteReply(replyId);
		}else{
			response.sendRedirect("login.jsp");
		}
		
		Topic t = (Topic)(request.getSession().getAttribute("topicItem"));
		int topicId =t.getId() ;
		response.sendRedirect("topicItem.action?topicId="+topicId);
	}

}
