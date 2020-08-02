package com.itouba.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

public class test {


    public static void main(String[] args) {
        RestTemplate template = new RestTemplate();
        long threadCount = 1000;
        String url = "http://192.168.3.6:8080/get/";

        for (int i=1; i<=threadCount; i++){
            int index = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String object = template.getForObject(url + String.valueOf(index), String.class);
                    System.out.println(new Date() + " get "+ String.valueOf(index)+" ok");
                }
            }).start();
        }
    }
}
