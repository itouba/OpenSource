package com.alibaba.cloud.examples;

import com.alibaba.cloud.examples.RocketMQProduceApplication.MySource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Date;

/**
 * @author <a href="mailto:fangjian0423@gmail.com">Jim</a>
 */
@SpringBootApplication
@EnableBinding({ MySource.class })
public class RocketMQProduceApplication {

	public interface MySource {
		@Output("APP_ARTICLE_MESSAGE")
		MessageChannel appArticleMessage();

		@Output("APP_ORDER_MESSAGE")
		MessageChannel appOrderMessage();
	}

	public static void main(String[] args) {
		SpringApplication.run(RocketMQProduceApplication.class, args);
	}

	@Bean
	public CustomRunner customArticleRunner() {
		return new CustomRunner("APP_ARTICLE_MESSAGE");
	}

	@Bean
	public CustomRunner customOrderRunner() {
		return new CustomRunner("APP_ORDER_MESSAGE");
	}


	public static class CustomRunner implements CommandLineRunner {

		private final String bindingName;

		public CustomRunner(String bindingName) {
			this.bindingName = bindingName;
		}

		@Autowired
		private SenderService senderService;

		@Autowired
		private MySource mySource;

		@Override
		public void run(String... args) throws Exception {
/*			if (this.bindingName.equals("APP_ARTICLE_MESSAGE")) {
				int count = 5;
				for (int index = 1; index <= count; index++) {
					senderService.sendObject(new Foo(index, "foo"), "APP_ARTICLE_MESSAGE");
				}
			}
			else if (this.bindingName.equals("APP_ORDER_MESSAGE")) {
				int count = 3;
				for (int index = 1; index <= count; index++) {
					String msgContent = "msg-Tag: {id :" + index + "}";
					senderService.sendWithTags(msgContent, "APP_ORDER_MESSAGE");
				}
			}*/

		}
	}


}
