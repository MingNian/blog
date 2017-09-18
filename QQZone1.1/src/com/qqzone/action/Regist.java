package com.qqzone.action;

import java.sql.Date;

import com.qqzone.dao.UserDAO;
import com.qqzone.dao.UserDetailDAO;
import com.qqzone.dao.impl.UserDAOImpl;
import com.qqzone.dao.impl.UserDetailDAOImpl;
import com.qqzone.pojo.User;
import com.qqzone.pojo.UserDetail;

public class Regist{
	private UserDAO userDAOImpl = UserDAOImpl.getInstance();
	private UserDetailDAO userDetailDAOImpl = UserDetailDAOImpl.getInstance();

	private String pwd;
	private String nickName;
	private String loginId;

	public String execute(){

			User user = new User(loginId, pwd, nickName, "imgs/default.jpg");
			int did = userDAOImpl.addUser(user);
			UserDetail userDetail = new UserDetail(did,"帅的被人砍", new Date(
					System.currentTimeMillis()), "水瓶座", "AB", "男");
			userDetailDAOImpl.addUserDetail(userDetail);
			return "failed";

	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
}
