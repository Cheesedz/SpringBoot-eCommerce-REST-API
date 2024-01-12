package com.Cheesedz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {
    @GetMapping("/admin")
    public String admin() {
        return "index";
    }
}
