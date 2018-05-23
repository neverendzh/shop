<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header  class="header">
    <div class="container header">
    <div class="span5">
        <div class="logo">
            <a href="/">
                <img src="/static/image/r___________renleipic_01/logo.jpg" alt="传智播客">
            </a>
        </div>
    </div>
    <div class="span9">
        <div class="headerAd">

            <img src="/static/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障">
        </div>
    </div>
    <div class="span10 last">
        <div class="topNav clearfix">
            <ul>
                <li id="headerLogin" class="headerLogin" style="display: list-item;">
                    <c:if test="${sessionScope.user != null}">
                        <a href="/ding/dan">${sessionScope.user.username}</a>
                    </c:if>
                    <c:if test="${sessionScope.user == null}">
                        <a href="/login">登录</a>|
                    </c:if>
                </li>
                <li id="headerRegister" class="headerRegister" style="display: list-item;">
                    <a href="/zhuce">注册</a>|
                </li>
                <%--<li id="headerUsername" class="headerUsername"><a href="index.jsp">[退出]</a>|</li>--%>
                <%--<li id="headerLogout" class="headerLogout">--%>
                    <%--<a href="index.jsp">退出</a>|--%>
                <%--</li>--%>
                <li>
                    <a >会员中心</a>
                    |
                </li>
                <li>
                    <a >购物指南</a>
                    |
                </li>
                <li>
                    <a href="/logout">退出</a>

                </li>
            </ul>
        </div>
        <div class="cart">
            <a href="/seach/shop">购物车</a>
        </div>
        <div class="phone">
            客服热线:
            <strong>96008/53277764</strong>
        </div>
    </div>
    <div class="span24">
        <ul class="mainNav">
            <li>
                <a href="/">首页</a>
                |
            </li>

            <c:forEach items="${categoryList}" var="categoryList">
                <li>
                    <a href="/category?cid=${categoryList.cid}&page=1">${categoryList.cname}</a>
                    |
                </li>
            </c:forEach>
        </ul>
    </div>
    </div>
</header>
