package com.ticket.controllers;

import com.ticket.dataStorage.FormData;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class ViewController {


    @RequestMapping("/")
    public String index(){
        return "index";
    }

    AtomicInteger counter = new AtomicInteger(0);

    @GetMapping("/client-form")
    public String LoadForm(Model model) {
        model.addAttribute("form", new FormData());
        model.addAttribute("datetime", new Date());
        model.addAttribute("counter",counter.incrementAndGet());
        return "client-form";
    }

    @PostMapping("/client-form")
    public String submitForm(@ModelAttribute("form") FormData form) {
        form.setUser_type("client");
        return "confirmation";
    }
}
