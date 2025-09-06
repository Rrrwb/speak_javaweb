<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    // 确保响应使用UTF-8编码
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>开发者论坛 - 用户登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/custom.css">
    <script src="${pageContext.request.contextPath}/static/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/bootstrap.min.js"></script>
</head>
<body>

<div class="container main-container">
    <!-- 页面标题 -->
    <h1 class="page-title">用户登录</h1>

    <!-- 导航栏 -->
    <div class="navbar-custom">
        <ul class="nav nav-tabs">
            <c:forEach items="${categoryList}" var="category">
                <li>
                    <a href="${pageContext.request.contextPath}/topic?method=list&c_id=${category.id}">${category.name}</a>
                </li>
            </c:forEach>

            <li class="user-info" style="float: right">
                <a href="${pageContext.request.contextPath}/user/register.jsp">注册</a>
            </li>
            <li class="user-info" style="float: right">
                <a href="${pageContext.request.contextPath}/user/login.jsp">登录</a>
            </li>
        </ul>
    </div>

    <!-- 登录表单 -->
    <div class="form-custom" style="max-width: 500px; margin: 0 auto;">
        <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/user?method=login" method="post">

            <div class="form-group">
                <label class="col-sm-3 control-label">手机号</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" name="phone" placeholder="请输入手机号" required>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label">密码</label>
                <div class="col-sm-9">
                    <input type="password" class="form-control" name="pwd" placeholder="请输入密码" required>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-9">
                    <button type="submit" class="btn btn-custom">
                        <i class="glyphicon glyphicon-log-in"></i> 立即登录
                    </button>
                    <a href="${pageContext.request.contextPath}/user/register.jsp" class="btn btn-default" style="margin-left: 10px;">
                        <i class="glyphicon glyphicon-user"></i> 注册账号
                    </a>
                </div>
            </div>

            <!-- 错误信息显示 -->
            <c:if test="${not empty msg}">
                <div class="alert alert-danger alert-custom">
                    <i class="glyphicon glyphicon-exclamation-sign"></i>
                        ${msg}
                </div>
            </c:if>

        </form>
    </div>

    <!-- 登录提示 -->
    <div class="alert alert-info alert-custom" style="max-width: 500px; margin: 20px auto;">
        <i class="glyphicon glyphicon-info-sign"></i>
        <strong>温馨提示：</strong>
        如果您还没有账号，请先<a href="${pageContext.request.contextPath}/user/register.jsp" style="color: #667eea;">注册</a>。
    </div>

</div>

</body>
</html>