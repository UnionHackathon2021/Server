package com.example.demo.controller;

import com.example.demo.exception_advice.exception.MemberNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class TestController {

    @GetMapping("/test")
    public String test() {
//        throw new MemberNotFoundException();
        return "Test";
    }
}
