package com.neverend.service;

import com.neverend.entity.Orderitem;
import com.neverend.entity.Orders;
import com.neverend.entity.Product;

import java.util.List;

public interface OrderitemService {
    Integer saveOrdertime(Orderitem orderitem);

    List<Orderitem> select(Integer oid);

    void del(Integer pid,Integer oid);

}
