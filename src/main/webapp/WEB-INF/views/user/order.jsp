<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/21 0021
  Time: 23:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

    <title>订单页面</title>
    <link href="/static/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="/static/css/cart.css" rel="stylesheet" type="text/css"/>
    <link href="/static/layui/css/layui.css"/>

</head>
<body>

<%@include file="../include/top.jsp"%>



<div class="container cart">

    <div class="span24">

        <div class="step step1">
            <ul>

                <li  class="current"></li>
                <li style="text-align: center;margin-left: 18%">您的订单</li>
            </ul>
        </div>
        <c:if test="${not empty msg}">
            <p style="text-align: center;color: red">${msg}</p>
        </c:if>

        <table>
            <tbody>
            <c:forEach items="${DindDanModel}" var="cs">
                <tr>
                    <th colspan="8">订单编号:&nbsp;&nbsp;&nbsp;&nbsp;
                        ${cs.oid}
                    </th>
                </tr>
            <tr>
                <th>图片</th>
                <th>商品</th>
                <th>价格</th>
                <th>数量</th>
                <th>小计</th>
                <th>操作</th>
            </tr>
            <tr>
                <td width="60">
                    <input type="hidden" name="id" value="22"/>
                    <img src="${pageContext.request.contextPath}/static/${cs.image}"/>
                </td>
                <td>
                    <a target="_blank"><s:property value="product.pname"/>${cs.pname}</a>
                </td>
                <td>
                    ${cs.shopPrice}
                </td>
                <td class="quantity" width="60">
                    <input type="text" name="count" value="${cs.count}" maxlength="4" disabled onpaste="return false;"/>
                    <div>
                        <span class="increase">&nbsp;</span>
                        <span class="decrease">&nbsp;</span>
                    </div>
                </td>
                <td width="140">
                    <span class="subtotal">￥${cs.subtotal}</span>
                </td>
                <td>
                        <c:if test="${cs.state  == 1}">
                            <a href="/sel/DinDan/NotFu?oid=${cs.oid}" class="delete">订单已提交：去付款</a>
                            <a href="/del/ding/dan?pid=${cs.pid}&oid=${cs.oid}" class="delete">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除</a>
                        </c:if>
                        <c:if test="${cs.state  == 2}">
                            <p  class="delete">已付款：代发货</p>
                        </c:if>
                        <c:if test="${cs.state  == 3}">
                            <a href="/queren/ding/dan?pid=${cs.oid}"  class="delete">已发货：点击确认</a>
                        </c:if>
                        <c:if test="${cs.state  == 4}">
                            <a  class="delete">交易完成</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <a type="button"  href="/del/ding/dan?pid=${cs.pid}&oid=${cs.oid}" class="layui-btn">删除</a>
                        </c:if>
                </td>
            </tr>
            </c:forEach>

            </tbody>
        </table>
        <dl id="giftItems" class="hidden" style="display: none;">
        </dl>
        <div class="total">
            <em id="promotion"></em>
            待付金额: <strong id="effectivePrice">￥${count}元</strong>
        </div>

    </div>

</div>
<%@include file="../include/footer.jsp"%>
</body>
</html>