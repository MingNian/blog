package com.qqzone.dao;

import java.util.Set;

import com.qqzone.pojo.User;

public interface UserDAO {
	
	User login(String loginId,String pwd);
	
	Set<User> getFriendSet(User user);
	
	boolean updateUser(User user);
	
	User getUserById(int id);
	
	int addUser(User user);
	
	User addFriend(String nickName);
	
	boolean beFriend(User u1,User u2);
	
}
