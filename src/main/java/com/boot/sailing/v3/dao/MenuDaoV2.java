package com.boot.sailing.v3.dao;

import com.boot.sailing.v3.vo.Coffee_menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Map;

@Mapper
public interface MenuDaoV2 {

    List<Coffee_menu> doList();

    int doInsert(@ModelAttribute Coffee_menu coffeeMenu);

    int doDelete(String strNo);

    //one row 조회
    Map<String, Object> doListOne(String strNo);

    //업데이트
    int doUpdate(@ModelAttribute Coffee_menu coffeeMenu);

    //가격검색
    List<Coffee_menu> doSearch(String strStartDate, String strEndDate, String strCoffee, String strKind);

    //가격변경
    int doUpdatePrice(String strNo, String strPrice);
    //가격 변경 로그 입력
    int doInsertLog(String strNo, String strPrice);

    int doInsertLogOne(List<String> chkList, String strPrice);

    int doUpdatePriceOne(List<String> chkList, String strPrice);

    int doBootLog(String strClass);
}
