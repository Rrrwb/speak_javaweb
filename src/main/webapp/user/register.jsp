<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>开发者论坛 - 用户注册</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/custom.css">
    <script src="${pageContext.request.contextPath}/static/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/bootstrap.min.js"></script>
</head>
<body>

<div class="container main-container">

    <!-- 页面标题 -->
    <h1 class="page-title">用户注册</h1>
    
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

    <!-- 注册表单 -->
    <div class="form-custom" style="max-width: 600px; margin: 0 auto;">
        <form class="form-horizontal" role="form" action="/user?method=register" method="post">
            
            <div class="form-group">
                <label class="col-sm-3 control-label">手机号</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" name="phone" placeholder="请输入手机号" required>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label">用户名</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" name="username" placeholder="请输入用户名" required>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label">密码</label>
                <div class="col-sm-9">
                    <input type="password" class="form-control" name="pwd" placeholder="请输入密码" required>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label">性别</label>
                <div class="col-sm-9">
                    <div class="radio-inline">
                        <label>
                            <input type="radio" name="sex" value="0" checked> 男
                        </label>
                    </div>
                    <div class="radio-inline">
                        <label>
                            <input type="radio" name="sex" value="1"> 女
                        </label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-9">
                    <button type="submit" class="btn btn-custom">
                        <i class="glyphicon glyphicon-ok"></i> 立即注册
                    </button>
                    <a href="${pageContext.request.contextPath}/user/login.jsp" class="btn btn-default" style="margin-left: 10px;">
                        <i class="glyphicon glyphicon-log-in"></i> 已有账号登录
                    </a>
                </div>
            </div>

        </form>
    </div>

    <!-- 注册提示 -->
    <div class="alert alert-info alert-custom" style="max-width: 600px; margin: 20px auto;">
        <i class="glyphicon glyphicon-info-sign"></i>
        <strong>注册须知：</strong>
        <ul style="margin: 10px 0; padding-left: 20px;">
            <li>用户名长度3-20个字符，支持中英文、数字</li>
            <li>密码长度6-20个字符，建议包含字母和数字</li>
            <li>手机号将作为登录凭证，请确保准确</li>
        </ul>
    </div>

</div>

</body>
</html>