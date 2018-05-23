<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0048)http://localhost:8080/mango/product/list/1.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>传智网上商城</title>
    <link href="/static/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="/static/css/product.css" rel="stylesheet" type="text/css"/>
    <%--<link rel="stylesheet" href="/static/css/bootstrap.min.css">--%>
</head>
<
<style>
    #pagination-demo li{
        float: left;
        font-size: 14px;
        padding: 2px;
        text-align: center;
        background-color: rgba(31, 214, 214, 0.35);
    }
</style>
<body>
<%@include file="../include/top.jsp"%>
<div class="container productList">
    <div class="span6" style="float: left">
        <div class="hotProductCategory">
            <c:forEach items="${prodoctModels}" var="productre">
                <dl>
                    <dt>
                        <a href="/category?cid=${productre.cid}&page=1">${productre.cname}</a>
                    </dt>
                    <c:forEach items="${productre.categoryseconds}" var="cate">
                        <dd>
                            <a href="/categorysecond?casid=${cate.csid}&page=1">${cate.csname}</a>
                        </dd>
                    </c:forEach>
                </dl>
            </c:forEach>
        </div>
    </div>
    <div class="span18 last" style="float: right;top: 10px">

        <form id="productForm" action="./image/蔬菜 - Powered By Mango Team.htm" method="get">
            <input type="hidden" id="brandId" name="brandId" value="">
            <input type="hidden" id="promotionId" name="promotionId" value="">
            <input type="hidden" id="orderType" name="orderType" value="">
            <input type="hidden" id="pageNumber" name="pageNumber" value="1">
            <input type="hidden" id="pageSize" name="pageSize" value="20">

            <div id="result"  class="result table clearfix">
                <ul>
                    <c:forEach items="${productList.list}" var="pros">
                        <li>
                            <a href="/product?pid=${pros.pid}">
                                <img src="${pageContext.request.contextPath}/static/${pros.image}" width="170" height="170"  style="display: inline-block;">

                                <span style='color:green'>
											 ${pros.pname}
											</span>

                                <span class="price">
												商城价： ￥${pros.shopPrice}/份
											</span>

                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>

            <ul id="pagination-demo" class="pagination-sm"></ul>
            <%--<div class="pagination">--%>
                <%--<span class="firstPage">&nbsp;</span>--%>
                <%--<span class="previousPage">&nbsp;</span>--%>
                <%--<span class="currentPage">1</span>--%>
                <%--<a href="javascript: $.pageSkip(2);">2</a>--%>
                <%--<a class="nextPage" href="javascript: $.pageSkip(2);">&nbsp;</a>--%>

                <%--<a class="lastPage" href="javascript: $.pageSkip(2);">&nbsp;</a>--%>
            <%--</div>--%>
        </form>
    </div>
</div>

<%@include file="../include/footer.jsp"%>
<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/jquery.twbsPagination.js"></script>
</body>
<script>
    $(function(){
        //分页
        $('#pagination-demo').twbsPagination({
            totalPages: ${productList.pages},
            visiblePages: 3,
            first:'首页',
            last:'末页',
            prev:'上一页',
            next:'下一页',
            href:"?cid="+encodeURIComponent('${param.cid}')+"&page={{number}}"
        });
    });
</script>
</html>
