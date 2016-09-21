<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>切换身份</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css.css">
</head>
<body>
    <div>${msg}</div>
    <h2>切换身份</h2>
    <c:if test="${isRunas}">上一个身份：${previousUsername}|<a href="${pageContext.request.contextPath}/runas/switchBack">切换回该身份</a></c:if>
    <h3>切换到其他身份：</h3>
    <table class="table">
        <thead>
        <tr>
            <th>用户名</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${toUserIds}" var="user">
            <tr>
                <td>${user.username}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/runas/switchTo/${user.id}">切换到该身份</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>