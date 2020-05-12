package com.alibaba.cloud.examples;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author <a href="mailto:fangjian0423@gmail.com">Jim</a>
 */
@Service
public class ReceiveService {

	@StreamListener("article")
	public void article(Message message) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		JSONObject jsStr = JSONObject.parseObject(String.valueOf(message.getPayload()));
		//System.out.println(System.currentTimeMillis()+":Article Message receive: " + message.getPayload() + ", foo header: " + message.getHeaders().get("foo"));
		RedisPoolUtil.set("article."+Integer.parseInt(jsStr.getString("id")), message.getPayload().toString());
	}

	@StreamListener("order")
	public void order(Message message) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		JSONObject jsStr = JSONObject.parseObject(String.valueOf(message.getPayload()));
		//System.out.println(System.currentTimeMillis()+":Order Message receive: " + message.getPayload() + ", foo header: " + message.getHeaders().get("foo"));
		RedisPoolUtil.set("order."+Integer.parseInt(jsStr.getString("id")), message.getPayload().toString());
	}


}
