<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>拥有角色</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%-- 当前Subject有角色 --%>
<shiro:hasRole name="admin">
[<shiro:principal/>]拥有角色。
</shiro:hasRole>
</body>
</html>