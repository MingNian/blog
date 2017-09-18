package com.qqzone.action;

import java.util.Date;

import com.opensymphony.xwork2.ActionContext;
import com.qqzone.dao.TopicDAO;
import com.qqzone.dao.impl.TopicDAOImpl;
import com.qqzone.pojo.Topic;
import com.qqzone.pojo.User;

public class AddTopic {

	TopicDAO topicDAO = TopicDAOImpl.getInstance();

	private String head;
	private String content;

	public String execute() {

		Object userObj = ActionContext.getContext().getSession().get("user");
		if (userObj != null) {
			User user = (User) userObj;
			Topic topic = new Topic(head, content, user, new Date());
			topicDAO.addTopic(topic);
			return "success";
		}
		return "failed";
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
