<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>网上商城管理中心</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath }/css/general.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/main.css" rel="stylesheet" type="text/css" />

<style type="text/css">
body {
  color: white;
}
</style>

<body style="background: #3299CB">
<form method="post" action="${pageContext.request.contextPath }/admin/shop" target="_parent" name='theForm' onsubmit="return validate()">
  <c:if test="${not empty message}">
      <p style="text-align: center;color: red">${message}</p>
  </c:if>
                <div style="text-align: center;margin-top: 15%">
                  <tr>
                    <%--<td><img src="images/login.png" width="178" height="256" border="0" alt="SHOP" /></td>--%>
                    <td style="padding-left: 50px">
                      <tr>
                        <td>管理员姓名：</td>
                        <td><input type="text" name="username" /></td>
                      </tr>
                      <br/> <br/>
                      <tr>
                        <td>管理员密码：</td>
                        <td><input type="password" name="password" /></td>
                      </tr>
                        <br/><br/>
                      <tr>
                        <td>
                          <input style="margin-left: 12%" type="submit" name="act" value="登陆" />
                        </td>
                      </tr>
                    </td>
                  </tr>
                </div>
</form>

</body>