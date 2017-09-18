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
		<div style="width: 99%;height: 20px;"></div>
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
			<form role="form" action="addTopic.action" method="post" >
				<div class="form-group">
					<input type="text" name="head"
						class="form-control" placeholder="请输入文章标题" required autofocus/>
					<div style="width: 100%;height: 20px;"></div>
					<textarea  name="content" class="form-control" rows="15"></textarea>
					<div style="width: 86%;padding-top: 20px;float: left;"></div>
					<div style="width: 10%;padding-top: 20px;float: left;">
						<input type="submit" value="提交" class="btn btn-lg btn-primary" >
					</div>
				</div>
			</form>
		</div>
	</div>


</body>
<!-- JQuery -->
<script src="js/jquery.min.js"></script>

</html>

