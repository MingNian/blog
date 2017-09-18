package com.qqzone.dao;

import com.qqzone.pojo.HostReply;
import com.qqzone.pojo.Reply;

public interface HostReplyDAO {
	
	HostReply getHostReply(Reply reply);
	
	boolean addHostReply(HostReply hostReply);
	
	boolean deleteHostReply(int id);
	
}
