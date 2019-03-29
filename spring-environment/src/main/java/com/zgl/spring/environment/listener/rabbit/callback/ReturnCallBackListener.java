package com.zgl.spring.environment.listener.rabbit.callback;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author zgl
 * @date 2019/3/29 下午4:24
 */
public class ReturnCallBackListener implements RabbitTemplate.ReturnCallback {

	@Override
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {

	}
}