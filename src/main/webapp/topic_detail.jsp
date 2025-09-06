<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>开发者论坛</title>
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
    <h1 class="page-title">话题详情</h1>
    
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
                    <li class="user-info" style="float: right">
                        <a href="${pageContext.request.contextPath}/reply.jsp?topic_id=${param.topic_id}">盖楼回复</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>

    <!-- 话题详情 -->
    <div class="topic-detail">
        <h2 class="topic-detail-title">${topic.title}</h2>
        <div class="topic-detail-content">${topic.content}</div>
        <div class="topic-detail-meta">
            <div class="row">
                <div class="col-md-6">
                    <strong>作者：</strong><span class="author-info">${topic.username}</span>
                </div>
                <div class="col-md-6">
                    <strong>发布时间：</strong><span class="time-info">${topic.createTime}</span>
                </div>
            </div>
        </div>
    </div>

    <!-- 回复列表 -->
    <div class="reply-section">
        <h3 style="color: #2c3e50; margin-bottom: 20px; border-bottom: 2px solid #667eea; padding-bottom: 10px;">
            回复列表 (${replyPage.totalRecord} 条回复)
        </h3>
        
        <c:forEach items="${replyPage.list}" var="reply">
            <div class="reply-item">
                <div class="reply-header">
                    <div>
                        <span class="reply-author">${reply.username}</span>
                        <span class="reply-time">${reply.createTime}</span>
                    </div>
                    <span class="reply-floor">第 ${reply.floor} 楼</span>
                </div>
                <div class="reply-content">${reply.content}</div>
            </div>
        </c:forEach>
    </div>

    <!-- 分页 -->
    <div class="pagination-custom">
        <ul class="pagination">
            <li><a href="#">&laquo;</a></li>
            <c:if test="${replyPage.totalPage>0}">
                <c:forEach var="i" begin="0" end="${replyPage.totalPage-1}" step="1">
                    <li>
                        <a href="${pageContext.request.contextPath}/topic?method=findDetailById&topic_id=${empty param.topic_id ? 1 : param.topic_id}&page=${i+1}">${i+1}</a>
                    </li>
                </c:forEach>
            </c:if>
            <li><a href="#">&raquo;</a></li>
        </ul>
    </div>

</div>

</body>
</html>