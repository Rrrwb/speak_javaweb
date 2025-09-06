<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>开发者论坛 - 发布话题</title>
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
    <h1 class="page-title">发布新话题</h1>
    
    <!-- 导航栏 -->
    <div class="navbar-custom">
        <ul class="nav nav-tabs">
            <c:forEach items="${categoryList}" var="category">
                <li>
                    <a href="${pageContext.request.contextPath}/topic?method=list&c_id=${category.id}">${category.name}</a>
                </li>
            </c:forEach>

            <c:choose>
                <c:when test="${empty loginUser}">
                    <li class="user-info" style="float: right">
                        <a href="${pageContext.request.contextPath}/user/register.jsp">注册</a>
                    </li>
                    <li class="user-info" style="float: right">
                        <a href="${pageContext.request.contextPath}/user/login.jsp">登录</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="user-info" style="float: right">
                        <a href="${pageContext.request.contextPath}/user?method=logout">注销</a>
                    </li>
                    <li class="user-info" style="float: right">
                        <a href="#">${loginUser.username}</a>
                    </li>
                    <li style="float: right">
                        <img src="${loginUser.img}" class="img-circle user-avatar" width="35px" height="35px">
                    </li>
                    <li class="user-info" style="float: right">
                        <a href="${pageContext.request.contextPath}/publish.jsp">发布主题</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>

    <!-- 发布表单 -->
    <div class="form-custom">
        <form class="form-horizontal" role="form" action="/topic?method=addTopic" method="post">
            
            <div class="form-group">
                <label class="col-sm-2 control-label">话题标题</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" name="title" placeholder="请输入话题标题" required>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label">选择分类</label>
                <div class="col-sm-6">
                    <select class="form-control" name="c_id" required>
                        <option value="">请选择分类</option>
                        <c:forEach items="${categoryList}" var="category">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label">话题内容</label>
                <div class="col-sm-10">
                    <textarea class="form-control" name="content" placeholder="请输入话题内容..." rows="8" required></textarea>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-custom">
                        <i class="glyphicon glyphicon-send"></i> 发布话题
                    </button>
                    <a href="${pageContext.request.contextPath}/topic?method=list" class="btn btn-default" style="margin-left: 10px;">
                        <i class="glyphicon glyphicon-arrow-left"></i> 返回列表
                    </a>
                </div>
            </div>

        </form>
    </div>

</div>

</body>
</html>