package com.neverend.controller;

import com.neverend.entity.Category;
import com.neverend.entity.Product;
import com.neverend.entity.User;
import com.neverend.service.impl.CategoryServiceImpl;
import com.neverend.service.impl.ProductServiceImpl;
import com.neverend.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private ProductServiceImpl productService;
    @GetMapping("/")
    public String index(Model model){
        List<Product> productList = productService.hotProduct(1);
        List<Product> productList1 = productService.newProduct();
        List<Category> categoryList = categoryService.selectCategory();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("productList",productList);
        model.addAttribute("productList1",productList1);
        return "user/index";
    }

    @GetMapping("/login")
    public String loginGsp(Model model){
        List<Product> productList = productService.hotProduct(1);
        List<Product> productList1 = productService.newProduct();
        List<Category> categoryList = categoryService.selectCategory();
        model.addAttribute("productList",productList);
        model.addAttribute("productList1",productList1);
        model.addAttribute("categoryList",categoryList);
        return "user/userlogin";
    }

    @PostMapping("/login")
    public String login(User user, Model model, HttpSession session){
        List<Product> productList = productService.hotProduct(1);
        List<Product> productList1 = productService.newProduct();
        List<Category> categoryList = categoryService.selectCategory();
        model.addAttribute("categoryList",categoryList);
        User user1 = userService.login(user);
        if (user1 != null){
            session.setMaxInactiveInterval(1800);
            session.setAttribute("user",user1);
            model.addAttribute("productList",productList);
            model.addAttribute("productList1",productList1);
            return "user/index";
        }else {
            return "user/userlogin";
        }
    }

    @GetMapping("/logout")
    public String logout(User user, Model model, HttpSession session){
        List<Category> categoryList = categoryService.selectCategory();
        model.addAttribute("categoryList",categoryList);
            session.setMaxInactiveInterval(0);
            session.removeAttribute("user");
            return "user/index";
    }


    @GetMapping("/zhuce")
    public String zhuce(Model model){
        List<Category> categoryList = categoryService.selectCategory();
        model.addAttribute("categoryList",categoryList);
        return "user/zhuce";
    }

    @PostMapping("/zhuce")
    public String zhucesave(User user, Model model){
        List<Category> categoryList = categoryService.selectCategory();
        model.addAttribute("categoryList",categoryList);
        try {
            int uid = userService.saveUser(user);
            if (uid == 0){
                model.addAttribute("message","该用户已注册");
                return "user/zhuce";
            }
            return "user/userlogin";
        }catch (Exception e){
            return "error/500";
        }

    }
}
