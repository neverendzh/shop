<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8"%>
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
         <div style="margin-left: 8%">
            <label style="color: #2aabd2;font-size: 16px">选择编辑商品分类</label>
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <select id="ones" onchange="one()" name="quiz1">
                        <option value="">选择一级分类</option>
                        <c:forEach items="${categoryList}" var="categroy">
                            <option value="${categroy.cid}">${categroy.cname}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <select id="two" onchange="two()" name="quiz2">
                        <option value="">选择二级分类</option>
                    </select>
                </div>
         </div>
    </div>

</div>
<!-- /. WRAPPER  -->
<!-- JS Scripts-->
<!-- jQuery Js -->
<script src="/static/js/jquery.min.js"></script>
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
<%--<script src="/static/layui/layui.js" charset="utf-8"></script>--%>
<script src="/static/layer/layer.js"></script>

</body>
<script>
  function one(){
      var objS = document.getElementById("ones");
      var as = objS.value;
          $.ajax({
              type: "POST",
              url: "/product/one/category",
              data:{"type":as},
              dataType:"json",
              success: function(data){
                  $("#two").empty();
                  $("#two").prepend("<option value=\"\">选择二级分类</option>");
                  for (var i=0;i<data.length;i++){
                      var option = "<option value="+data[i].csid+'>'+data[i].csname+'</option>';
                      $("#two").append(option);
                  }
                  layer.msg("请选择二级分类");
              },
              error : function(){
                  layer.msg("服务异常稍后再试");
              }
          });
  };
  function two(){
      var objS = document.getElementById("two");
      var as = objS.value;
      $.ajax({
          type: "POST",
          url: "/product/two/categorysend",
          data:{"type":as,
                 "page":1},
          dataType:"json",
          success: function(data){
              // $("#two").empty();
              // $("#two").prepend("<option value=\"\">选择二级分类</option>");
              // for (var i=0;i<data.length;i++){
              //     var option = "<option value="+data[i].csid+'>'+data[i].csname+'</option>';
              //     $("#two").append(option);
              // }
              window.location.href="/product/two/views/categorysend?cid="+as+"&page="+1;
          },
          error : function(){
              layer.alert("服务异常稍后再试");
          }
      });
  };
</script>
</html>