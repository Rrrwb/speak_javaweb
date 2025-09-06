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
    <h1 class="page-title">开发者论坛</h1>
    
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

    <!-- 话题列表 -->
    <div class="table-responsive">
        <table class="table table-custom">
            <thead>
                <tr>
                    <th>标题</th>
                    <th>内容预览</th>
                    <th>作者</th>
                    <th>发布时间</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${topicPage.list}" var="topic">
                    <tr>
                        <td>
                            <a href="${pageContext.request.contextPath}/topic?method=findDetailById&topic_id=${topic.id}" class="topic-title">
                                ${topic.title}
                            </a>
                        </td>
                        <td>
                            <div class="topic-content">${topic.content}</div>
                        </td>
                        <td>
                            <span class="author-info">${topic.username}</span>
                        </td>
                        <td>
                            <span class="time-info">${topic.createTime}</span>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/topic?method=findDetailById&topic_id=${topic.id}" class="action-btn">
                                查看详情
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- 分页 -->
    <div class="pagination-custom">
        <ul class="pagination">
            <li><a href="#">&laquo;</a></li>
            <c:if test="${topicPage.totalPage>0}">
                <c:forEach var="i" begin="0" end="${topicPage.totalPage-1}" step="1">
                    <li>
                        <a href="${pageContext.request.contextPath}/topic?method=list&c_id=${empty param.c_id ? 1 : param.c_id}&page=${i+1}">${i+1}</a>
                    </li>
                </c:forEach>
            </c:if>
            <li><a href="#">&raquo;</a></li>
        </ul>
    </div>

</div>

</body>
</html>