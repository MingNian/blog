package com.qqzone.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.qqzone.dao.UserDAO;
import com.qqzone.dao.base.BaseDAO;
import com.qqzone.dao.base.Parser;
import com.qqzone.pojo.User;

public class UserDAOImpl extends BaseDAO<User> implements UserDAO {

	private UserDAOImpl() {
	}

	private static UserDAOImpl instance = new UserDAOImpl();

	public static UserDAOImpl getInstance() {
		return instance;
	}

	Parser<User> parser = new Parser<User>() {
		@Override
		public User loadRs(ResultSet rs) {
			try {
				if (rs.next()) {
					int id = rs.getInt(1);
					String loginId = rs.getString(2);
					String pwd = rs.getString(3);
					String nickName = rs.getString(4);
					String headImg = rs.getString(5);
					User user = new User(id, loginId, pwd, nickName, headImg);
					return user;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public Set<User> executeQuery(ResultSet rs) {
			Set<User> friends = new HashSet<User>();
			try {
				while (rs.next()) {
					int id = rs.getInt(1);
					String nickName = rs.getString(4);
					String headImg = rs.getString(5);
					friends.add(new User(id, nickName, headImg));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return friends;
		}

		@Override
		public Object complexQuery(ResultSet rs) {
			// TODO Auto-generated method stub
			return super.complexQuery(rs);
		}

	};

	public User login(String loginId, String pwd) {
		String sql = "select * from t_user where loginId = ? and pwd = ? ";
		return super.load(parser, sql, loginId, pwd);
	}

	public Set<User> getFriendSet(User user) {
		String sql = "select t_user.* from (select * from t_friend where userId =?) t1 left join t_user on t1.friendId  = t_user.id ";
		return super.executeQuery(parser, sql, user.getId());
	}

	public boolean updateUser(User user) {
		String sql = "update  t_user set pwd=? ,nickName = ? ,headImg =?  where id = ?";
		return super.executeUpdate(sql, user.getPwd(), user.getNickName(),
				user.getHeadImg(), user.getId());
	}

	public User getUserById(int id) {
		String sql = "select * from t_user where id = ?";
		return super.load(parser, sql, id);
	}

	public int addUser(User user) {
		String sql = "insert into t_user values(null,?,?,?,?)";
		return executeUpdateReturnGeneratedKey(sql, user.getLoginId(), user.getPwd(),
				user.getNickName(), user.getHeadImg());
	}

	public User addFriend(String nickName) {
		String sql = "select * from t_user where nickName = ?";
		return super.load(parser, sql, nickName);
		
	}

	public boolean beFriend(User u1, User u2) {
		String sql = "insert into t_friend values(null,?,?);";
		return super.executeUpdate(sql, u1.getId(),u2.getId());
	}

}
