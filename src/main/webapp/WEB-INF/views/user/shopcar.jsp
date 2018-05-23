<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>购物车</title>

    <link href="/static/css/common.css" rel="stylesheet" type="text/css">
    <link href="/static/css/cart.css" rel="stylesheet" type="text/css">


</head>
<body>
<%@include file="../include/top.jsp"%>
<div class="container cart">
    <div class="span24">
        <div class="step step1">

        </div>
        <table>
            <tbody><tr>
                <th>图片</th>
                <th>商品</th>
                <th>价格</th>
                <th>数量</th>
                <th>小计</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${sessionScope.mapCart.cartItems}" var="cs">
                <tr>
                    <td width="60">
                        <input type="hidden" name="id" value="22">
                        <img src="${pageContext.request.contextPath}/static/${cs.product.image}">
                    </td>
                    <td>
                        <a target="_blank"> </a>
                    </td>
                    <td>
                        ￥${cs.product.shopPrice}
                    </td>
                    <td class="quantity" width="60">
                        ${cs.count}
                    </td>
                    <td width="140">
                        <span class="subtotal">￥${cs.subtotal}</span>
                    </td>
                    <td>
                        <a href="/del/shop/one?pid=${cs.product.pid}" class="delete">删除</a>
                    </td>
                </tr>
            </c:forEach>

            </tbody></table>
        <dl id="giftItems" class="hidden" style="display: none;">
        </dl>
        <div class="total">
            <em id="promotion"></em>
            <em>
                登录后确认是否享有优惠
            </em>
            赠送积分: <em id="effectivePoint">596</em>
            商品金额: <strong id="effectivePrice">￥${sessionScope.mapCart.total}元</strong>
        </div>
        <div class="bottom">
            <a href="/del/shop/all" id="clear" class="clear">清空购物车</a>
            <a href="/shop/sub" id="submit" class="submit">提交订单</a>
        </div>
    </div>
</div>
<%@include file="../include/footer.jsp"%>
</body></html>