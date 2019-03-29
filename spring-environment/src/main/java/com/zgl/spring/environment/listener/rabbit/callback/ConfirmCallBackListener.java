package com.zgl.spring.environment.listener.rabbit.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;

/**
 * @author zgl
 * @date 2019/3/29 下午4:24
 */
public class ConfirmCallBackListener implements RabbitTemplate.ConfirmCallback {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		if (!ack) {
			logger.error("confirm--:correlationData:{},cause:{}", correlationData, cause);
		}
	}
}