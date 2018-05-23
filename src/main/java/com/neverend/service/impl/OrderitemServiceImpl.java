package com.neverend.service.impl;

import com.neverend.entity.Orderitem;
import com.neverend.entity.OrderitemExample;
import com.neverend.entity.Orders;
import com.neverend.mapper.OrderitemMapper;
import com.neverend.mapper.OrdersMapper;
import com.neverend.service.OrderitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderitemServiceImpl implements OrderitemService {
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrderitemMapper orderitemMapper;

    @Override
    public Integer saveOrdertime(Orderitem orderitem) {
        int orderitem1 =  orderitemMapper.insert(orderitem);
        return orderitem1;
    }

    @Override
    public List<Orderitem> select(Integer oid) {
        OrderitemExample orderitemExample = new OrderitemExample();
        orderitemExample.createCriteria().andOidEqualTo(oid);
        List<Orderitem> orderitems = orderitemMapper.selectByExample(orderitemExample);
        return orderitems;
    }

    @Override
    public void del(Integer pid,Integer oid) {
        Orderitem orderitem = orderitemMapper.selectPidOid(pid,oid);
        orderitemMapper.delectPidOid(pid,oid);
        Orders orders = ordersMapper.selectByPrimaryKey(oid);
        double newtotw = 0;
        if (orders != null){
           newtotw = orders.getTotal()-orderitem.getSubtotal();
           orders.setTotal(newtotw);
            ordersMapper.updateByPrimaryKey(orders);
        }
    }


}
