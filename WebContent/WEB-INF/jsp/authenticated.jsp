<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>已授权</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%-- 用户已经身份验证通过 --%>
<shiro:authenticated>
[<shiro:principal/>]身份验证已通过。
</shiro:authenticated>
</body>
</html>