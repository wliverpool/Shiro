<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>首页</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	
	<%-- 用户已经身份验证/记住我登录后显示相应的身份信息 --%>
	<shiro:user>
		欢迎[<shiro:principal/>]登录成功！<a href="${pageContext.request.contextPath}/logout">退出</a><c:if test="${isRunas}">上一个身份：${previousUsername}|<a href="${pageContext.request.contextPath}/runas/switchBack">切换回该身份</a></c:if>
		<a href="${pageContext.request.contextPath}/authenticated">已身份认证</a><br/>
		<a href="${pageContext.request.contextPath}/role">角色授权</a><br/>
		<a href="${pageContext.request.contextPath}/permission">权限授权</a><br/>
		<a href="${pageContext.request.contextPath}/authorityUserByAnnotation">通过注解限制已身份认证</a><br/>
		<a href="${pageContext.request.contextPath}/authorityRoleByAnnotation">通过注解限制角色授权</a><br/>
		<a href="${pageContext.request.contextPath}/authorityPermissionsByAnnotation">通过注解限制权限授权</a><br/>
		<shiro:hasRole name="admin">
			<a href="${pageContext.request.contextPath}/runas">切换身份</a>
		</shiro:hasRole>
	</shiro:user>
</body>
</html>