package com.boot.sailing.v3.controller;

import com.boot.sailing.v3.service.MemberSvcV2;
import com.boot.sailing.v3.vo.Cust_info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/v2")
public class MemerConV2 {

    @Autowired
    MemberSvcV2 memberSvcV2;

    @RequestMapping("/member")
    public String doMember(Model model){
        //Data 만들기, List, Map
        List<Cust_info> list = memberSvcV2.doList();

        //Data 송부
        model.addAttribute("list", list);
        model.addAttribute("hello", "========= MemberCon V2 ========");
        return "/v2/member/member";
    }

    //조회하기
    @PostMapping("/member_search")
    public String doSearch( @RequestParam("start_date") String strStartDate,
                            @RequestParam("end_date") String strEndDate,
                            @RequestParam("name") String strName,
                            Model model

    ){
        List<Cust_info> list = memberSvcV2.doSearch(strStartDate, strEndDate, strName);
        model.addAttribute("list", list);

        return "/v2/member/member";
    }
}