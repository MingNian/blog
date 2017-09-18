package com.qqzone.dao;

import java.util.Set;

import com.qqzone.pojo.Topic;
import com.qqzone.pojo.User;

public interface TopicDAO{

	Set<Topic> getTopicSet(User user,int currPage,int pageSize); 
	
	int getMaxPage(User dispalyUser);
	
	Topic getTopicById(int id);
	
	boolean addTopic(Topic topic);
	
	boolean deleteTopic(int id);
	
}
