package com.alibaba.cloud.examples.api;

import com.alibaba.cloud.examples.Foo;
import com.alibaba.cloud.examples.RedisPoolUtil;
import com.alibaba.cloud.examples.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RestController {

    @Autowired
    private SenderService senderService;

    @ResponseBody
    @GetMapping("/direct/{id}")
    public ResultVO direct(@PathVariable int id){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ResultVO(200, "success");
    }

    @ResponseBody
    @GetMapping("/cmd/article/{id}")
    public ResultVO article(@PathVariable int id) throws Exception {
        String data = RedisPoolUtil.get("article." + id);
        if (data != null){
            return new ResultVO(200, data == null ? "failed" : "success", data);
        }
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            senderService.sendArticle(new Foo(id, "article"), "APP_ARTICLE_MESSAGE");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();
        return new ResultVO(200, "article cmd success");
    }

    @ResponseBody
    @GetMapping("/cmd/order/{id}")
    public ResultVO order(@PathVariable int id) throws Exception {
        String data = RedisPoolUtil.get("order." + id);
        if (data != null){
            return new ResultVO(200, data == null ? "failed" : "success", data);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    senderService.sendOrder(new Foo(id, "order"), "APP_ORDER_MESSAGE");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return new ResultVO(200, "order cmd success");
    }

    @ResponseBody
    @GetMapping("/result/article/{id}")
    public ResultVO articleResult(@PathVariable int id){
        String data = RedisPoolUtil.get("article." + id);
        return new ResultVO(200, data == null ? "failed" : "success", data);
    }

    @ResponseBody
    @GetMapping("/result/order/{id}")
    public ResultVO orderResult(@PathVariable int id){
        String data = RedisPoolUtil.get("order." + id);
        return new ResultVO(200, data == null ? "failed" : "success", data);
    }
}
