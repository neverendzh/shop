package com.neverend.service;

import com.neverend.entity.Category;
import com.neverend.entity.Categorysecond;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import java.util.List;

public interface CategoryService {

    List<Category> selectCategory();


    void del(Integer integer);

    List<Categorysecond> selectCategorys(String cid);

    List<Category> selectCategorycid(String cid);

    void insertCaName(String caName);

    Category selectCategoryCaname(String caName);

    Category selectCategoryCid(String cid);

    void updateCidCname(String name, Integer integer);
}
