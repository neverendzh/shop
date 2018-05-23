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

</head>
<body>

<%@include file="../include/top.jsp"%>



<div class="container cart">

    <div class="span24">

        <div class="step step1">
            <ul>

                <li  class="current"></li>
                <li>您的订单</li>
            </ul>
        </div>


        <table>
            <tbody>
            <tr>
                <th>图片</th>
                <th>商品</th>
                <th>价格</th>
                <th>数量</th>
                <th>小计</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${mapCart.cartItems}" var="cs">
                <tr>
                    <td width="60">
                        <input type="hidden" name="id" value="22"/>
                        <img src="${pageContext.request.contextPath}/static/${cs.product.image}"/>
                    </td>
                    <td>
                        <a target="_blank"><s:property value="product.pname"/>${cs.product.pname}</a>
                    </td>
                    <td>
                            ${cs.product.shopPrice}
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
                            <p class="delete">点击下发按钮付款</p>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
        <dl id="giftItems" class="hidden" style="display: none;">
        </dl>
        <div class="total">
            <em id="promotion"></em>
            待付金额: <strong id="effectivePrice">￥${mapCart.total}元</strong>
        </div>
        <form id="orderForm" action="/order_payOrder" method="post">
            <input type="hidden" name="order.oid" value=""/>
            <div class="span24">
                <input style="display: none" value="${mapCart.total}" name="total"/>
                <input style="display: none" value="${orders1.oid}" name="orderID"/>
                <p>
                    收货地址：<input name="order.user.addr" type="text" value="${user.addr}" style="width:350px" />
                    <br />
                    收货人&nbsp;&nbsp;&nbsp;：<input name="order.user.username" type="text" value="${user.name}" style="width:150px" />
                    <br />
                    联系方式：<input name="order.user.phone" type="text"value="${user.phone}" style="width:150px" />
                </p>
                <hr />
                <p>
                    选择银行：<br/>
                    <input type="radio" name="pd_FrpId" value="ICBC-NET-B2C" checked="checked"/>工商银行
                    <img src="/static/bank_img/icbc.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" name="pd_FrpId" value="BOC-NET-B2C"/>中国银行
                    <img src="/static/bank_img/bc.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" name="pd_FrpId" value="ABC-NET-B2C"/>农业银行
                    <img src="/static/bank_img/abc.bmp" align="middle"/>
                    <br/>
                    <input type="radio" name="pd_FrpId" value="BOCO-NET-B2C"/>交通银行
                    <img src="/static/bank_img/bcc.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" name="pd_FrpId" value="PINGANBANK-NET"/>平安银行
                    <img src="/static/bank_img/pingan.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" name="pd_FrpId" value="CCB-NET-B2C"/>建设银行
                    <img src="/static/bank_img/ccb.bmp" align="middle"/>
                    <br/>
                    <input type="radio" name="pd_FrpId" value="CEB-NET-B2C"/>光大银行
                    <img src="/static/bank_img/guangda.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" name="pd_FrpId" value="CMBCHINA-NET-B2C"/>招商银行
                    <img src="/static/bank_img/cmb.bmp" align="middle"/>
                </p>
                <hr />
                <p style="text-align:right">
                    <a href="javascript:document.getElementById('orderForm').submit();">
                        <img src="/static/images/finalbutton.gif" width="204" height="51" border="0" />
                    </a>
                </p>
            </div>
        </form>
    </div>

</div>
<%@include file="../include/footer.jsp"%>
</body>
</html>