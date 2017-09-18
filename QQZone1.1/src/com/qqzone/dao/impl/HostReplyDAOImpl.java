package com.qqzone.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.qqzone.dao.HostReplyDAO;
import com.qqzone.dao.base.BaseDAO;
import com.qqzone.dao.base.IParser;
import com.qqzone.dao.base.Parser;
import com.qqzone.pojo.HostReply;
import com.qqzone.pojo.Reply;
import com.qqzone.pojo.User;

public class HostReplyDAOImpl extends BaseDAO<HostReply> implements
		HostReplyDAO {

	private HostReplyDAOImpl() {
	};

	private static HostReplyDAOImpl instance = new HostReplyDAOImpl();

	public static HostReplyDAOImpl getInstance() {
		return instance;
	}

	private IParser<HostReply> parser = new Parser<HostReply>() {

		@Override
		public HostReply loadRs(ResultSet rs) {
			try {
				if (rs.next()) {
					int id = rs.getInt(1);
					int authorId = rs.getInt(2);
					String content = rs.getString(3);
					Date date = rs.getDate(4);
					int replyId = rs.getInt(5);
					return new HostReply(id, new User(authorId), content, date,
							new Reply(replyId));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}

	};

	public HostReply getHostReply(Reply reply) {
		String sql = "select t1.* from t_hostreply t1 inner join t_reply t2 where t1.reply = t2.id and t1.reply = ?";
		return super.load(parser, sql, reply.getId());
	}

	public boolean addHostReply(HostReply hostReply) {
		String sql = "insert into t_hostreply values(null,?,?,?,?); ";
		return super.executeUpdate(sql, hostReply.getAuthor().getId(),
				hostReply.getContent(), hostReply.getDate(), hostReply
						.getReply().getId());
	}

	public boolean deleteHostReply(int id) {
		String sql = "delete from t_hostReply where id = ?";
		return super.executeUpdate(sql, id);
	}

}
