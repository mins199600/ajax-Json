package com.boot.sailing.v3.controller;

import com.boot.sailing.v3.service.MenuSvcV2;
import com.boot.sailing.v3.vo.Coffee_menu;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/v2")
@Log4j2
public class MenuConV2 {

    @Autowired
    MenuSvcV2 menuSvcV2;

    @RequestMapping("/menu")
    public String doMenu(Model model) {

        //Data 만들기, List, Map
        List<Coffee_menu> list = menuSvcV2.doList();

        //Data 송부
        model.addAttribute("list", list);
        model.addAttribute("hello", "========= MenuCon ========");

        return "/v2/menu/menu";
    }

    @GetMapping("/menu_ins")
    public String doInsert() {
        return "/v2/menu/menu_ins";
    }

    @PostMapping("/menu_ins")
    public String doInsertPost(@ModelAttribute Coffee_menu coffeeMenu) {
        int i = menuSvcV2.doInsert(coffeeMenu);

        return "redirect:/v2/menu";
    }

    @GetMapping("/menu_del")
    public String doDelete(@RequestParam("no") String strNo) {
        int i = menuSvcV2.doDelete(strNo);
        return "redirect:/v2/menu";
    }

    @GetMapping("/menu_up")
    public String doUpdate(Model model, @RequestParam("no") String strNo) {
        Map<String, Object> map = menuSvcV2.doListOne(strNo);
        model.addAttribute("map", map);
        return "/v2/menu/menu_up";
    }

    @PostMapping("/menu_up")
    public String doUpdatePost(@ModelAttribute Coffee_menu coffeeMenu) {
        int i = menuSvcV2.doUpdate(coffeeMenu);
        return "redirect:/v2/menu";
    }

    //조회하기
    @PostMapping("/menu_search")
    public String doSearch(@RequestParam("start_date") String strStartDate,
                           @RequestParam("end_date") String strEndDate,
                           @RequestParam(value = "coffee", defaultValue = "ALL") String strCoffee,
                           @RequestParam("kind") String strKind,
                           Model model
    ) {
        List<Coffee_menu> list = menuSvcV2.doSearch(strStartDate, strEndDate, strCoffee, strKind);
        model.addAttribute("list", list);
        return "/v2/menu/menu";
    }
    //가격수정 - 다중 체크
    @PostMapping("/menu_updatePrice")
    public String doUpdatePrice(@RequestParam("chkCoffeeNo") List<String> chkList,
                                @RequestParam("hidden_price") String strPrice,
                                Model model
    ) {
        String strReturn = "redirect:/v2/menu";
        try {
            if (chkList != null) {
           /* for(String strNo : chkList){
                int int1 = menuSvc.doInsertLog(strNo, strPrice);
                int int2 = menuSvc.doUpdatePrice(strNo, strPrice);
            }*/
                int int1 = menuSvcV2.doUpdateInsert(chkList, strPrice);
                // int int1 = menuSvcV2.doInsertLogOne(chkList, strPrice);
                // int int2 = menuSvcV2.doUpdatePriceOne(chkList, strPrice);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("error", e.getMessage());
            strReturn = "/v2/comm/ErrorPage";
        }

        return strReturn;
    }
}
