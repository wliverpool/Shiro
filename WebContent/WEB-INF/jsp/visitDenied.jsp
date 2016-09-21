<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>拥有角色</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<shiro:notAuthenticated>
游客无权访问
</shiro:notAuthenticated>
<shiro:authenticated>
[<shiro:principal/>]没有指定角色或者权限.
</shiro:authenticated>
</body>
</html>