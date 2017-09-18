package com.qqzone.action;

import java.util.Date;

import com.opensymphony.xwork2.ActionContext;
import com.qqzone.dao.HostReplyDAO;
import com.qqzone.dao.impl.HostReplyDAOImpl;
import com.qqzone.pojo.HostReply;
import com.qqzone.pojo.Reply;
import com.qqzone.pojo.User;

public class HostReplyAction{
	private HostReplyDAO hostReplyDAO = HostReplyDAOImpl.getInstance();
	
	private int replyId;
	private int topicId;
	private String hostReplyText;
	
	public String execute(){
		
		Object userObj = ActionContext.getContext().getSession().get("user");
		if(userObj!=null){
			HostReply hr = new HostReply((User)userObj, hostReplyText, new Date(), new Reply(replyId));
			hostReplyDAO.addHostReply(hr);
			
			ActionContext.getContext().put("topicId",topicId);
			//response.sendRedirect("topicItem.action?topicId="+topicIdStr);
			return "topicItem";
			
		}else{
			return "failed";
		}
		
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

	public String getHostReplyText() {
		return hostReplyText;
	}

	public void setHostReplyText(String hostReplyText) {
		this.hostReplyText = hostReplyText;
	}

}
