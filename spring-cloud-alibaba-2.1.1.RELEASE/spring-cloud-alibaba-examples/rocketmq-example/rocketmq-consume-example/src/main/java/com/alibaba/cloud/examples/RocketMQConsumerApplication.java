package com.alibaba.cloud.examples;

import com.alibaba.cloud.examples.RocketMQConsumerApplication.MySink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.binder.PollableMessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author <a href="mailto:fangjian0423@gmail.com">Jim</a>
 */
@SpringBootApplication
@EnableBinding({ MySink.class })
@EnableCaching
public class RocketMQConsumerApplication {

	public interface MySink {

		@Input("article")
		SubscribableChannel article();

		@Input("order")
		SubscribableChannel order();


	}

	public static void main(String[] args) {
		SpringApplication.run(RocketMQConsumerApplication.class, args);
	}


}
