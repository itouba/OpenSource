package com.itouba.api.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class ApiController {

    @GetMapping("/get/{id}")
    @ResponseBody
    public String get(@PathVariable String id){
        System.out.println(new Date() + " input id:"+id);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Date() + " get id:"+id);
        return id;
    }
}
