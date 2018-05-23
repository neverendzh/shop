<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/23 0023
  Time: 1:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside class="main-sidebar">
    <nav class="navbar-default navbar-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav" id="main-menu">
                <li>
                    <a href="#"><i class="fa fa-sitemap"></i> 用户管理<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="">编辑用户</a>
                        </li>
                    </ul>
                </li>
                <%--一二级分类管理--%>
                <li>
                    <a href="#"><i class="fa fa-sitemap"></i> 分类管理<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="/shop/one">一级分类</a>
                        </li>
                        <li>
                            <a href="/shop/two">二级分类</a>
                        </li>
                    </ul>
                </li>

                <li>
                    <a href="#"><i class="fa fa-sitemap"></i>商品管理<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="/edit/product">编辑商品</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#"><i class="fa fa-sitemap"></i> 订单管理<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="/shop/ding/dan/suc">未发货款订单</a>

                        </li>
                        <li>
                            <a href="/shop/ding/dan/no/us">已发货订单</a>
                        </li>
                        <li>
                            <a href="/shop/ding/dan/su/us">交易完成订单</a>
                        </li>
                        <li>
                            <a href="/shop/ding/dan/us/no">未付款订单</a>
                        </li>

                    </ul>
                </li>
            </ul>

        </div>
    </nav>
</aside>
