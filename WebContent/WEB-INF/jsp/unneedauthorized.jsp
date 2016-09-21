<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>无需授权</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%-- 用户已经身份验证通过 --%>
<shiro:authenticated>
[<shiro:principal/>]身份无需授权即可访问。
</shiro:authenticated>
<shiro:guest>
游客可访问。
</shiro:guest>
</body>
</html>