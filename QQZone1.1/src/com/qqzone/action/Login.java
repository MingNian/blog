package com.qqzone.action;

import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.qqzone.dao.UserDAO;
import com.qqzone.dao.UserDetailDAO;
import com.qqzone.dao.impl.UserDAOImpl;
import com.qqzone.dao.impl.UserDetailDAOImpl;
import com.qqzone.pojo.User;
import com.qqzone.pojo.UserDetail;

public class Login{
	
	private UserDetailDAO userDetailDAO = UserDetailDAOImpl.getInstance();
	private UserDAO userDAO = UserDAOImpl.getInstance();
	private User u;
	
	public String execute() {
		
		User user = userDAO.login(u.getLoginId(), u.getPwd());
		
		if(user==null){
			return "failed";
		}else{
			UserDetail detail = userDetailDAO.getUserDetail(user);
			Set<User> friends = userDAO.getFriendSet(user);
			ActionContext.getContext().getSession().put("friends", friends);
			ActionContext.getContext().getSession().put("user", user);
			ActionContext.getContext().getSession().put("detail", detail);
			return "topic";
		}
		
	}

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

}
