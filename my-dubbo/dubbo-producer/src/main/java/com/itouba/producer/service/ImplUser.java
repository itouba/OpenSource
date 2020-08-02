package com.itouba.producer.service;

import com.itouba.api.entity.User;

public class ImplUser {

    public User getUser(){
        return  new User(1, "唐建平");
    }
}
