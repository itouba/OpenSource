package com.itouba.obteamwork;

import com.itouba.obteamwork.mapper.TeamMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    TeamMapper teamMapper;

   @Test
    public void test(){
       System.out.println(teamMapper.get(100));
    }
}