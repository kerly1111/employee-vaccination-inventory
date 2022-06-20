package com.kruger.employee.vaccination.inventory.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "WELCOME";
    }

    @RequestMapping("/accessdenied")
    public String accessdenied() {
        return "Acceso denegado";
    }


}
