package com.qqzone.action;

import com.opensymphony.xwork2.ActionContext;
import com.qqzone.dao.HostReplyDAO;
import com.qqzone.dao.impl.HostReplyDAOImpl;
import com.qqzone.pojo.Topic;

public class DeleteHostReply{

	private HostReplyDAO hostReplyDAO = HostReplyDAOImpl.getInstance();
	
	private int hostReplyId;
	private int topicId;

	public String  execute(){
		hostReplyDAO.deleteHostReply(hostReplyId);
		Topic t = (Topic)(ActionContext.getContext().getSession().get("topicItem"));
		topicId =t.getId();
		//response.sendRedirect("topicItem.action?topicId="+topicId);
		ActionContext.getContext().put("topicId", topicId);
		return "topicItem";
	}

	public int getHostReplyId() {
		return hostReplyId;
	}

	public void setHostReplyId(int hostReplyId) {
		this.hostReplyId = hostReplyId;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

}
