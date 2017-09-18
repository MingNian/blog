package com.qqzone.pojo;

import java.util.Date;
import java.util.Set;

public class Topic {
	private int id;
	private String head;
	private String content;
	private User author;
	private Date date;
	private int replyCount;
	
	private Set<Reply> replySet;
	
	public Topic(){}

	public Topic(int id, String head, String content, User author, Date date) {
		super();
		this.id = id;
		this.head = head;
		this.content = content;
		this.author = author;
		this.date = date;
	}

	public Topic(String head, String content, User author, Date date) {
		super();
		this.head = head;
		this.content = content;
		this.author = author;
		this.date = date;
	}

	public Topic(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set<Reply> getReplySet() {
		return replySet;
	}

	public void setReplySet(Set<Reply> replySet) {
		this.replySet = replySet;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	
	
}
