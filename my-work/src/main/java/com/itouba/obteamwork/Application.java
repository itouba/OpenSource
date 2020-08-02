package com.itouba.obteamwork;
import com.itouba.obteamwork.config.AppLocaleResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;


@SpringBootApplication
@EnableCaching
public class Application  implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private ApplicationContext appContext;


    @Bean
    public AppLocaleResolver localeResolver(){
        return new AppLocaleResolver();
    }


    @Override
    public void run(String... args){
        String[] beans = appContext.getBeanDefinitionNames();
        Arrays.sort(beans);
        for (String bean : beans)
        {
            //System.out.println(bean + " of Type :: " + appContext.getBean(bean).getClass());
        }
    }
}
