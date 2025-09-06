<%--
  开发者论坛 - 主页
  这是一个简单的转发页面，将用户重定向到默认分类的话题列表
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>开发者论坛</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/custom.css">
</head>
<body>
    <!-- 重定向到默认分类的话题列表 -->
    <jsp:forward page="/topic?method=list&c_id=1"></jsp:forward>
</body>
</html>