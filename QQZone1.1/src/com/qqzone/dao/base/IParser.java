package com.qqzone.dao.base;

import java.sql.ResultSet;
import java.util.Set;

public interface IParser<T> {
	T loadRs(ResultSet rs);
	
	Set<T> executeQuery(ResultSet rs);
	
	Object complexQuery(ResultSet rs);
}
