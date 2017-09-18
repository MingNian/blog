package com.qqzone.action;

import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.qqzone.dao.TopicDAO;
import com.qqzone.dao.impl.TopicDAOImpl;
import com.qqzone.pojo.Topic;
import com.qqzone.pojo.User;

public class DeleteTopic {
	private TopicDAO topicDAO = TopicDAOImpl.getInstance();
	private int topicId;

	public String execute() {
		topicDAO.deleteTopic(topicId);
		ActionContext.getContext().put("topicId", topicId);
		User user = (User)ActionContext.getContext().getSession().get("user");
		Set<Topic> topicSet = topicDAO.getTopicSet(user, 1, 5);
		ActionContext.getContext().getSession().put("topicSet", topicSet);
		return "success";
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

}
