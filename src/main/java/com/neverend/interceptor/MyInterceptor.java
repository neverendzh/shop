package com.neverend.interceptor;

import com.neverend.entity.Adminuser;
import com.neverend.entity.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2018/2/26.
 */
public class MyInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取用户请求的路径
        String requestUrl = request.getRequestURI();

        //如果是静态路径，则放行
        if(requestUrl.startsWith("/static/")) {
            return true;
        }

        //如果是登录页面，则方行
        if("".equals(requestUrl) ||
                "/".equals(requestUrl)||
                "/login".equals(requestUrl)||
                "/zhuce".equals(requestUrl)||
                "/shop/car".equals(requestUrl)||
                "/seach/shop".equals(requestUrl)||
                "/product".equals(requestUrl)||
                "/category".equals(requestUrl)||
                "/categorysecond".equals(requestUrl)||
                "/admin".equals(requestUrl)||
                "/admin/shop".equals(requestUrl)) {
            return true;
        }

        //判断用户是否登录
        HttpSession httpSession = request.getSession();

        if ("/login".equals(requestUrl) || "/logout".equals(requestUrl)||"/ding/dan".equals(requestUrl)||"/shop/sub".equals(requestUrl)
                ||"/sel/DinDan/NotFu".equals(requestUrl)||"/del/ding/dan".equals(requestUrl)||"/order_payOrder".equals(requestUrl)
                ||"/success".equals(requestUrl)||
                "/queren/ding/dan".equals(requestUrl)){
            User account = (User) httpSession.getAttribute("user");
            if (account != null){
                return true;
            }else {
                response.sendRedirect("/login");
            }
        }

        if ("/shop/one".equals(requestUrl)||"admin/shopone".equals(requestUrl)||"/del".equals(requestUrl)||
                "/del/categorysecondid".equals(requestUrl)||
                "/and/one/shop".equals(requestUrl)||
                "/exit".equals(requestUrl)||
                "/shop/two".equals(requestUrl)||
                "/and/two/shop".equals(requestUrl)||
                "/del/categorysecondid/two".equals(requestUrl)||
                "/exit/two".equals(requestUrl)||
                "/edit/product".equals(requestUrl)||
                "/product/one/category".equals(requestUrl)||
                "/product/two/categorysend".equals(requestUrl)||
                "/product/two/views/categorysend".equals(requestUrl)||
                "/del/del/pid".equals(requestUrl)||
                "/shop/ding/dan/suc".equals(requestUrl)||
                "/send/shop".equals(requestUrl)||
                "/shop/ding/dan/no/us".equals(requestUrl)||
                "/shop/ding/dan/su/us".equals(requestUrl)||
                "/shop/ding/dan/us/no".equals(requestUrl)||
                "/admin/logout".equals(requestUrl)){
            Adminuser adminuser = (Adminuser) httpSession.getAttribute("admin");
            if (adminuser != null){
                return true;
            }else {
                response.sendRedirect("/admin");
            }
        }
        return false;
    }
}