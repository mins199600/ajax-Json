package com.boot.sailing.v3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v2")
public class HomeConV2 {

    @GetMapping({"/","/home"})
    public String doHome(){

        return "/v3/home/home";
    }

    @GetMapping("/home2")
    public String doHome2Get(){
        return "/v3/home/home";
    }

    @PostMapping("/home2")
    public String doHome2Post(){
        return "/v3/home/home";
    }
}
