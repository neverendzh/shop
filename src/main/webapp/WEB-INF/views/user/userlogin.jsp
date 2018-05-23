<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>会员登录</title>

<link href="/static/css/common.css" rel="stylesheet" type="text/css"/>
<link href="/static/css/login.css" rel="stylesheet" type="text/css"/>


</head>
<body>

<%@include file="../include/top.jsp"%>
	
</div>	<div class="container login">
		<div class="span12">
<div class="ad">
					<img src="/static/image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">
</div>		</div>
		<div class="span12 last">
			<div class="wrap">
				<div class="main">
					<div class="title">
						<strong>会员登录</strong>USER LOGIN 
					</div>
					<form id="loginForm" action="/login"  method="post">
						<table>
							<tbody><tr>
								<th>
										用户名/E-mail:
								</th>
								<td>
									<input type="text" id="username" name="username" class="text" maxlength="20">
									
								</td>
							</tr>
							<tr>
								<th>
									密&nbsp;&nbsp;码:
								</th>
								<td>
									<input type="password" id="password" name="password" class="text" maxlength="20" autocomplete="off">
								</td>
							</tr>
								<tr>
									<th>
										验证码:
									</th>
									<td>
										<span class="fieldSet">
											<%--<input type="text" id="captcha" name="captcha" class="text captcha" maxlength="4" autocomplete="off"><img id="captchaImage" class="captchaImage" src="./image/captcha.jhtml" title="点击更换验证码">--%>
										</span>
									</td>
								</tr>
							<tr>
								<th>&nbsp;
									
								</th>
								<td>
									<label>
										<input type="checkbox" id="isRememberUsername" name="isRememberUsername" value="true">记住用户名
									</label>
									<label>
										&nbsp;&nbsp;<a >找回密码</a>
									</label>
								</td>
							</tr>
							<tr>
								<th>&nbsp;
									
								</th>
								<td>
									<input id="loginBtn" type="button" class="submit" value="登 录">
								</td>
							</tr>
							<tr class="register">
								<th>&nbsp;
									
								</th>
								<td>
									<dl>
										<dt>还没有注册账号？</dt>
										<dd>
											立即注册即可体验在线购物！
											<a href="/zhuce">立即注册</a>
										</dd>
									</dl>
								</td>
							</tr>
						</tbody></table>
					</form>
				</div>
			</div>
		</div>
	</div>
<%@include file="../include/footer.jsp"%>
<script src="/static/js/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/jquery-validate/1.17.0/jquery.validate.js"></script>
</body>
<script>
    $(function(){
        $("#loginBtn").click(function(){
            $("#loginForm").submit();
        });

        $("#loginForm").validate({
            errorClass : "text-danger",
            errorElement : "span",
            rules : {
                username : {
                    required : true
                },
                password : {
                    required : true
                }
            },
            messages : {
                username : {
                    required : "请输入用户名"
                },
                password : {
                    required : "请输入密码"
                }
            },
        });

    });

</script>
</html>