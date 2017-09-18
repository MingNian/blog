package com.qqzone.dao.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

public class BaseDAO<T> {
	private PreparedStatement psmt;
	private Connection conn;
	private ResultSet rs;

	private Connection getConn() {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3307/qqzone", "root", "root");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void close(ResultSet rs, PreparedStatement psmt, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (psmt != null ) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void setParams(PreparedStatement psmt, Object... params) {
		try {
			for (int i = 0; i < params.length; i++) {
				psmt.setObject(i + 1, params[i]);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public boolean executeUpdate(String sql, Object... params) {
		conn = getConn();
		try {
			psmt = conn.prepareStatement(sql);
			setParams(psmt, params);
			return psmt.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(rs, psmt, conn);
		}
		return false;
	}
	
	public T load(IParser<T> parser,String sql,Object...params){
		conn = getConn();
		try {
			psmt = conn.prepareStatement(sql);
			setParams(psmt, params);
			rs = psmt.executeQuery();
			return parser.loadRs(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(rs, psmt, conn);
		}
		return null;
	}
	
	public Set<T> executeQuery(IParser<T> parser,String sql,Object...params){
		conn = getConn();
		try {
			psmt = conn.prepareStatement(sql);
			setParams(psmt, params);
			rs = psmt.executeQuery();
			return parser.executeQuery(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected Object complexQuery(IParser<T> parser,String sql,Object...params){
		conn = getConn();
		try {
			psmt = conn.prepareStatement(sql);
			setParams(psmt, params);	
			rs = psmt.executeQuery();
			return parser.complexQuery(rs);	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(rs, psmt, conn);
		}
		return 0;
	}
	
	protected int executeUpdateReturnGeneratedKey(String sql,Object...params){
		conn = getConn();
		try {
			psmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			setParams(psmt,params);
			int count = psmt.executeUpdate();
			if(count>0){
				rs = psmt.getGeneratedKeys();
				if(rs.next()){
					return rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(rs,psmt,conn);
		}
		return 0;
	}

}
