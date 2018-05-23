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
    <link href="/static/layui/css/layui.css" rel="stylesheet">
</head>

<body>
<div id="wrapper">
   <%@include file="haider.jsp"%>
    <!--/. NAV TOP  -->

   <%@include file="left.jsp"%>
    <!-- /. NAV SIDE  -->
    <div id="page-wrapper">
        <%-----------------------%>
        <div class="panel panel-default">

            <div class="panel-heading">
                二级分类管里
                <c:if test="${not empty andmsg}">
                    <h4 id="ceshi" onclick="c()" style="text-align: center;color: red;display: none">${andmsg}</h4>
                </c:if>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button onclick="a()" type="button" style="float: right;margin-right: 5%">添加</button>
            </div>
            <div class="panel-body">
                <div class="table-responsive">
                    <c:if test="${not empty message}">
                        <h4 id="ceshi" onclick="c()" style="text-align: center;color: red;display: none">${message}</h4>
                    </c:if>
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>分类名称</th>
                            <th>修改</th>
                            <th>删除</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${categoryseconds}" var="cates" >
                            <tr>
                                <td>${cates.csname}</td>
                                <td><button onclick="edit(${cates.csid},${cates.cid})" value="${cates}" type="button">修改</button></td>
                                <td><a href="/del/categorysecondid/two?categorysecondid=${cates.csid}&csteid=${cates.cid}">删除</a></td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <%----------------------%>
        <c:if test="${not empty productList}">
            <div class="panel panel-default">
                <div class="panel-heading">

                 一级分类---->${category.cname}----> 二级分类:${categorysecond.csname}----->商品

                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                            <%--<h4 style="text-align: center">${message}</h4>--%>
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>分类名称</th>
                                <th>删除</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${productList}" var="cate" >
                                <tr>
                                    <td>${cate.pname}</td>
                                    <td><a href="/del/pid?pid=${cate.pid}">删除</a></td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </c:if>
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
<script src="/static/layer/layer.js"></script>
<script src="/static/layui/layui.js"></script>

</body>

<script>
    function a(){
        layer.open({
            type: 1,
            skin: 'layui-layer-rim', //加上边框
            area: ['420px', '240px'], //宽高
            content: '  <form action="/and/two/shop" method="post">\n' +
            '                           <div style="margin-top: 0%" class="layui-form-item">\n' +
            '                               <div class="layui-form-item" style="float: right;margin-top: 5%;margin-left: 0px;margin-right: 3%;width 20px;padding: 0px">\n' +
            '                                       <select name="interest" lay-filter="aihao">\n' +
            '                                           <option value="">一级分类</option>\n' +
            '                                           <c:forEach items="${categoryList}" var="cals">\n' +
            '                                          <option value="${cals.cid}">${cals.cname}</option>\n' +
            '                                            </c:forEach>\n' +
            '                                       </select>\n' +
            '                               </div>\n' +
            '                                   <div class="layui-inline" style="float: left;margin-top: 5%">\n' +
            '                                           <label class="layui-form-label">名称</label>\n' +
            '                                           <div class="layui-input-inline">\n' +
            '                                               <input type="text" name="name" lay-verify="required|phone" autocomplete="off" class="layui-input">\n' +
            '                                           </div>\n' +
            '                                   </div>\n' +
            '                                   </div>\n' +
            '                            <button style="text-align: center;margin-left: 20%" class="layui-btn">提交</button>\n' +
            '                        </form>\n'
        });
    };

    function edit(t,b) {
        var csid = t;
        var cid = b;
        layer.open({
            type: 1,
            skin: 'layui-layer-rim', //加上边框
            area: ['420px', '240px'], //宽高
            content: '<form action="/exit/two" method="post">\n' +
            '            <input style="display: none" name="csid" value="'+csid+'">\n'+
            '            <input style="display: none" name="cid" value="'+cid+'">\n'+
            '            <div style="margin-top: 5%" class="layui-form-item">\n' +
            '                <div class="layui-inline">\n' +
            '                    <label class="layui-form-label">请输入修改后的名称</label>\n' +
            '                    <div class="layui-input-inline">\n' +
            '                     <input type="text" name="name" lay-verify="required|phone" autocomplete="off" class="layui-input">\n' +
            '                    </div>\n' +
            '                </div>\n' +
            '                </div>\n' +
            '            <button style="text-align: center;margin-left: 20%" class="layui-btn">提交</button>\n' +
            '        </form>'
        });
    };

    function c(text){
        layer.msg(text);
    };

    var oDiv = document.getElementById('ceshi');   //获取元素div
    oDiv.onclick = function(){   //给元素增加点击事件
        var  message = document.getElementById("ceshi").innerText;
       c(message);

    };
    oDiv.click();

</script>

</html>