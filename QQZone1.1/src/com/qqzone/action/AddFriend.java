package com.qqzone.action;

import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.qqzone.dao.UserDAO;
import com.qqzone.dao.impl.UserDAOImpl;
import com.qqzone.pojo.User;

public class AddFriend {
	private UserDAO userDAO = UserDAOImpl.getInstance();

	private String nickName;

	public String execute() {
		User user = userDAO.addFriend(nickName);
		boolean flag = user == null;
		if (flag) {
			return "failed";
		} else {
			User currUser = (User) ActionContext.getContext().getSession()
					.get("user");
			userDAO.beFriend(currUser, user);
			Set<User> friends = userDAO.getFriendSet(currUser);
			ActionContext.getContext().getSession().put("friends", friends);
			ActionContext.getContext().getSession().put("user", currUser);
			return "success";
		}
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
