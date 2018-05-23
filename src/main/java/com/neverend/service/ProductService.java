package com.neverend.service;

import com.github.pagehelper.PageInfo;
import com.neverend.entity.Product;
import com.neverend.model.ProdoctModel;

import java.util.List;

public interface ProductService {

    List<Product> hotProduct(Integer hot);

    List<Product> newProduct();

    Product productOne(String pid);

    List<ProdoctModel> oneTwoProduct(String cid);

    PageInfo<Product> productOneCid(String cid, String page);

    PageInfo<Product> selectCsid(String casid, String page);

    Product selectpid(Integer pid);

    boolean delectPid(String pid);
}
