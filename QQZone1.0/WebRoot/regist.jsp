<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">

<meta name="viewport" content="width=device-width, initial-scale=1">

<title>注册</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/signin.css" rel="stylesheet">



</head>

<body>

	<div class="container">

		<form class="form-signin" method="post" action="regist.action">
			<h2 class="form-signin-heading">Please regist</h2>

			<input type="text" id="inputEmail" name="loginId"
				class="form-control" placeholder="请输入用户名" required >
				
			<input type="text" id="inputNickName" name="nickName"
				class="form-control" placeholder="请输入昵称" required >

			<input type="password" name="pwd" id="inputPassword"
				class="form-control" placeholder="请输入密码" required>
			<button class="btn btn-lg btn-primary btn-block" type="submit">注册</button>
			<div class="checkbox">
				<label> <a href="login.jsp">登录</a>
				</label>
			</div>
		</form>

	</div>
</body>

<!-- JQuery -->
<script src="js/jquery.min.js"></script>

</html>

