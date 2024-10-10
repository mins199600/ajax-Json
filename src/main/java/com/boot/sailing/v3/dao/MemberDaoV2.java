package com.boot.sailing.v3.dao;

import com.boot.sailing.v3.vo.Cust_info;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberDaoV2 {
    List<Cust_info> doList();
    List<Cust_info> doSearch(String strStartDate, String strEndDate, String strName);
}
