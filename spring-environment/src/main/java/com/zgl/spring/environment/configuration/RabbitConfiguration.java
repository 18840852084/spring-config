package com.zgl.spring.environment.configuration;


import com.zgl.spring.environment.listener.rabbit.QueueZglConsumer;
import com.zgl.spring.environment.listener.rabbit.QueueZyyConsumer;
import com.zgl.spring.environment.listener.rabbit.callback.ConfirmCallBackListener;
import com.zgl.spring.environment.listener.rabbit.callback.ReturnCallBackListener;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import javax.annotation.Resource;

/**
 * @author zgl
 * @date 2019/4/1 上午11:34
 */
@Configuration
@PropertySource(value = {"classpath:rabbit.properties"})
public class RabbitConfiguration {

	@Value("${spring.rabbitmq.host}")
	private String host;
	@Value("${spring.rabbitmq.port}")
	private int port;
	@Value("${spring.rabbitmq.username}")
	private String username;
	@Value("${spring.rabbitmq.password}")
	private String password;
	@Value("${spring.rabbitmq.virtual-host}")
	private String virtualHost;
	@Value("${spring.rabbitmq.exchange}")
	private String exchangeName;
	@Value("${spring.rabbitmq.queueName}")
	private String queueName;
	@Value("${spring.rabbitmq.queue1Name}")
	private String queue1Name;
	@Value("${spring.rabbitmq.queueKey}")
	private String queueKey;
	@Value("${spring.rabbitmq.queue1Key}")
	private String queue1Key;
	@Value("${spring.rabbitmq.lowConcurrency}")
	private int lowConcurrency;
	@Value("${spring.rabbitmq.highConcurrency}")
	private int highConcurrency;
	@Value("${spring.rabbitmq.publisher-confirms}")
	private boolean publisherConfirms;
	@Value("${spring.rabbitmq.publisher-returns}")
	private boolean publisherReturns;

	@Resource
	private ConfirmCallBackListener confirmCallBackListener;

	@Resource
	private ReturnCallBackListener returnCallBackListener;

	@Resource
	private QueueZglConsumer zglConsumer;

	@Resource
	private QueueZyyConsumer zyyConsumer;

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost(host);
		connectionFactory.setPort(port);
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);
		connectionFactory.setVirtualHost(virtualHost);
		connectionFactory.setPublisherReturns(publisherReturns);
		connectionFactory.setPublisherConfirms(publisherConfirms);
		return connectionFactory;
	}

	@Bean
	public RabbitAdmin rabbitAdmin() {
		return new RabbitAdmin(connectionFactory());
	}


	@Bean
	public DirectExchange exchange() {
		return new DirectExchange(exchangeName);
	}

	@Bean
	public Queue queue() {
		return new Queue(queueName, true, false, false, null);
	}

	@Bean
	public Queue queue1() {
		return new Queue(queue1Name, true, false, false, null);
	}

	@Bean
	public Binding binding(){
		return BindingBuilder.bind(queue()).to(exchange()).with(queueKey);
	}

	@Bean
	public Binding binding1(){
		return BindingBuilder.bind(queue1()).to(exchange()).with(queue1Key);
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate template = new RabbitTemplate(connectionFactory());
		template.setMandatory(true);
		template.setConfirmCallback(confirmCallBackListener);
		template.setReturnCallback(returnCallBackListener);
		return template;
	}

	@Bean
	public SimpleMessageListenerContainer messageListenerContainer(){
		SimpleMessageListenerContainer messageListenerContainer=new SimpleMessageListenerContainer();
		messageListenerContainer.setQueueNames(queueName);
		messageListenerContainer.setConnectionFactory(connectionFactory());
		messageListenerContainer.setMessageListener(zglConsumer);
		messageListenerContainer.setConcurrentConsumers(highConcurrency);
		return messageListenerContainer;
	}

	@Bean
	public SimpleMessageListenerContainer messageListenerContainer1(){
		SimpleMessageListenerContainer messageListenerContainer=new SimpleMessageListenerContainer();
		messageListenerContainer.setQueueNames(queue1Name);
		messageListenerContainer.setConnectionFactory(connectionFactory());
		messageListenerContainer.setMessageListener(zyyConsumer);
		messageListenerContainer.setConcurrentConsumers(lowConcurrency);
		return messageListenerContainer;
	}
}