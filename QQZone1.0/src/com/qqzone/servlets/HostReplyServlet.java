package com.qqzone.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qqzone.dao.HostReplyDAO;
import com.qqzone.dao.impl.HostReplyDAOImpl;
import com.qqzone.pojo.HostReply;
import com.qqzone.pojo.Reply;
import com.qqzone.pojo.User;
import com.qqzone.util.StringUtil;

public class HostReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HostReplyDAO hostReplyDAO = HostReplyDAOImpl.getInstance();
	
	public HostReplyServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		
		String replyIdStr = request.getParameter("replyId");
		String topicIdStr = request.getParameter("topicIdStr");
		int replyId = Integer.parseInt(replyIdStr);
		String content = request.getParameter("hostReplyText");
		Object userObj = session.getAttribute("user");
		if(StringUtil.isNotEmpty(replyIdStr) && userObj!=null){
			HostReply hr = new HostReply((User)userObj, content, new Date(), new Reply(replyId));
			hostReplyDAO.addHostReply(hr);
			
			response.sendRedirect("topicItem.action?topicId="+topicIdStr);
			
		}else{
			response.sendRedirect("login.jsp");
		}
		
	}

}
