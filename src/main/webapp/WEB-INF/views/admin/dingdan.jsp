<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Home</title>
    <!-- Bootstrap Styles-->
    <link href="/static/assets/css/bootstrap.css" rel="stylesheet" />
    <!-- FontAwesome Styles-->
    <link href="/static/assets/css/font-awesome.css" rel="stylesheet" />
    <!-- Morris Chart Styles-->
    <link href="/static/assets/js/morris/morris-0.4.3.min.css" rel="stylesheet" />
    <!-- Custom Styles-->
    <link href="/static/assets/css/custom-styles.css" rel="stylesheet" />
    <!-- Google Fonts-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
</head>

<body>
<div id="wrapper">
   <%@include file="haider.jsp"%>
    <!--/. NAV TOP  -->

   <%@include file="left.jsp"%>
    <!-- /. NAV SIDE  -->
    <div id="page-wrapper">
        <div class="panel panel-default">

            <div class="panel-heading">
                订单管理
                <c:if test="${not empty msg}">
                    <p style="color: red;text-align: center">${msg}</p>
                </c:if>
            </div>
            <div class="panel-body">
                <div class="table-responsive">
                    <h4 style="text-align: center;color: red">${message}</h4>
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>订单编号</th>
                            <th>金额</th>
                            <th>时间</th>
                            <th>收货人</th>
                            <th>电话</th>
                            <th>地址</th>
                            <th>订单状态</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${orders}" var="cate" >
                            <tr>
                                <td>${cate.oid}</td>
                                <td>${cate.total}</td>
                                <td>${cate.ordertime}</td>
                                <td>${cate.name}</td>
                                <td>${cate.phone}</td>
                                <td>${cate.addr}</td>
                                <c:if test="${cate.state == 2}">
                                    <td><button id="fahuo" class="btn-success" onclick="sendShop(this)" value="${cate.oid}" type="button">点击发货</button></td>
                                </c:if>
                                <c:if test="${cate.state == 1}">
                                    <td><button  class="btn-success"  value="${cate.oid}" type="button">未付款</button></td>
                                </c:if>
                                <c:if test="${cate.state == 3}">
                                    <td><button  class="btn-success"  value="${cate.oid}" type="button">已发货</button></td>
                                </c:if>
                                <c:if test="${cate.state == 4}">
                                    <td><button  class="btn-success"  value="${cate.oid}" type="button">交易完成</button></td>
                                </c:if>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

</div>
<!-- /. WRAPPER  -->
<!-- JS Scripts-->
<!-- jQuery Js -->
<script src="/static/assets/js/jquery-1.10.2.js"></script>
<!-- Bootstrap Js -->
<script src="/static/assets/js/bootstrap.min.js"></script>

<!-- Metis Menu Js -->
<script src="/static/assets/js/jquery.metisMenu.js"></script>
<!-- Morris Chart Js -->
<script src="/static/assets/js/morris/raphael-2.1.0.min.js"></script>
<script src="/static/assets/js/morris/morris.js"></script>


<script src="/static/assets/js/easypiechart.js"></script>
<script src="/static/assets/js/easypiechart-data.js"></script>


<!-- Custom Js -->
<script src="/static/assets/js/custom-scripts.js"></script>


</body>
    <script>
        function sendShop(oid) {
            window.location.href="/send/shop?oid="+oid.value;
        }
    </script>
</html>