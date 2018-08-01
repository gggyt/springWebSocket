package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class controllers {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/myIndex")
    public String myIndex() {
        return "myindex";
    }
}
