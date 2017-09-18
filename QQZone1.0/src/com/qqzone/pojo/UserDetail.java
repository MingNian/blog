package com.qqzone.pojo;

import java.sql.Date;

public class UserDetail {
	private int id;
	private String realName;
	private Date birthday;
	private String star,blood,sex;
	
	public UserDetail(){}

	public UserDetail(int id, String realName, Date birthday, String star,
			String blood,String sex) {
		super();
		this.id = id;
		this.realName = realName;
		this.birthday = birthday;
		this.star = star;
		this.blood = blood;
		this.sex=sex;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getBlood() {
		return blood;
	}

	public void setBlood(String blood) {
		this.blood = blood;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
}
