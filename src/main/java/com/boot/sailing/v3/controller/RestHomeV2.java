package com.boot.sailing.v3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestHomeV2 {

    @GetMapping("v2/rest")
    public String doRest(){

        String strHtml = "Hello Rest Controller";
        return strHtml;
    }
}
