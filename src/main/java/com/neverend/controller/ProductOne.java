package com.neverend.controller;

import com.github.pagehelper.PageInfo;
import com.neverend.entity.Category;
import com.neverend.entity.Product;
import com.neverend.model.ProdoctModel;
import com.neverend.service.impl.CategoryServiceImpl;
import com.neverend.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProductOne {
    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private ProductServiceImpl productService;
    @GetMapping("/product")
    public String productTextNoe(Model model, HttpServletRequest request){
        String pid = request.getParameter("pid");
        List<Category> categoryList = categoryService.selectCategory();
        Product product = productService.productOne(pid);
        List<ProdoctModel> prodoctModels = productService.oneTwoProduct("1");
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("prodoctModels",prodoctModels);
        model.addAttribute("product",product);
        return "user/productext";
    }


    @GetMapping("/category")
    public String product(Model model,HttpServletRequest request){
        String cid = request.getParameter("cid");
        String page = request.getParameter("page");
        List<Category> categoryList = categoryService.selectCategory();
        List<ProdoctModel> prodoctModels = productService.oneTwoProduct("1");
        PageInfo<Product> productList = productService.productOneCid(cid,page);
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("prodoctModels",prodoctModels);
        model.addAttribute("productList",productList);
        return "user/fenlie";
    }

    @GetMapping("/categorysecond")
    public String categorysecond(Model model,HttpServletRequest request){
        String casid = request.getParameter("casid");
        String page = request.getParameter("page");
        List<Category> categoryList = categoryService.selectCategory();
        List<ProdoctModel> prodoctModels = productService.oneTwoProduct("1");
        PageInfo<Product> productList = productService.selectCsid(casid,page);
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("prodoctModels",prodoctModels);
        model.addAttribute("productList",productList);
        return "user/fenlie";
    }
}
