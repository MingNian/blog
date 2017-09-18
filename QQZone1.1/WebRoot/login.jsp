<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">

<meta name="viewport" content="width=device-width, initial-scale=1">

<title>登录</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/signin.css" rel="stylesheet">



</head>

<body>

	<div class="container">

		<form class="form-signin" method="post" action="login.action">
			<h2 class="form-signin-heading">Please sign in</h2>

			<input type="text" id="inputEmail" name="u.loginId"
				class="form-control" placeholder="请输入用户名" required autofocus>

			<input type="password" name="u.pwd" id="inputPassword"
				class="form-control" placeholder="请输入密码" required>
			<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
			<div class="checkbox">
				<label> <a href="regist.jsp" >注册</a>
				</label>
			</div>
		</form>

	</div>
</body>

<!-- JQuery -->
<script src="js/jquery.min.js"></script>

</html>

