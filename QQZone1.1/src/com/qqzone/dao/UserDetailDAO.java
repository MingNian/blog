package com.qqzone.dao;

import com.qqzone.pojo.User;
import com.qqzone.pojo.UserDetail;

public interface UserDetailDAO {
	
	UserDetail getUserDetail(User user);
	
	boolean updateUserDetail(UserDetail userDetail);
	
	boolean addUserDetail(UserDetail userDetail);
	
}
