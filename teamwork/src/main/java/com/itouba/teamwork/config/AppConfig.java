package com.itouba.teamwork.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "app")
public class AppConfig {
    private String url;
    private String token;
    private HashMap user;



    private List<String> client;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public HashMap getUser() {
        return user;
    }

    public void setUser(HashMap user) {
        this.user = user;
    }

    public List<String> getClient() {
        return client;
    }

    public void setClient(List<String> client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "AppConfig{" +
                "url='" + url + '\'' +
                ", token='" + token + '\'' +
                ", user=" + user +
                ", client=" + client +
                '}';
    }
}
