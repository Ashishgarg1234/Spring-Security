package com.cisco.resourceserver.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @GetMapping("/hello")
    @PreAuthorize("#oauth2.hasScope('read')")
    public String securedHello()
    {
        return "Hello From OAuth Secured Server!!";
    }

}
