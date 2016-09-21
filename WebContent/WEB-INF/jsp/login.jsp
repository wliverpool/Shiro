<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
<title>登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
.error {
	color: red;
}
</style>
</head>
<body>
	<h4>
	<%-- 用户没有身份验证时显示 --%>
	<shiro:guest>欢迎游客访问</shiro:guest>
	</h4>
	<div class="error">${error}</div>
	<form action="" method="post">
		用户名：<input type="text" name="username"><br /> 
		密码：<input type="password" name="password"><br /> 
       	 验证码：
        <input type="text" name="captcha">
        <img class="jcaptcha-btn jcaptcha-img" src="${pageContext.request.contextPath}/captcha" title="点击更换验证码">
        <a class="jcaptcha-btn" href="javascript:;">换一张</a>
        <br/>
		下次自动登录：<input type="checkbox" name="rememberMe" value="true"><br/>
		<input type="submit" value="登录">
	</form>
</body>
<script src="${pageContext.request.contextPath}/static/js/jquery-1.11.0.min.js"></script>
<script>
    $(function() {
        $(".jcaptcha-btn").click(function() {
            $(".jcaptcha-img").attr("src", '${pageContext.request.contextPath}/captcha?'+new Date().getTime());
        });
    });
</script>
</html>