package com.neverend.mapper;

import com.neverend.entity.Orderitem;
import com.neverend.entity.Orders;
import com.neverend.entity.OrdersExample;
import java.util.List;

import com.neverend.model.DindDanModel;
import org.apache.ibatis.annotations.Param;

public interface OrdersMapper {
    long countByExample(OrdersExample example);

    int deleteByExample(OrdersExample example);

    int deleteByPrimaryKey(Integer oid);

    int insert(Orders record);

    int insertSelective(Orders record);

    List<Orders> selectByExample(OrdersExample example);

    Orders selectByPrimaryKey(Integer oid);

    int updateByExampleSelective(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByExample(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    List<DindDanModel> selectDingDan(@Param("uid") Integer uid);

    List<DindDanModel> selectOid(@Param("oid") Integer oid,@Param("uid") Integer uid);

    List<Orderitem> selectordtim(@Param("oid") Integer oid);
}