<%@ page contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
				<li class="active"><a>好友列表</a></li>

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
			<h2 class="sub-header">日志</h2>
			<div class="table-responsive">
					<c:choose>
						<c:when test="${empty topicSet }">
							<h4>${displayUser.nickName}的日志列表为空</h4>
						</c:when>
						<c:otherwise>
						<table class="table table-striped">
							<thead>
								<tr>
									<th>序号</th>
									<th>标题</th>
									<th>内容</th>
									<th>日期</th>
									<th>评论数</th>
								</tr>
							</thead>
							<c:set var="num" value="${pageSize*currPage-pageSize}" />
							<c:forEach items="${topicSet}" var="t">
								<tbody>
									<tr>
										<c:set var="num" value="${num+1}" />
										<td>${num}</td>
										<td><a href="topicItem.action?topicId=${t.id}" >
											${fn:substring(t.head,0,5)}${fn:length(t.head)>5?"...":""}</a></td>
										<td>${fn:substring(t.content,0,10)}${fn:length(t.content)>10?"...":""}</td>
										<td>${t.date}</td>
										<td>${t.replyCount}</td>
									</tr>
								</tbody>
							</c:forEach>
							</table><table  style="width: 100%; text-align: center;" >
								<tr><td>
								<a href="topic.action?displayUserId=${displayUser.id}&currPage=1" class="a_page" >首页</a>
								</td><td>
								<a href="topic.action?displayUserId=${displayUser.id}&currPage=${currPage == 1 ? 1 : currPage-1}"  class="a_page">上一页</a>
								</td><td>
								<a href="topic.action?displayUserId=${displayUser.id}&currPage=${currPage == maxPage ? maxPage : currPage+1}"  class="a_page">下一页</a>
								</td><td>
								<a href="topic.action?displayUserId=${displayUser.id}&currPage=${maxPage}" class="a_page">尾页</a>
								</td></tr></table>
						</c:otherwise>
					</c:choose>
			</div>
		</div>
	</div>
</body>
<!-- JQuery -->
<script src="js/jquery.min.js"></script>

</html>

