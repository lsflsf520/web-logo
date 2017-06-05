<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<html>
<head>
<title>登  录</title>
<link href="${base}/css/login.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="login_content" align="center">
			<div class="loginForm">
				<form action="${base }/sys/doLogon.do" method="post">
					<p>
						<label>用户名</label>
						<input type="text" name="username" size="20" value="${username }" class="login_input" />
					</p>
					<p>
						<label>密  码</label>
						<input type="password" name="password" size="20" class="login_input" />
					</p>
					<div class="login_bar">
						<input class="sub" type="submit" value="登   录" />
					</div>
				</form>
			</div>
	</div>
</body>


</html>