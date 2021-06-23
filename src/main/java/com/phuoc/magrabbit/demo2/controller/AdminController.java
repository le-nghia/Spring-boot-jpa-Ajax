package com.phuoc.magrabbit.demo2.controller;

import com.phuoc.magrabbit.demo2.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AccountRepository accountRepository;


    @GetMapping
    public String admin(){
        return "admins/index";
    }

    @GetMapping("/calendar")
    public String calderdar(){
        return "admins/listProduct";
    }
}
