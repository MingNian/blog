package com.qqzone.action;

import java.util.Date;

import com.opensymphony.xwork2.ActionContext;
import com.qqzone.dao.ReplyDAO;
import com.qqzone.dao.impl.ReplyDAOImpl;
import com.qqzone.pojo.Reply;
import com.qqzone.pojo.Topic;
import com.qqzone.pojo.User;

public class ReplyAction{
	private ReplyDAO replyDAO = ReplyDAOImpl.getInstance();
	
	private String reply;
	private int topicId;
	
	public String execute(){
		
		Object object = ActionContext.getContext().getSession().get("user");
		
		if(object!=null ){
			Date date = new Date();
			Reply r = new Reply((User)object, reply, date, new Topic(topicId));
			replyDAO.addReply(r);
			return "topicItem";
		}else{
			return "failed";
		}
		
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

}
