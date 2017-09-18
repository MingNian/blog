package com.qqzone.pojo;

import java.util.Date;

public class HostReply {
	private int id;
	private User author;
	private String content;
	private Date date;
	private Reply reply;

	public HostReply() {
	}

	public HostReply(int id, User author, String content, Date date, Reply reply) {
		super();
		this.id = id;
		this.author = author;
		this.content = content;
		this.date = date;
		this.reply = reply;
	}

	public HostReply(User author, String content, Date date, Reply reply) {
		super();
		this.author = author;
		this.content = content;
		this.date = date;
		this.reply = reply;
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

	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}

}
