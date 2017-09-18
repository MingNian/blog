package com.qqzone.action;

import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
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

public class TopicItem {

	private TopicDAO topicDAO = TopicDAOImpl.getInstance();
	private UserDAO userDAO = UserDAOImpl.getInstance();
	private ReplyDAO replyDAO = ReplyDAOImpl.getInstance();
	private HostReplyDAO hostReplyDAO = HostReplyDAOImpl.getInstance();

	private int topicId;

	public String execute() {

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

		ActionContext.getContext().getSession().put("replySet", replySet);
		ActionContext.getContext().getSession().put("topicItem", topic);

		return "topic";
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	
	
}
