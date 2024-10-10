package com.boot.sailing.v3.service;

import com.boot.sailing.comm.Bootlog;
import com.boot.sailing.v3.dao.MenuDaoV2;
import com.boot.sailing.v3.vo.Coffee_menu;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class MenuSvcV2 {

    @Autowired
    MenuDaoV2 menuDaoV2;
    @Autowired
    private Bootlog bootlog;
    @Autowired
    PlatformTransactionManager transactionManager;
    @Autowired
    TransactionDefinition definition;

    public MenuSvcV2() {
        log.info("===== MenuSvc , V2 =====");
    }

    public List<Coffee_menu> doList() {
        List<Coffee_menu> list = menuDaoV2.doList();
        log.info(list);
        return list;
    }

    public int doInsert(@ModelAttribute Coffee_menu coffeeMenu) {
        int i = menuDaoV2.doInsert(coffeeMenu);
        return i;
    }

    public int doDelete(String strNo) {
        int i = menuDaoV2.doDelete(strNo);
        return i;
    }
    //one row 조회
    public Map<String, Object> doListOne(String strNo) {

        Map<String, Object> map = menuDaoV2.doListOne(strNo);

        return map;
    }

    //수정
    public int doUpdate(@ModelAttribute Coffee_menu coffeeMenu) {
        int i = menuDaoV2.doUpdate(coffeeMenu);
        return i;
    }


    public List<Coffee_menu> doSearch(String strStartDate, String strEndDate, String strCoffee, String strKind) {

        List<Coffee_menu> list = menuDaoV2.doSearch(strStartDate, strEndDate, strCoffee, strKind);
        return list;
    }

    public int doUpdatePrice(String strNo, String strPrice) {
        int int2 = menuDaoV2.doUpdatePrice(strNo, strPrice);
        return int2;
    }
    //가격변경 로그
    public int doInsertLog(String strNo, String strPrice) {
        int int1 = menuDaoV2.doInsertLog(strNo, strPrice);
        return int1;
    }

    public int doInsertLogOne(List<String> chkList, String strPrice) {
        int int1 = menuDaoV2.doInsertLogOne(chkList, strPrice);
        return int1;
    }

    public int doUpdatePriceOne(List<String> chkList, String strPrice) {
        int int2 = menuDaoV2.doUpdatePriceOne(chkList, strPrice);
        return int2;
    }
    //@Transactional(rollbackFor = Exception.class)
    public int doUpdateInsert(List<String> chkList, String strPrice) throws RuntimeException {
        log.info("=======================================");
        int int1 = 0;
        try {

            TransactionStatus status1 = transactionManager.getTransaction(definition);
            int int2 = menuDaoV2.doUpdatePriceOne(chkList, strPrice);
            transactionManager.rollback(status1);

            TransactionStatus status2 = transactionManager.getTransaction(definition);
            int1 = menuDaoV2.doUpdatePriceOne(chkList, strPrice);
            transactionManager.rollback(status2);

        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        } finally {
            log.info("=================finally=================");
            TransactionStatus status3 = transactionManager.getTransaction(definition);
            int1 = menuDaoV2.doUpdatePriceOne(chkList, strPrice);
            bootlog.doBootLog(getClass().getName());
            transactionManager.commit(status3);

        }
        return int1;
        //checked 예외 발생 지점
        /*File file = new File("not existing file.txt");
        FileInputStream stream = new FileInputStream(file);*/

        //unchecked Exception -> ArithemticExcption이 발생
            /*int numerator = 1;
            int denominator = 0;
            int result = numerator / denominator;*/
    }
}

