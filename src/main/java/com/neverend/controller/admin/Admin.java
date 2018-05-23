package com.neverend.controller.admin;

import com.github.pagehelper.PageInfo;
import com.neverend.entity.*;
import com.neverend.mapper.AdminuserMapper;
import com.neverend.model.DindDanModel;
import com.neverend.service.impl.CategoryServiceImpl;
import com.neverend.service.impl.CategorysecondServiceImpl;
import com.neverend.service.impl.OrdersServiceImpl;
import com.neverend.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class Admin{

    @Autowired
    private OrdersServiceImpl ordersService;
    @Autowired
    private AdminuserMapper adminuserMapper;
    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private CategorysecondServiceImpl categorysecondService;
    @Autowired
    private CategoryServiceImpl categoryServicel;
    @Autowired
    private ProductServiceImpl productService;
    @GetMapping("/admin")
    public String indexAdmin(){
     return "admin/index";
    }

    @PostMapping("/admin/shop")
    public String adminlogin(Adminuser adminuser, Model model, HttpSession session){
        AdminuserExample adminuserExample = new AdminuserExample();
        adminuserExample.createCriteria().andUsernameEqualTo(adminuser.getUsername());
        List<Adminuser> adminusers = adminuserMapper.selectByExample(adminuserExample);
        if (!adminusers.isEmpty()){
            Adminuser adminuser1 = adminusers.get(0);
            if (adminuser1.getPassword().equals(adminuser.getPassword())){
                session.setAttribute("admin",adminuser1);
                return "admin/home";
            }else {
                model.addAttribute("message","账号密码错误");
                return "admin/index";
            }
        }
        model.addAttribute("message","账号密码错误");
        return "admin/index";
    }


    @GetMapping("/admin/shop")
    public String adminlogi(Adminuser adminuser, Model model, HttpSession session){
        return "admin/home";
    }

    @GetMapping("/shop/one")
    public String onere(Adminuser adminuser, Model model, HttpSession session) {
        List<Category> categoryList = ordersService.selectAll();
        model.addAttribute("categoryList",categoryList);
        return "admin/shopone";
    }


    @GetMapping("/del")
    public String delShopOne(Adminuser adminuser, Model model, HttpSession session, HttpServletRequest request) {
        String cid = request.getParameter("cateid");
        List<Categorysecond> categoryseconds  = categoryServicel.selectCategorys(cid);
        if (categoryseconds.isEmpty()){
            categoryService.del(Integer.valueOf(cid));
            model.addAttribute("message","删除成功");
        }else {
            model.addAttribute("message","请先删除一级分类下的二级分类");
            model.addAttribute("categoryseconds",categoryseconds);
            List<Category> categoryList = categoryService.selectCategorycid(cid);
            Category category = categoryList.get(0);
            model.addAttribute("category",category);
        }
        List<Category> categoryList = ordersService.selectAll();
        model.addAttribute("categoryList",categoryList);

        return "admin/shopone";
    }


    @GetMapping("/del/categorysecondid")
    public String delcsds(Adminuser adminuser, Model model, HttpSession session, HttpServletRequest request) {
        String cids = request.getParameter("categorysecondid");
        String cid = request.getParameter("csteid");
        List<Categorysecond> categoryseconds  = categoryServicel.selectCategorys(cid);
        if (!categoryseconds.isEmpty()){
            List<Product> productList = productService.selectCsidAll(cids);
            if (productList.isEmpty()){
                categorysecondService.delcateys(Integer.valueOf(cids));
            }else {
                model.addAttribute("message","请先删除二级分类下的商品");
                model.addAttribute("categoryseconds",categoryseconds);
                List<Category> categoryList = categoryService.selectCategorycid(cid);
                Categorysecond categorysecond = categorysecondService.selectCays(cids);
                model.addAttribute("categorysecond",categorysecond);
                Category category = categoryList.get(0);
                model.addAttribute("category",category);
                model.addAttribute("productList",productList);
            }
        }
        List<Category> categoryList = ordersService.selectAll();
        model.addAttribute("categoryList",categoryList);

        return "admin/shopone";
    }


    @GetMapping("/del/categorysecondid/two")
    public String delcsdstwo(Adminuser adminuser, Model model, HttpSession session, HttpServletRequest request) {
        String cids = request.getParameter("categorysecondid");
        String cid = request.getParameter("csteid");
        List<Categorysecond> categoryseconds  = categoryServicel.selectCategorys(cid);
        if (!categoryseconds.isEmpty()){
            List<Product> productList = productService.selectCsidAll(cids);
            if (productList.isEmpty()){
                categorysecondService.delcateys(Integer.valueOf(cids));
                model.addAttribute("message","删除成功");
                 categoryseconds =  categorysecondService.selectCateSencondAll();
                model.addAttribute("categoryseconds",categoryseconds);
            }else {
                model.addAttribute("message","请先删除二级分类下的商品");
                categoryseconds =  categorysecondService.selectCateSencondAll();
                model.addAttribute("categoryseconds",categoryseconds);
                List<Category> categoryList = categoryService.selectCategorycid(cid);
                Categorysecond categorysecond = categorysecondService.selectCays(cids);
                model.addAttribute("categorysecond",categorysecond);
                Category category = categoryList.get(0);
                model.addAttribute("category",category);
                model.addAttribute("productList",productList);
            }
        }

        List<Category> categoryList = categoryService.selectCategory();
        model.addAttribute("categoryList",categoryList);
        return "admin/shoptwo";
    }


    @PostMapping("/and/one/shop")
    public String AndNoeShop(Model model,HttpServletRequest request){
        String caName = request.getParameter("name");
        Category category = categoryService.selectCategoryCaname(caName);
        if (category == null){
            categoryService.insertCaName(caName);
        }else {
            model.addAttribute("andmsg","该一级分类已添加");
        }
            List<Category> categoryList = ordersService.selectAll();
            model.addAttribute("categoryList",categoryList);
            return "admin/shopone";
    }




    @PostMapping("/exit")
    public String exitOne(Adminuser adminuser, Model model, HttpSession session, HttpServletRequest request) {
        String cid = request.getParameter("cateid");
        String name = request.getParameter("name");
        Category category = categoryService.selectCategoryCid(cid);
        if (category != null){
            if (category.getCname().equals(name)){
                model.addAttribute("andmsg","无任何修改");
            }else {
                categoryService.updateCidCname(name,Integer.valueOf(cid));
                model.addAttribute("andmsg","修改成功");
            }
        }else {
            model.addAttribute("andmsg","参数异常");
        }
        List<Category> categoryList = ordersService.selectAll();
        model.addAttribute("categoryList",categoryList);
        return "admin/shopone";
    }

    @PostMapping("/exit/two")
    public String exittwo(HttpServletRequest request,Model model){
        String csid = request.getParameter("csid");
        String name = request.getParameter("name");
        String cid = request.getParameter("cid");
        List<Categorysecond> categoryseconds = null;
        Categorysecond categorysecond = categorysecondService.selectCsid(csid);
        if (categorysecond != null){
            if (categorysecond.getCsname().equals(name)){
                model.addAttribute("andmsg","请输入新的名称");
            }else {
                categorysecondService.updateCateSend(csid,name,cid);
                model.addAttribute("andmsg","修改成功");
            }
            categoryseconds = categorysecondService.selectCateSencondAll();
            model.addAttribute("categoryseconds",categoryseconds);
        }else {
            categoryseconds = categorysecondService.selectCateSencondAll();
            model.addAttribute("categoryseconds",categoryseconds);
            model.addAttribute("andmsg","参数异常，刷新页面重试");
        }

        List<Category> categoryList = categoryService.selectCategory();
        model.addAttribute("categoryList",categoryList);
        return "admin/shoptwo";
    }

    @GetMapping("/shop/two")
    public String deitTwo(Model model){
       List<Category> categoryList = categoryService.selectCategory();
       List<Categorysecond> categoryseconds =  categorysecondService.selectCateSencondAll();
       model.addAttribute("categoryseconds",categoryseconds);
       model.addAttribute("categoryList",categoryList);
        return "admin/shoptwo";
    }

    @PostMapping("/and/two/shop")
    public String andshoptwo(Model model,HttpServletRequest request){

        String name = request.getParameter("name");
        String cid = request.getParameter("interest");
       Categorysecond categorysecond =  categorysecondService.selectCaysName(name);
       if (categorysecond == null){
           categorysecondService.saveCateSend(name,Integer.valueOf(cid));
           model.addAttribute("andmsg","添加成功");
       }else {
           model.addAttribute("andmsg","该二级分类已存在");
       }



        List<Category> categoryList = categoryService.selectCategory();
        model.addAttribute("categoryList",categoryList);
        List<Categorysecond> categoryseconds =  categorysecondService.selectCateSencondAll();
        model.addAttribute("categoryseconds",categoryseconds);
        return "admin/shoptwo";
    }

    @GetMapping("/edit/product")
    public String editproduct(Model model,HttpServletRequest request){
       List<Category> categoryList =  categoryService.selectCategory();
       model.addAttribute("categoryList",categoryList);
        return "admin/product";
    }

    @ResponseBody
    @RequestMapping("/product/one/category")
    public Object editProductCategoryAll(HttpServletRequest request,Model model){
        List<Category> categoryList =  categoryService.selectCategory();
        model.addAttribute("categoryList",categoryList);
        String typeid = request.getParameter("type");
        List<Categorysecond> categoryseconds = categorysecondService.selectCaySecondAllCaID(Integer.valueOf(typeid));
        return categoryseconds;
    }


    @ResponseBody
    @RequestMapping("/product/two/categorysend")
    public Object editProductCategorysAllTwo(HttpServletRequest request,Model model){
        String typeidcsdi = request.getParameter("type");
        String page = request.getParameter("page");
        List<Category> categoryList =  categoryService.selectCategory();
        model.addAttribute("categoryList",categoryList);
        PageInfo<Product> PageInfo = productService.selectCsid(typeidcsdi,page);
        return PageInfo;
    }

    @GetMapping("/product/two/views/categorysend")
    public Object editProductviewsCategorysAllTwo(HttpServletRequest request,Model model){
        String typeidcsdi = request.getParameter("cid");
        String page = request.getParameter("page");
        PageInfo<Product> PageInfo = productService.selectCsidtwo(typeidcsdi,page);
        model.addAttribute("pageinf",PageInfo);
        List<Categorysecond> categoryseconds = categorysecondService.selectCateSencondAll();
        model.addAttribute("vcategoryseconds",categoryseconds);
        return "admin/producttwov";
    }


    @GetMapping("/del/del/pid")
    public String delproductpid(Model model,HttpServletRequest request){
        String pid = request.getParameter("pid");
        boolean b = productService.delectPid(pid);
        if (b){
            model.addAttribute("msg","删除成功");
        }else {
            model.addAttribute("msg","参数错误");
        }
        String typeidcsdi = request.getParameter("cid");
        String page = request.getParameter("page");
        PageInfo<Product> PageInfo = productService.selectCsidtwo(typeidcsdi,page);
        model.addAttribute("pageinf",PageInfo);
        return "admin/producttwov";
    }




    @GetMapping("/shop/ding/dan/suc")
    public String shopDingDan(Model model){
         List<Orders> orders =  ordersService.selectStateAll(2);
         model.addAttribute("orders",orders);
         return "admin/dingdan";
    }


    @GetMapping("/send/shop")
    public String sendShop(Model model,HttpServletRequest request){
        String odi = request.getParameter("oid");
        Orders orders = ordersService.select(Integer.valueOf(odi));
        if (orders == null){
            model.addAttribute("msg","参数错误");
        }else {
            Integer type = orders.getState();
            if (type == 2){
                orders.setState(3);
                ordersService.update(orders);
            }else {
                model.addAttribute("msg","该订单已发货");
            }
        }
        List<Orders> ordersss =  ordersService.selectStateAll(2);
        model.addAttribute("orders",ordersss);
        return "admin/dingdan";
    }
    @GetMapping("/shop/ding/dan/no/us")
    public String sendShopNoUs(Model model,HttpServletRequest request){
        List<Orders> ordersss =  ordersService.selectStateAll(3);
        model.addAttribute("orders",ordersss);
        return "admin/dingdan";
    }

    @GetMapping("/shop/ding/dan/su/us")
    public String sendShopdanSuUs(Model model,HttpServletRequest request){
        List<Orders> ordersss =  ordersService.selectStateAll(4);
        model.addAttribute("orders",ordersss);
        return "admin/dingdan";
    }

    @GetMapping("/shop/ding/dan/us/no")
    public String sendShopUsNo(Model model,HttpServletRequest request){
        List<Orders> ordersss =  ordersService.selectStateAll(1);
        model.addAttribute("orders",ordersss);
        return "admin/dingdan";
    }


    @GetMapping("/admin/logout")
    public String logOut(HttpSession session,Model model){
        session.removeAttribute("admin");
        return "admin/index";
    }
}
