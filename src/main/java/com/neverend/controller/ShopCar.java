package com.neverend.controller;

import com.neverend.entity.*;
import com.neverend.model.Cart;
import com.neverend.model.CartItem;
import com.neverend.model.DindDanModel;
import com.neverend.service.impl.CategoryServiceImpl;
import com.neverend.service.impl.OrderitemServiceImpl;
import com.neverend.service.impl.OrdersServiceImpl;
import com.neverend.service.impl.ProductServiceImpl;
import com.neverend.util.PaymentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
public class ShopCar {
    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private OrdersServiceImpl ordersService;
    @Autowired
    private OrderitemServiceImpl orderitemService;
    @Autowired
    private ProductServiceImpl productService;
    @PostMapping("/shop/car")
    public String shopCarJsp(HttpServletRequest request, Model model, HttpSession session){
        Cart cart = (Cart) session.getAttribute("mapCart");
        String praductId = request.getParameter("praductId");
        String total = request.getParameter("count");
        Product product = productService.productOne(praductId);

        CartItem cartItem = new CartItem();
        cartItem.setCount(Integer.valueOf(total));
        cartItem.setProduct(product);

        if (cart == null){
            cart = new Cart();
            cart.addCart(cartItem);
        }else {
            cart.addCart(cartItem);
        }
        session.setMaxInactiveInterval(1800);

        Collection<CartItem> cartItems = cart.getCartItems();
        for (CartItem c:cartItems
             ) {
            System.out.println(c.getProduct().toString());
        }
        System.out.println(cart.getCartItems());
        session.setAttribute("mapCart",cart);

        List<Category> categoryList = categoryService.selectCategory();
        model.addAttribute("categoryList",categoryList);
        return "user/shopcar";
    }


    @GetMapping("/seach/shop")
    public String search(HttpSession session,Model model){
        Cart cart = (Cart) session.getAttribute("mapCart");
        session.setAttribute("mapCart",cart);
        List<Category> categoryList = categoryService.selectCategory();
        model.addAttribute("categoryList",categoryList);
        return "user/shopcar";
    }


    @GetMapping("/del/shop/all")
    public String delall(HttpSession session,Model model){
        List<Category> categoryList = categoryService.selectCategory();
        model.addAttribute("categoryList",categoryList);
        Cart cart = (Cart) session.getAttribute("mapCart");
        if (cart == null){
            return "user/index";
        }else {
            session.setAttribute("mapCart",null);
        }
        return "user/shopcar";
    }

    @GetMapping("/del/shop/one")
    public String delone(HttpSession session,Model model,HttpServletRequest request){
        String pid =  request.getParameter("pid");
        List<Category> categoryList = categoryService.selectCategory();
        model.addAttribute("categoryList",categoryList);
        Cart cart = (Cart) session.getAttribute("mapCart");
        if (cart == null){
            return "user/index";
        }else {
            cart.removeCart(Integer.valueOf(pid));
            session.setAttribute("mapCart",cart);
        }
        return "user/shopcar";
    }

    @GetMapping("/shop/sub")
    public String shopSub(HttpSession session,Model model){
        User user = (User) session.getAttribute("user");
        Cart cart = (Cart) session.getAttribute("mapCart");
        Orders orders = new Orders();
        orders.setState(1);
        orders.setOrdertime(new Date());
        orders.setAddr(user.getAddr());
        orders.setTotal(cart.getTotal());
        orders.setName(user.getName());
        orders.setUid(user.getUid());
        Integer oid = ordersService.saveOrder(orders);
        Collection<CartItem> cartItems = cart.getCartItems();
        Integer ordOid = null;
        for (CartItem c:cartItems) {
            Orderitem orderitem =new Orderitem();
            orderitem.setCount(c.getCount());
            orderitem.setSubtotal(c.getSubtotal());
            orderitem.setPid(c.getProduct().getPid());
            orderitem.setOid(oid);
             ordOid = orderitemService.saveOrdertime(orderitem);
        }
        Orders orders1 = ordersService.select(oid);
        List<Orderitem> orderitems = orderitemService.select(orders1.getOid());
        List<Product> productList = new ArrayList<>();
        for (Orderitem o: orderitems) {
            Product product = productService.selectpid(o.getPid());
            if (product != null){
                productList.add(product);
            }
        }
        Cart cart1 = new Cart();
        for (Product p: productList) {
            CartItem cartItem =  new CartItem();
            cartItem.setProduct(p);
            for (Orderitem o: orderitems) {
                if (o.getPid()== p.getPid()){
                    cartItem.setCount(o.getCount());
                    cart1.addCart(cartItem);
                }
            }
        }
        session.setAttribute("mapCart",null);
        model.addAttribute("mapCart",cart1);
        model.addAttribute("orders1",orders1);
        model.addAttribute("ordtime",orderitems);
        model.addAttribute("user",user);
        List<Category> categoryList = categoryService.selectCategory();
        model.addAttribute("categoryList",categoryList);
        return "user/fukuan";
    }

    @GetMapping("/sel/DinDan/NotFu")
    public String selDinDanNotFu(HttpServletRequest request,Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        String oid = request.getParameter("oid");
        Integer uid = user.getUid();
        List<DindDanModel> dindDanModels = ordersService.selectOid(Integer.valueOf(oid),uid);
        double countmoney = 0;
        if (!dindDanModels.isEmpty()){
            countmoney = dindDanModels.get(0).getTotal();
        }
        List<Category> categoryList = categoryService.selectCategory();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("dindDanModels",dindDanModels);
        model.addAttribute("countmoney",countmoney);
        model.addAttribute("user",user);
        model.addAttribute("oid",oid);
        return "user/fukuanone";
    }


    @GetMapping("/ding/dan")
    public String dindan(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        List<DindDanModel> oids = ordersService.selectState(Integer.valueOf(user.getUid()));
        List<Category> categoryList = categoryService.selectCategory();
        double countAll = 0;
        for (DindDanModel d:oids) {
            if (d.getState() == 1){
                countAll = countAll+ d.getShopPrice();
            }
            System.out.println(d);
        }
        System.out.println(Arrays.toString(oids.toArray()));
        model.addAttribute("count",countAll);
        model.addAttribute("DindDanModel",oids);
        model.addAttribute("categoryList",categoryList);
        return "user/order";
    }


    @GetMapping("/del/ding/dan")
    public String delDingDan(Model model,HttpServletRequest request,HttpSession session){
        String pid = request.getParameter("pid");
        String oid = request.getParameter("oid");
        orderitemService.del(Integer.valueOf(pid),Integer.valueOf(oid));
        List<Orderitem> orderitemss =  ordersService.selectordtim(Integer.valueOf(oid));
        if (orderitemss.isEmpty() ||  orderitemss.size() == 0){
                ordersService.del(Integer.valueOf(oid));
        }

        User user = (User) session.getAttribute("user");
        List<DindDanModel> oids = ordersService.selectState(Integer.valueOf(user.getUid()));
        List<Category> categoryList = categoryService.selectCategory();
        double countAll = 0;
        for (DindDanModel d:oids) {
            if (d.getState() == 1){
                countAll = countAll+ d.getTotal();
            }
            System.out.println(d);
        }

        model.addAttribute("count",countAll);
        model.addAttribute("DindDanModel",oids);
        model.addAttribute("categoryList",categoryList);
        return "user/order";
    }

    @PostMapping("/order_payOrder")
    public void order_payOrder(HttpServletRequest request, HttpServletResponse response){
        String orderoid = request.getParameter("orderID");
        String orderaddr = request.getParameter("order.user.addr");
        String orderusername = request.getParameter("order.user.username");
        String orderphone = request.getParameter("order.user.phone");
        String total = request.getParameter("total");
//        支付通道，银行
        String pd_FrpIds = "ABC-NET-B2C";
        Orders orders = ordersService.select(Integer.valueOf(orderoid));
        if (total.equals(orders.getTotal().toString())){
            // 付款需要的参数:
        String p0_Cmd = "Buy"; // 业务类型:
        String p1_MerId = "10001126856";// 商户编号:
        String p2_Order = orders.getOid().toString();//order.getOid().toString();// 订单编号:
        String p3_Amt = "0.01"; // 付款金额:
        String p4_Cur = "CNY"; // 交易币种:
        String p5_Pid = ""; // 商品名称:
        String p6_Pcat = ""; // 商品种类:
        String p7_Pdesc = ""; // 商品描述:
        String p8_Url = "http://127.0.0.1:80/success"; // 商户接收支付成功数据的地址:
        String p9_SAF = ""; // 送货地址:
        String pa_MP = ""; // 商户扩展信息:
        String pd_FrpId = pd_FrpIds;// 支付通道编码:
        String pr_NeedResponse = "1"; // 应答机制:
        String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // 秘钥
        String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
                p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
                pd_FrpId, pr_NeedResponse, keyValue); // hmac
        // 向易宝发送请求:
        StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
        sb.append("p0_Cmd=").append(p0_Cmd).append("&");
        sb.append("p1_MerId=").append(p1_MerId).append("&");
        sb.append("p2_Order=").append(p2_Order).append("&");
        sb.append("p3_Amt=").append(p3_Amt).append("&");
        sb.append("p4_Cur=").append(p4_Cur).append("&");
        sb.append("p5_Pid=").append(p5_Pid).append("&");
        sb.append("p6_Pcat=").append(p6_Pcat).append("&");
        sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
        sb.append("p8_Url=").append(p8_Url).append("&");
        sb.append("p9_SAF=").append(p9_SAF).append("&");
        sb.append("pa_MP=").append(pa_MP).append("&");
        sb.append("pd_FrpId=").append(pd_FrpId).append("&");
        sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
        sb.append("hmac=").append(hmac);

        // 重定向:向易宝出发:
        try {
            response.sendRedirect(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    }



    @RequestMapping("/success")
    public String success(HttpServletRequest request,Model model){
//        支付结果
        String r1_Code = request.getParameter("r1_Code");

//      支付金额  r3_Amt
        String r3_Amt = request.getParameter("r3_Amt");
//      商户订单号   r6_Order
        String r6_Order = request.getParameter("r6_Order");
        // 修改订单的状态:
        Orders currOrder = ordersService.findByOid(Integer.parseInt(r6_Order));
        System.out.println(r1_Code+"--"+r3_Amt+"--"+r6_Order+"--");
        // 修改订单状态为2:已经付款:
        currOrder.setState(2);
        ordersService.update(currOrder);
        model.addAttribute( "msg","支付成功!订单编号为: "+r6_Order +" 付款金额为: "+currOrder.getTotal());
        return "user/msg";
    }


    @GetMapping("/queren/ding/dan")
    public String querendingdan(HttpServletRequest request,Model model,HttpSession session){
       String oid =  request.getParameter("pid");
        Orders orders = ordersService.select(Integer.valueOf(oid));
        if (orders == null){
            model.addAttribute("msg","参数错误");
        }else {
            Integer type = orders.getState();
            if (type == 3){
                orders.setState(4);
                ordersService.update(orders);
            }else {
                model.addAttribute("msg","该订单确认收货");
            }
        }
        User user = (User) session.getAttribute("user");
        List<DindDanModel> oids = ordersService.selectState(Integer.valueOf(user.getUid()));
        List<Category> categoryList = categoryService.selectCategory();
        double countAll = 0;
        for (DindDanModel d:oids) {
            if (d.getState() == 1){
                countAll = countAll+ d.getShopPrice();
            }
            System.out.println(d);
        }
        System.out.println(Arrays.toString(oids.toArray()));
        model.addAttribute("count",countAll);
        model.addAttribute("DindDanModel",oids);
        model.addAttribute("categoryList",categoryList);
        return "user/order";
    }
}
