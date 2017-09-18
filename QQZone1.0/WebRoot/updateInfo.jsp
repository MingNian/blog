<%@ page contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">

<meta name="viewport" content="width=device-width, initial-scale=1">

<title>主页</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/dashboard.css" rel="stylesheet">
<link href="css/blog.css" rel="stylesheet">
<script type="text/javascript">
	window.onload = function(){
		//初始化 星座,血型 selected
		var s_star = document.getElementById("select_star");
		var s_blood = document.getElementById("select_blood");
		var o_star = '${detail.star}';
		var o_blood ='${detail.blood}';
		if(o_star!=null && o_star!=""){
			for(var i=0;i<s_star.options.length;i++){
				if(s_star.options[i].value==o_star){
					s_star.options[i].selected="selected";
					break;
				}
			}			
		}
		if(o_blood!=null && o_star!=""){
			for(var i=0;i<s_blood.options.length;i++){
				if(s_blood.options[i].value==o_blood){
					s_blood.options[i].selected="selected";
					break;
				}
			}			
		}
		var r_sex = document.getElementsByName("sex");
		for(var i=0;i<r_sex.length;i++){
			if("${detail.sex}"==r_sex[i].value){
				r_sex[i].checked=true;
			}
		}
		var d_birthday = document.getElementsByName("birthday")[0];
		d_birthday.value='${detail.birthday}';
	}
</script>
</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<c:choose>
					<c:when test="${empty user}">
						<a class="navbar-brand" href="login.jsp" >
						尚未登录  </a>
					</c:when>
					<c:otherwise>
						<a class="navbar-brand" href="topic.action?displayUserId=${user.id}&currPage=1">
					    ${user.nickName}</a>
					</c:otherwise>
				</c:choose>
				
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="topic.action?displayUserId=${user.id}&currPage=1">我的主页</a></li>
					<li><a href="updateInfo.jsp">个人资料</a></li>
					<li><a href="addTopic.jsp">添加日志</a></li>
					<li><a href="addFriend.jsp">添加好友</a></li>
				</ul>
			</div>
		</div>
	</nav>


	<div class="container">
		<div style="width: 100%;height: 20px;"></div>
		<div class="col-lg-3">
			<ul class="nav nav-sidebar">
				<li class="active"><a href="#">好友列表</a></li>

				<c:choose>
					<c:when test="${not empty friends }">
						<c:forEach items="${friends }" var="f">
							<li><a href="topic.action?displayUserId=${f.id}&currPage=1">${f.nickName}</a></li>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<li><a>没有任何好友</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<div class="col-lg-1"></div>
		<div class="col-lg-8">
		
			<div style="width: 80%;margin-left: 10%;" >
				<form action="updateInfo.action" method="post" enctype="multipart/form-data" class="form-signin"  >
					<input type="hidden" name="userId" value="${user.id}"/>
					<table style="width: 100%;border-collapse:separate; border-spacing:0px 10px;" >
						<tr>
							<td style="width: 30%;">登录账号:</td>
							<td style="text-align:center;width: 70%;">${user.loginId}</td>
						</tr>
						<tr>
							<td>昵称:</td>
							<td><input type="text" value="${user.nickName }" class="form-control"
								name="nickName" /></td>
						</tr>
						<tr>
							<td>头像:</td>
							<td><img src="${user.headImg }" width="100px;" height="100px"
								alt="头像" /> <input type="file"
								accept="image/png,image/jpg,image/jpeg" name="headImg"
								value="选择文件"></td>
						</tr>
						<tr>
							<td>真实姓名</td>
							<td><input type="text" value="${detail.realName }" class="form-control"
								 height="44px;" name="realName" /></td>
						</tr>
		
						<tr>
							<td>性别:</td>
							<td><input type="radio" name="sex" value="男" />男 &nbsp;
								&nbsp; &nbsp; &nbsp; <input type="radio" height="44px;" name="sex" value="女" />女</td>
						</tr>
						<tr>
							<td>出生日期</td>
							<td><input type="date"  class="form-control"
								name="birthday" height="44px;" /></td>
						</tr>
						<tr>
							<td>星座</td>
							<td><select id="select_star" name="star" class="form-control" >
									<option value="白羊座">--白羊座--</option>
									<option value="金牛座">--金牛座--</option>
									<option value="双子座">--双子座--</option>
									<option value="巨蟹座">--巨蟹座--</option>
									<option value="狮子座">--狮子座--</option>
									<option value="处女座">--处女座--</option>
									<option value="天秤座">--天秤座--</option>
									<option value="天蝎座">--天蝎座--</option>
									<option value="射手座">--射手座--</option>
									<option value="摩羯座">--摩羯座--</option>
									<option value="水瓶座">--水瓶座--</option>
									<option value="双鱼座">--双鱼座--</option>
							</select></td>
						</tr>
						<tr>
							<td>血型</td>
							<td><select id="select_blood" name="blood" class="form-control">
									<option value="A">- A -</option>
									<option value="AB">- AB -</option>
									<option value="B">- B -</option>
									<option value="O">- O -</option>
							</select></td>
						</tr>
						<tr>
							<td colspan="2" style="text-align: center;" >
								<input type="submit"  class="btn btn-lg btn-primary btn-block" value="确认修改"  />
							</td>
						</tr>
					</table>
				</form>
			
			</div>
		</div>
		
	</div>


</body>
<!-- JQuery -->
<script src="js/jquery.min.js"></script>

</html>

