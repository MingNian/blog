package com.qqzone.servlets;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qqzone.dao.HostReplyDAO;
import com.qqzone.dao.ReplyDAO;
import com.qqzone.dao.TopicDAO;
import com.qqzone.dao.UserDAO;
import com.qqzone.dao.impl.HostReplyDAOImpl;
import com.qqzone.dao.impl.ReplyDAOImpl;
import com.qqzone.dao.impl.TopicDAOImpl;
import com.qqzone.dao.impl.UserDAOImpl;
import com.qqzone.pojo.HostReply;
import com.qqzone.pojo.Reply;
import com.qqzone.pojo.Topic;
import com.qqzone.util.StringUtil;

public class TopicItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TopicItemServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	private TopicDAO topicDAO = TopicDAOImpl.getInstance();
	private UserDAO userDAO = UserDAOImpl.getInstance();
	private ReplyDAO replyDAO = ReplyDAOImpl.getInstance();
	private HostReplyDAO hostReplyDAO = HostReplyDAOImpl.getInstance();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		String topicIdStr = request.getParameter("topicId");
		if (StringUtil.isNotEmpty(topicIdStr)) {
			int topicId = Integer.parseInt(topicIdStr);
			Topic topic = topicDAO.getTopicById(topicId);
			Set<Reply> replySet = replyDAO.getReplySet(topicId);
			for (Reply reply : replySet) {
				reply.setTopic(topic);
				reply.setAuthor(userDAO.getUserById(reply.getAuthor().getId()));
				reply.setHostReply(hostReplyDAO.getHostReply(reply));

				HostReply hr = reply.getHostReply();
				if (hr != null) {
					reply.getHostReply().setReply(reply);
					reply.getHostReply().setAuthor(
							userDAO.getUserById(topic.getAuthor().getId()));
				}
			}

			session.setAttribute("replySet", replySet);
			session.setAttribute("topicItem", topic);
		} else {
			response.sendRedirect("login.jsp");
		}

		response.sendRedirect("topic.jsp");
	}
}
