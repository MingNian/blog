package com.qqzone.dao.base;

import java.sql.ResultSet;
import java.util.Set;

public abstract class Parser<T> implements IParser<T> {

	public T loadRs(ResultSet rs) {
		return null;
	}

	public Set<T> executeQuery(ResultSet rs) {
		return null;
	}

	public Object complexQuery(ResultSet rs) {
		return null;
	}

}
