package com.neverend.service.impl;

import com.neverend.entity.*;
import com.neverend.mapper.CategoryMapper;
import com.neverend.mapper.OrdersMapper;
import com.neverend.model.DindDanModel;
import com.neverend.service.OrdersService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Integer saveOrder(Orders orders) {
       Integer integer =  ordersMapper.insert(orders);
       integer = orders.getOid();
        return integer;
    }

    @Override
    public Orders select(Integer ordOid) {
        OrdersExample ordersExample = new OrdersExample();
        ordersExample.createCriteria().andOidEqualTo(ordOid);
        List<Orders> orders = ordersMapper.selectByExample(ordersExample);
        if (!orders.isEmpty()){
            return orders.get(0);
        }
        return null;
    }

    @Override
    public Orders findByOid(int i) {
        OrdersExample ordersExample = new OrdersExample();
        ordersExample.createCriteria().andOidEqualTo(i);
        List<Orders> orders = ordersMapper.selectByExample(ordersExample);
        return orders.get(0);
    }

    @Override
    public void update(Orders currOrder) {
        OrdersExample ordersExample = new OrdersExample();
        ordersMapper.updateByPrimaryKey(currOrder);
    }

    @Override
    public List<DindDanModel> selectOid(Integer oid,Integer uid) {
        List<DindDanModel> dindDanModels = ordersMapper.selectOid(oid,uid);
        return dindDanModels;
    }

    @Override
    public List<Orderitem> selectordtim(Integer oid) {
       return ordersMapper.selectordtim(oid);

    }

    @Override
    public void del(Integer integer) {
        OrdersExample ordersExample = new OrdersExample();
        ordersExample.createCriteria().andOidEqualTo(integer);
        ordersMapper.deleteByExample(ordersExample);
    }

    @Override
    public List<Category> selectAll() {
        CategoryExample categoryExample = new CategoryExample();
        List<Category> categoryList= categoryMapper.selectByExample(categoryExample);
        return categoryList;
    }

    public List<DindDanModel> selectState(Integer  uid) {
       List<DindDanModel> dindDanModels = ordersMapper.selectDingDan(uid);
       return dindDanModels;
    }

    public List<Orders> selectStatetwo(Integer  uid) {
        OrdersExample ordersExample = new OrdersExample();
        ordersExample.createCriteria().andStateEqualTo(uid);
        List<Orders> orders = ordersMapper.selectByExample(ordersExample);
        return orders;
    }

    public List<Orders> selectStateAll(int i) {
        OrdersExample ordersExample = new OrdersExample();
        ordersExample.createCriteria().andStateEqualTo(i);
        List<Orders> orders = ordersMapper.selectByExample(ordersExample);
        return orders;
    }
}
