package com.itouba.teamwork;

import com.itouba.teamwork.config.AppConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
class TeamworkApplicationTests {

    @Autowired
    AppConfig config;

    @Test
    void contextLoads() {
        System.out.println(config.toString());
    }

}
