package com.zgl.spring.environment.listener.rabbit;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * @author zgl
 * @date 2019/3/29 下午2:34
 */
public class QueueZglConsumer {

	private Logger logger = LoggerFactory.getLogger(getClass());

	public void onMessage(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
		logger.info("消费者消费:{}", message);
	}
}