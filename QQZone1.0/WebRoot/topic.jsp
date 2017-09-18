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
			<div style="width: 100%;height: 100px;float: left;">
				<div style="width:20%;float: left;">
					<img alt="作者头像" src="${topicItem.author.headImg}" width="100px"
						height="100px">
				</div>
				<div style="width:80%;float: left;">
					<h2 class="blog-post-title">${topicItem.head}</h2>
					<p class="blog-post-meta" style="margin-right: 40px;">${topicItem.date}
						by <a href="#">${topicItem.author.nickName }</a>
						<c:choose>
							<c:when test="${topicItem.author.id==user.id}">
								<a href="deleteTopic.action?topicId=${topicItem.id}">删除</a>
							</c:when>
						</c:choose>
					</p>
				</div>
			</div>

			<div style="width: 90%;height:20px;margin-left: 5%;float: left"></div>
			<div style="width: 90%;margin-left: 5%;float: left">
				<div style="font-weight: 200;font-size: 16px;">${topicItem.content }</div>
			</div>
			<div style="width: 90%;margin-left: 5%;float: left">
				<hr>
			</div>

			<div style="width: 90%;margin-left: 5%;float: left">
				<form method="post" action="reply.action">
					<input type="hidden" value="${topicItem.id}" name="topicId" />
					<div style="width: 87%;float: left;">
						<input type="text" name="reply" class="form-control"
							placeholder="你的回复">
					</div>
					<div style="width: 10%;float: left;margin-left: 3%;">
						<input type="submit" value="回复" class="btn btn-primary" />
					</div>
				</form>
				<div style="padding-top: 30px;width: 100%;float: left;">
					<c:choose>
						<c:when test="${empty replySet}">
							<h3 style="text-align: center;">暂时没有回复!</h3>
						</c:when>
						<c:otherwise>
							<c:forEach items="${replySet}" var="r">
								<div style="width: 100%;height: 10px;"></div>
								<div
									style="width: 100%;height: auto;min-height: 68px;background-color: #eee;">
									<div style="width: 68px;height: 100%;float: left">
										<img alt="回复者头像" src="${r.author.headImg}" width="68px"
											height="68px">
									</div>
									<div
										style="margin-left:80px;height: auto;min-height: 68px;font-weight: 200;font-size: 14px;">
										${r.content}<br>
										<div style="width: 100%;">
											<p class="blog-post-meta">${r.date}
												by <a href="#">${r.author.nickName }</a>
												<c:choose>
													<c:when test="${r.author.id ==user.id}">
														<a href="deleteReply.action?replyId=${r.id}"
															style="margin-left: 10px;">删除</a>
													</c:when>
												</c:choose>
											</p>
										</div>
										<c:choose>
											<c:when test="${not empty r.hostReply }">
												<div style="width: 100%;height: auto;min-height: 44px;">
													<div style="width: 44;height: 100%;float: left">
														<img alt="回复者头像" src="${r.hostReply.author.headImg}"
															width="44" height="44">
													</div>
													<div
														style="margin-left:60px;font-weight: 200;font-size: 14px;">
														${r.hostReply.content }<br>
														<div style="width: 100%;">
															<p class="blog-post-meta">${r.hostReply.date}
																by <a href="#">${r.hostReply.author.nickName }</a>
																<c:choose>
																	<c:when test="${topicItem.author.id ==user.id}">
																		<a
																			href="deleteHostReply.action?hostReplyId=${r.hostReply.id}"
																			style="margin-left: 10px;">删除</a>
																	</c:when>
																</c:choose>
															</p>
														</div>
													</div>
												</div>
											</c:when>
											<c:otherwise>
												<c:choose>
													<c:when test="${user.id==topicItem.author.id }">
														<div
															style="height: auto;min-height:44px;width: 90%;margin-left: 5%;">
															<form method="post" action="hostReply.action">
																<input type="hidden" value="${r.id}" name="replyId" />
																<input type="hidden" name="topicIdStr"
																	value="${r.topic.id}" />
																<div style="width: 87%;float: left;">
																	<input type="text" name="hostReplyText"
																		class="form-control" placeholder="你的回复">
																</div>
																<div style="width: 10%;float: left;margin-left: 3%;">
																	<input type="submit" value="回复" class="btn btn-primary" />
																</div>
															</form>
														</div>
													</c:when>
												</c:choose>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</div>

			</div>
		</div>
	</div>

	<div style="width: 100%;height: 60px;"></div>
</body>
<!-- JQuery -->
<script src="js/jquery.min.js"></script>

</html>

