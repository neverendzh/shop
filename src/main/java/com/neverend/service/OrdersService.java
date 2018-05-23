package com.neverend.service;

import com.neverend.entity.Category;
import com.neverend.entity.Orderitem;
import com.neverend.entity.Orders;
import com.neverend.model.DindDanModel;
import org.springframework.core.annotation.Order;

import java.util.List;

public interface OrdersService {
    Integer saveOrder(Orders orders);

    Orders select(Integer ordOid);

    Orders findByOid(int i);

    void update(Orders currOrder);

    List<DindDanModel> selectOid(Integer oid,Integer uid);

    List<Orderitem> selectordtim(Integer integer);

    void del(Integer integer);

    List<Category> selectAll();
}
