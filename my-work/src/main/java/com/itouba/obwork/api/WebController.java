package com.itouba.obwork.api;

import com.itouba.obwork.config.AppConf;
import com.itouba.obwork.entitry.Team;
import com.itouba.obwork.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class WebController {

    @Autowired
    private AppConf conf;

    @Autowired
    TeamMapper teamMapper;

    @Autowired
    MessageSource messageSource;

    private static int st = 0;      //静态的
    private int index = 0;    //非静态

    @PostMapping(value = "/token/{id}")
    public Object token(@PathVariable long id){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpServletResponse response = servletRequestAttributes.getResponse();

        System.out.println(request.getHeaderNames());

        String message = messageSource.getMessage("login.name", null, LocaleContextHolder.getLocale());
        //Team team = teamMapper.get(id);
        System.out.println(conf.toString());
        System.out.println(st++ + " | " + index++);
        return message;
    }

    @GetMapping(value = "/get/{id}")
    public Object get(@PathVariable long id){
        Team team = teamMapper.get(id);
        return id+ ":" +st++ + " | " + index++;
    }
}
