package com.qqzone.pojo;

import java.util.Set;

public class User {
	@Override
	public String toString() {
		return "User [id=" + id + ", loginId=" + loginId + ", pwd=" + pwd
				+ ", nickName=" + nickName + ", headImg=" + headImg + "]";
	}

	private int id;
	private String loginId,pwd,nickName,headImg;
	
	private UserDetail userDetail;
	private Set<Topic> topicSet;
	private Set<Reply> replySet;
	private Set<User> friendSet;
	
	public User(){}

	public User(int id, String loginId, String pwd, String nickName,String headImg) {
		super();
		this.id = id;
		this.loginId = loginId;
		this.pwd = pwd;
		this.nickName = nickName;
		this.headImg = headImg;
	}

	public User(int id) {
		super();
		this.id = id;
	}

	public User(String loginId, String pwd, String nickName, String headImg) {
		super();
		this.loginId = loginId;
		this.pwd = pwd;
		this.nickName = nickName;
		this.headImg = headImg;
	}

	public User(int id, String nickName,String headImg) {
		super();
		this.id = id;
		this.nickName = nickName;
		this.headImg = headImg;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
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

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public Set<Topic> getTopicSet() {
		return topicSet;
	}

	public void setTopicSet(Set<Topic> topicSet) {
		this.topicSet = topicSet;
	}

	public Set<Reply> getReplySet() {
		return replySet;
	}

	public void setReplySet(Set<Reply> replySet) {
		this.replySet = replySet;
	}

	public Set<User> getFriendSet() {
		return friendSet;
	}

	public void setFriendSet(Set<User> friendSet) {
		this.friendSet = friendSet;
	}

	
}
