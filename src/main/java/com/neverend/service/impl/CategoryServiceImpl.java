package com.neverend.service.impl;

import com.neverend.entity.Category;
import com.neverend.entity.CategoryExample;
import com.neverend.entity.Categorysecond;
import com.neverend.entity.CategorysecondExample;
import com.neverend.mapper.CategoryMapper;
import com.neverend.mapper.CategorysecondMapper;
import com.neverend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private  CategorysecondMapper categorysecondMapper;
    /**
     * 查询以及分类
     * @return
     */
    @Override
    public List<Category> selectCategory() {
        CategoryExample categoryExample = new CategoryExample();
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        return categoryList;
    }

    @Override
    public void del(Integer integer) {
        categoryMapper.deleteByPrimaryKey(integer);
    }

    @Override
    public List<Categorysecond> selectCategorys(String cid) {
        CategorysecondExample categorysecondExample = new CategorysecondExample();
        categorysecondExample.createCriteria().andCidEqualTo(Integer.valueOf(cid));
        return categorysecondMapper.selectByExample(categorysecondExample);
    }

    @Override
    public List<Category> selectCategorycid(String cid) {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andCidEqualTo(Integer.valueOf(cid));
        return categoryMapper.selectByExample(categoryExample);
    }

    @Override
    public void insertCaName(String caName) {
        Category category = new Category();
        category.setCname(caName);
        categoryMapper.insert(category);
    }

    @Override
    public Category selectCategoryCaname(String caName) {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andCnameEqualTo(caName);
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        if (!categoryList.isEmpty()){
            Category category = categoryList.get(0);
            return category;

        }
        return null;
    }

    @Override
    public Category selectCategoryCid(String cid) {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andCidEqualTo(Integer.valueOf(cid));
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        if (!categoryList.isEmpty()){
            Category category = categoryList.get(0);
            return category;

        }
        return null;
    }

    @Override
    public void updateCidCname(String name, Integer integer) {
        Category category = new Category();
        category.setCid(integer);
        category.setCname(name);
        categoryMapper.updateByPrimaryKey(category);
    }
}
