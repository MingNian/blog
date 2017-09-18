package com.qqzone.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import com.qqzone.dao.UserDetailDAO;
import com.qqzone.dao.base.BaseDAO;
import com.qqzone.dao.base.IParser;
import com.qqzone.dao.base.Parser;
import com.qqzone.pojo.User;
import com.qqzone.pojo.UserDetail;

public class UserDetailDAOImpl extends BaseDAO<UserDetail> implements
		UserDetailDAO {

	private UserDetailDAOImpl() {
	}

	private static UserDetailDAOImpl instance = new UserDetailDAOImpl();

	public static UserDetailDAO getInstance() {
		return instance;
	}

	private IParser<UserDetail> parser = new Parser<UserDetail>() {

		@Override
		public UserDetail loadRs(ResultSet rs) {
			try {
				if (rs.next()) {
					int id = rs.getInt(1);
					String realName = rs.getString(2);
					Date birthday = rs.getDate(3);
					String star = rs.getString(4);
					String blood = rs.getString(5);
					String sex = rs.getString(6);

					return new UserDetail(id, realName, birthday, star, blood,
							sex);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}

	};

	public UserDetail getUserDetail(User user) {
		String sql = "select * from t_detail where id = ? ;";
		return super.load(parser, sql, user.getId());
	}

	public boolean updateUserDetail(UserDetail userDetail) {
		String sql = "update  t_detail set realName=?,birthday=?,star=?,blood=? , sex=? where id=? ";
		return super.executeUpdate(sql, userDetail.getRealName(),
				userDetail.getBirthday(), userDetail.getStar(),
				userDetail.getBlood(), userDetail.getSex(), userDetail.getId());
	}

	public boolean addUserDetail(UserDetail userDetail) {
		String sql = "insert into  t_detail values (?,?,?,?,?,?);";
		return super.executeUpdate(sql, userDetail.getId(),
				userDetail.getRealName(), userDetail.getBirthday(),
				userDetail.getStar(), userDetail.getBlood(),
				userDetail.getSex());
	}

}
