package com.qqzone.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.qqzone.dao.TopicDAO;
import com.qqzone.dao.base.BaseDAO;
import com.qqzone.dao.base.Parser;
import com.qqzone.pojo.Topic;
import com.qqzone.pojo.User;

public class TopicDAOImpl extends BaseDAO<Topic> implements TopicDAO {
	private TopicDAOImpl() {
	}

	private static TopicDAOImpl instance = new TopicDAOImpl();

	public static TopicDAOImpl getInstance() {
		return instance;
	}

	private Parser<Topic> parser = new Parser<Topic>() {

		@Override
		public Topic loadRs(ResultSet rs) {
			try {
				if (rs.next()) {
					int id = rs.getInt(1);
					String head = rs.getString(2);
					String content = rs.getString(3);
					int author = rs.getInt(4);
					String nickName = rs.getString(5);
					String headImg = rs.getString(6);
					Date date = rs.getDate(7);
					return new Topic(id, head, content, new User(author,
							nickName, headImg), date);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public Set<Topic> executeQuery(ResultSet rs) {
			Set<Topic> topicSet = new HashSet<Topic>();
			try {
				while (rs.next()) {
					int id = rs.getInt(1);
					String head = rs.getString(2);
					String content = rs.getString(3);
					int author = rs.getInt(4);
					String nickName = rs.getString(5);
					String headImg = rs.getString(6);
					Date date = rs.getDate(7);
					topicSet.add(new Topic(id, head, content, new User(author,
							nickName, headImg), date));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return topicSet;
		}

		@Override
		public Object complexQuery(ResultSet rs) {
			try {
				if (rs.next()) {
					return rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return 0;
		}

	};

	public Set<Topic> getTopicSet(User user, int currPage, int pageSize) {
		String sql = "select t1.id,t1.head,t1.content,t1.author,t2.nickName,t2.headImg,t1.date from t_topic t1 inner join t_user t2 where t2.id= ? and t1.author = t2.id  limit ? , ? ";
		return executeQuery(parser, sql, user.getId(), (currPage - 1)
				* pageSize, currPage * pageSize);
	}

	public int getMaxPage(User dispalyUser) {
		String sql = "select count(*) from t_topic where author = ? ";
		return (Integer) complexQuery(parser, sql, dispalyUser.getId());
	}

	public Topic getTopicById(int id) {
		String sql = "select t1.id,t1.head,t1.content,t1.author,t2.nickName,t2.headImg,t1.date from t_topic t1 inner join t_user t2 where t1.id= ? and t1.author = t2.id ";
		return super.load(parser, sql, id);
	}

	public boolean addTopic(Topic topic) {
		String sql = "insert into t_topic values(null,?,?,?,?);";
		return super.executeUpdate(sql, topic.getHead(), topic.getContent(),
				topic.getAuthor().getId(), topic.getDate());
	}

	public boolean deleteTopic(int id) {
		String sql = "delete from t_topic where id = ? ";
		return super.executeUpdate(sql, id);
	}

}
