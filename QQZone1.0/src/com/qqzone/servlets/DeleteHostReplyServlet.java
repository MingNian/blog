package com.qqzone.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qqzone.dao.HostReplyDAO;
import com.qqzone.dao.impl.HostReplyDAOImpl;
import com.qqzone.pojo.Topic;
import com.qqzone.util.StringUtil;

/**
 * Servlet implementation class DeleteHostReplyServlet
 */
public class DeleteHostReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteHostReplyServlet() {
        super();
    }
    
    private HostReplyDAO hostReplyDAO = HostReplyDAOImpl.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hostReplyIdStr = request.getParameter("hostReplyId");
		
		if(StringUtil.isNotEmpty(hostReplyIdStr)){
			int hostReplyId = Integer.parseInt(hostReplyIdStr);	
			hostReplyDAO.deleteHostReply(hostReplyId);
			
		}else{
			response.sendRedirect("login.jsp");
		}
		
		Topic t = (Topic)(request.getSession().getAttribute("topicItem"));
		int topicId =t.getId() ;
		response.sendRedirect("topicItem.action?topicId="+topicId);
		
		
	}

}
