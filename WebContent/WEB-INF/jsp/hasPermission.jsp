<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="customshiro" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>拥有权限</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%-- 前Subject有权限 --%>
<shiro:hasPermission name="user:create">
[<shiro:principal/>]拥有权限。
</shiro:hasPermission>
<customshiro:hasAnyPermissions name="user:create,abc:update">
用户[<shiro:principal/>]拥有权限user:create或abc:update<br/>
</customshiro:hasAnyPermissions>
<customshiro:hasAllPermissions name="user:create,menu:create">
用户[<shiro:principal/>]拥有权限user:create和menu:create<br/>
</customshiro:hasAllPermissions>
</body>
</html>