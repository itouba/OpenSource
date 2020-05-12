package com.alibaba.cloud.examples;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.alibaba.cloud.examples.RocketMQProduceApplication.MySource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.support.RocketMQHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

/**
 * @author <a href="mailto:fangjian0423@gmail.com">Jim</a>
 */
@Service
public class SenderService {

	@Autowired
	private MySource source;

	private ObjectMapper objectMapper = new ObjectMapper();


	public <T> void sendArticle(T msg, String tag) throws Exception {
		Message message = MessageBuilder.withPayload(msg)
				.setHeader(MessageConst.PROPERTY_TAGS, tag)
				.setHeader("foo", new Foo(1, "bar"))
				.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
				.build();
		source.appArticleMessage().send(message);
		//System.out.println("send article message at " + System.currentTimeMillis());
	}

	public <T> void sendOrder(T msg, String tag) throws Exception {
		Message message = MessageBuilder.withPayload(msg)
				.setHeader(MessageConst.PROPERTY_TAGS, tag)
				.setHeader("foo", new Foo(1, "bar"))
				.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
				.build();
		source.appOrderMessage().send(message);
		//System.out.println("send order message at " + System.currentTimeMillis());
	}


}
