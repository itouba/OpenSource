package com.alibaba.cloud.examples.api;


import java.util.Date;

public class ApiTest {
    public static void main(String[] args) {
        System.out.println("start time:" + new Date());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int num = 800;
        ApiRestTemplate restTemplate = new ApiRestTemplate();
        for (int i=1; i<=100; i++) {
            for (int k=1; k<=num; k++){
                int var1 = i;
                int var2 = k;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ResultVO resultVO = restTemplate.sendGetAPIRequest("http://localhost:8020/cmd/article/" + (var1 * num + var2 * 1), 1300);
                        //ResultVO resultVO = restTemplate.sendGetRequest("http://localhost:8020/direct/" + (var1 * num + var2 * 1), null);
                        System.out.println(new Date().toString() + " retry:" + ApiRestTemplate.retryCount
                                + " failed:" + ApiRestTemplate.failedCount  + ": "+resultVO.toString());
                    }
                }).start();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
