<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>开发者论坛 - 回复话题</title>
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
    <h1 class="page-title">回复话题</h1>
    
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

    <!-- 回复表单 -->
    <div class="form-custom">
        <form class="form-horizontal" role="form" action="/topic?method=replyByTopicId&topic_id=${param.topic_id}" method="post">
            
            <div class="form-group">
                <label class="col-sm-2 control-label">回复内容</label>
                <div class="col-sm-10">
                    <textarea class="form-control" name="content" placeholder="请输入您的回复内容..." rows="6" required></textarea>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-custom">
                        <i class="glyphicon glyphicon-comment"></i> 发表回复
                    </button>
                    <a href="${pageContext.request.contextPath}/topic?method=findDetailById&topic_id=${param.topic_id}" class="btn btn-default" style="margin-left: 10px;">
                        <i class="glyphicon glyphicon-arrow-left"></i> 返回话题
                    </a>
                </div>
            </div>

        </form>
    </div>

    <!-- 提示信息 -->
    <div class="alert alert-info alert-custom">
        <i class="glyphicon glyphicon-info-sign"></i>
        <strong>温馨提示：</strong>
        请文明回复，尊重他人观点。
    </div>

</div>

</body>
</html>