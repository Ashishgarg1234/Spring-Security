package com.cisco.demospringsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {

    @GetMapping("/user/greeting/{name}")
    public String greeting(@PathVariable("name") String name)
    {
        return "Hello "+name;
    }

    @GetMapping("/admin/greeting/{name}")
    public String greetingCisco(@PathVariable("name") String name)
    {
        return "Hello "+name+"! Welcome to Cisco!!";
    }
}
