package com.qqzone.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.qqzone.dao.ReplyDAO;
import com.qqzone.dao.base.BaseDAO;
import com.qqzone.dao.base.IParser;
import com.qqzone.dao.base.Parser;
import com.qqzone.pojo.Reply;
import com.qqzone.pojo.Topic;
import com.qqzone.pojo.User;

public class ReplyDAOImpl extends BaseDAO<Reply> implements ReplyDAO {

	private ReplyDAOImpl() {}
	private static ReplyDAOImpl instance = new ReplyDAOImpl();
	public static ReplyDAOImpl getInstance(){
		return instance;
	}
	
	private IParser<Reply> parser = new Parser<Reply>() {


		@Override
		public Object complexQuery(ResultSet rs) {
			try {
				if(rs.next()){
					return rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return super.complexQuery(rs);
		}

		@Override
		public Set<Reply> executeQuery(ResultSet rs) {
			Set<Reply> replySet = new HashSet<Reply>();
			try {
				while(rs.next()){
					int id = rs.getInt(1);
					int authorId = rs.getInt(2);
					String content = rs.getString(3);
					Date date = rs.getDate(4);
					int topicId= rs.getInt(5);
					User author = new User(authorId);
					Topic topic = new Topic(topicId);
					replySet.add(new Reply(id, author, content, date, topic));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return replySet;
		}
		
	};
	
	public Set<Reply> getReplySet(int id) {
		String sql = "select * from t_reply where topic = ? order by date ";
		return super.executeQuery(parser, sql, id);
	}

	public boolean addReply(Reply reply) {
		String sql = "insert into t_reply values(null,?,?,?,?);";
		return super.executeUpdate(sql, reply.getAuthor().getId(),reply.getContent(),reply.getDate(),reply.getTopic().getId());
	}

	public int getReplyCountByTopicId(int id) {
		String sql=" select count(*) from t_reply where topic = ?  ";
		return (Integer) complexQuery(parser, sql, id);
	}

	public boolean deleteReply(int id) {
		String sql = "delete from t_reply where id = ? ";
		return super.executeUpdate(sql, id);
	}

}
