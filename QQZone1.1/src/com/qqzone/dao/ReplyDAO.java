package com.qqzone.dao;

import java.util.Set;

import com.qqzone.pojo.Reply;

public interface ReplyDAO {
	
	Set<Reply> getReplySet(int id);

	int getReplyCountByTopicId(int id);
	
	boolean addReply(Reply reply);
	
	boolean deleteReply(int id);
	
}
