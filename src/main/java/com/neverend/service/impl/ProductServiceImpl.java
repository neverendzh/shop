package com.neverend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neverend.entity.Product;
import com.neverend.entity.ProductExample;
import com.neverend.mapper.CategoryMapper;
import com.neverend.mapper.ProductMapper;
import com.neverend.model.ProdoctModel;
import com.neverend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CategoryMapper categoryMapper;


    /**
     * 查询热门商品
     * @param hot
     * @return
     */
    @Override
    public List<Product> hotProduct(Integer hot) {
        ProductExample productExample = new ProductExample();
        productExample.createCriteria().andIsHotEqualTo(hot);
        productExample.setOrderByClause("pdate desc");
        List<Product> productList = productMapper.selectByExample(productExample);
        productList = productList.subList(0,10);
        return productList;
    }

    /**
     * 最新商品
     * @return
     */
    @Override
    public List<Product> newProduct() {
        ProductExample productExample = new ProductExample();
        productExample.setOrderByClause("pdate desc");
        List<Product> productList = productMapper.selectByExample(productExample);
        productList = productList.subList(0,10);
        return productList;
    }

    @Override
    public Product productOne(String pid) {
        ProductExample productExample = new ProductExample();
        Integer pid1 = Integer.valueOf(pid);
        productExample.createCriteria().andPidEqualTo(pid1);
        List<Product> productList = productMapper.selectByExample(productExample);
        Product product = productList.get(0);
        return product;
    }

    @Override
    public List<ProdoctModel> oneTwoProduct(String cid) {
          List<ProdoctModel> prodoctModels = categoryMapper.selectByCid(cid);
          return prodoctModels;
    }

    @Override
    public PageInfo<Product> productOneCid(String cid, String page) {
        try {
            PageHelper.startPage(Integer.valueOf(page),12);
            List<Product> productList = productMapper.selectCid(cid);
            return new PageInfo<>(productList);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public PageInfo<Product> selectCsid(String casid, String page) {
        try {
            PageHelper.startPage(Integer.valueOf(page),12);
            ProductExample productExample = new ProductExample();
            productExample.createCriteria().andCsidEqualTo(Integer.valueOf(casid));
            List<Product> productList = productMapper.selectByExample(productExample);
            return new PageInfo<>(productList);
        }catch (Exception e){
            return null;
        }
    }

    public PageInfo<Product> selectCsidtwo(String casid, String page) {
        try {
            PageHelper.startPage(Integer.valueOf(page),7);
            ProductExample productExample = new ProductExample();
            productExample.createCriteria().andCsidEqualTo(Integer.valueOf(casid));
            List<Product> productList = productMapper.selectByExample(productExample);
            return new PageInfo<>(productList);
        }catch (Exception e){
            return null;
        }
    }

    public List<Product> selectCsidAll(String casid) {
        try {
            ProductExample productExample = new ProductExample();
            productExample.createCriteria().andCsidEqualTo(Integer.valueOf(casid));
            List<Product> productList = productMapper.selectByExample(productExample);
            return productList;
        }catch (Exception e){
            return null;
        }
    }
    @Override
    public Product selectpid(Integer pid) {
        ProductExample productExample = new ProductExample();
        productExample.createCriteria().andPidEqualTo(pid);
        List<Product> productList = productMapper.selectByExample(productExample);
        if (!productList.isEmpty()){
            return productList.get(0);
        }else {
            return null;
        }
    }

    @Override
    public boolean delectPid(String pid) {
      Product product = productMapper.selectByPrimaryKey(Integer.valueOf(pid));
      if (product == null){
          return false;
      }else {
          Product product1 = new Product();
          product1.setPid(Integer.valueOf(pid));
          productMapper.updateByPrimaryKey(product1);
          productMapper.deleteByPrimaryKey(Integer.valueOf(pid));
          return true;

      }
    }

}
