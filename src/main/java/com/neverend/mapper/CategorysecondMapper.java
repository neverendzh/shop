package com.neverend.mapper;

import com.neverend.entity.Categorysecond;
import com.neverend.entity.CategorysecondExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CategorysecondMapper {
    long countByExample(CategorysecondExample example);

    int deleteByExample(CategorysecondExample example);

    int deleteByPrimaryKey(Integer csid);

    int insert(Categorysecond record);

    int insertSelective(Categorysecond record);

    List<Categorysecond> selectByExample(CategorysecondExample example);

    Categorysecond selectByPrimaryKey(Integer csid);

    int updateByExampleSelective(@Param("record") Categorysecond record, @Param("example") CategorysecondExample example);

    int updateByExample(@Param("record") Categorysecond record, @Param("example") CategorysecondExample example);

    int updateByPrimaryKeySelective(Categorysecond record);

    int updateByPrimaryKey(Categorysecond record);
}