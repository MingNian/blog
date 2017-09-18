package com.qqzone.pojo;

import java.util.Date;

public class Reply {
	private int id;
	private User author;
	private String content;
	private Date date;
	private Topic topic;
	private HostReply hostReply;
	
	public Reply(){}

	public Reply(int id, User author, String content, Date date, Topic topic) {
		super();
		this.id = id;
		this.author = author;
		this.content = content;
		this.date = date;
		this.topic = topic;
	}

	public Reply(User author, String content, Date date, Topic topic) {
		super();
		this.author = author;
		this.content = content;
		this.date = date;
		this.topic = topic;
	}

	public Reply(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public HostReply getHostReply() {
		return hostReply;
	}

	public void setHostReply(HostReply hostReply) {
		this.hostReply = hostReply;
	}
	
}
