package com.qqzone.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.qqzone.dao.UserDAO;
import com.qqzone.dao.UserDetailDAO;
import com.qqzone.dao.impl.UserDAOImpl;
import com.qqzone.dao.impl.UserDetailDAOImpl;
import com.qqzone.pojo.User;
import com.qqzone.pojo.UserDetail;
import com.qqzone.util.StringUtil;

public class UpdateInfo {

	private UserDAO userDAO = UserDAOImpl.getInstance();
	private UserDetailDAO userDetailDAO = UserDetailDAOImpl.getInstance();

	public String execute() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		// disk file upload factory
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);

		upload.setSizeMax(10 * 1024 * 1024);
		upload.setHeaderEncoding("utf-8");

		String nickName = null, dateStr = null, realName = null, sex = null, star = null, blood = null, headImg = null;
		Date date = null;
		User user = (User) ActionContext.getContext().getSession().get("user");
		try {
			List<FileItem> fileItems = upload.parseRequest(request);
			Iterator<FileItem> iter = fileItems.iterator();
			while (iter.hasNext()) {
				FileItem item = iter.next();
				if (item.isFormField()) {
					String itemName = item.getFieldName();

					if (itemName.equals("nickName")) {
						nickName = item.getString("utf-8");
					} else if (itemName.equals("realName")) {
						realName = item.getString("utf-8");
					} else if (itemName.equals("sex")) {
						sex = item.getString("utf-8");
					} else if (itemName.equals("star")) {
						star = item.getString("utf-8");
					} else if (itemName.equals("blood")) {
						blood = item.getString("utf-8");
					} else if (itemName.equals("birthday")) {
						SimpleDateFormat formatter = new SimpleDateFormat(
								"yyyy-MM-dd");
						dateStr = item.getString();
						try {
							java.util.Date d = formatter.parse(dateStr);
							date = new Date(d.getTime());
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
				} else {
					headImg = item.getString("utf-8");
					if (StringUtil.isNotEmpty(headImg)) {
						InputStream is = item.getInputStream();
						byte[] bs = new byte[1024];
						int len = -1;

						String picName = "pic";
						picName = "imgs/" + picName + "__"
								+ UUID.randomUUID().toString() + ".jpg";
						headImg = picName;
						picName = "../JavaWorkspace/.metadata/.me_tcat7/webapps/QQZone/"
								+ picName;

						OutputStream os = new FileOutputStream(
								new File(picName));
						while ((len = is.read(bs)) != -1) {
							os.write(bs, 0, len);
						}
						os.close();
						os.flush();
						user.setHeadImg(headImg);
					} else {
						headImg = user.getHeadImg();
						user.setHeadImg(headImg);
					}
				}
				user.setNickName(nickName);
				UserDetail detail = (UserDetail)ActionContext.getContext().getSession().get("detail");
				detail.setBlood(blood);
				detail.setRealName(realName);
				detail.setSex(sex);
				detail.setStar(star);
				detail.setBirthday(date);

				userDAO.updateUser(user);
				userDetailDAO.updateUserDetail(detail);
				
				ActionContext.getContext().getSession().put("detail", detail);
				ActionContext.getContext().getSession().put("user", user);

			}

		} catch (FileUploadException e) {
			e.printStackTrace();
			return "failed";
		}catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
		return "updateInfo";
	}

}
