package com.qqzone.action;

import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.qqzone.dao.ReplyDAO;
import com.qqzone.dao.TopicDAO;
import com.qqzone.dao.UserDAO;
import com.qqzone.dao.impl.ReplyDAOImpl;
import com.qqzone.dao.impl.TopicDAOImpl;
import com.qqzone.dao.impl.UserDAOImpl;
import com.qqzone.pojo.Topic;
import com.qqzone.pojo.User;

public class TopicAction {

	private TopicDAO topicDAO = TopicDAOImpl.getInstance();
	private UserDAO userDAO = UserDAOImpl.getInstance();
	private ReplyDAO replyDAO = ReplyDAOImpl.getInstance();

	private int displayUserId=0;
	private int currPage=0;

	public String execute() {
		if(currPage<=0){
			currPage = 1;
		}
		int pageSize = 5;
		User displayUser = null;
		User user = null;

		Object obj = ActionContext.getContext().getSession().get("user");
		if (obj == null) {
			return "failed";
		} else {
			user = (User) obj;
			displayUser = user;
		}
		if(displayUserId<=0){
			displayUserId= user.getId();
		}else{
			displayUser = userDAO.getUserById(displayUserId);
		}
		
		Set<Topic> topicSet = topicDAO.getTopicSet(displayUser, currPage, pageSize);
		int maxPage = topicDAO.getMaxPage(displayUser);
		maxPage = (maxPage + 4) / pageSize;

		for (Topic t : topicSet) {
			t.setReplyCount(replyDAO.getReplyCountByTopicId(t.getId()));
		}

		ActionContext.getContext().getSession().put("maxPage", maxPage);
		ActionContext.getContext().getSession().put("topicSet", topicSet);
		ActionContext.getContext().getSession().put("currPage", currPage);
		ActionContext.getContext().getSession().put("pageSize", pageSize);
		ActionContext.getContext().getSession().put("displayUser", displayUser);
		return "success";
	}

	public int getDisplayUserId() {
		return displayUserId;
	}

	public void setDisplayUserId(int displayUserId) {
		this.displayUserId = displayUserId;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}


}
