package com.itouba.obteamwork.config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Component
@ConfigurationProperties(prefix = "app")
@PropertySource("classpath:application-dev.yml")
public class AppConf {
    private String url;
    private String token;
    private UserInfo user;
    private HashMap<String, Object> path;
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

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public HashMap<String, Object> getPath() {
        return path;
    }

    public void setPath(HashMap<String, Object> path) {
        this.path = path;
    }

    public List<String> getClient() {
        return client;
    }

    public void setClient(List<String> client) {
        this.client = client;
    }

    @Override
    public String toString() {
        UUID uuid = UUID.randomUUID();
        return "AppConf{" +
                "url='" + url + '\'' +
                ", token='" + token + '\'' +
                ", uuid=" + uuid +
                ", path=" + path +
                ", client=" + client +
                '}';
    }
}
