package com.qqzone.action;

import com.opensymphony.xwork2.ActionContext;
import com.qqzone.dao.ReplyDAO;
import com.qqzone.dao.impl.ReplyDAOImpl;
import com.qqzone.pojo.Topic;

public class DeleteReply {
	private ReplyDAO replyDAO = ReplyDAOImpl.getInstance();

	private int replyId;
	private int topicId;
	
	public String execute() {

		replyDAO.deleteReply(replyId);

		Topic t = (Topic) (ActionContext.getContext().getSession()
				.get("topicItem"));
		topicId = t.getId();
		ActionContext.getContext().put("topicId", topicId);
		return "topicItem";
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

}
