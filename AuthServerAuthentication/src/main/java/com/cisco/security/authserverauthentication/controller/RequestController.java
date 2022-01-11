package com.cisco.security.authserverauthentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class RequestController {

    @Autowired
    private WebClient webClient;
    @GetMapping("/user/greeting/{name}")
    public String greeting(@PathVariable("name") String name)
    {
        return "Hello "+name;
    }

    @GetMapping("/admin/greeting/{name}")
    public String greetingCisco(@PathVariable(value = "name",required = false) String name)
    {
        //return "Hello "+name+"! Welcome to Cisco!!";

        return webClient.get().uri("http://localhost:8090/hello").retrieve().bodyToMono(String.class).block();


    }
}
