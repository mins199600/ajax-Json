package com.boot.sailing.v3.dao;

import com.boot.sailing.v3.vo.Order_list;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDaoV2 {
    List<Order_list> doList();

    List<Order_list> doSearch(String strStartDate, String strEndDate, String strCoffee, String strName);
}
