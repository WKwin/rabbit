package com.kwin.rabbit.consumer;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.kwin.rabbit.entity.Order;
import com.rabbitmq.client.Channel;

@Component
public class OrderReceiver {

/*	@RabbitListener(//可以利用这个注解创建队列、exchange、添加绑定路由key，创建完后发现有5个change，这是因为我们配置文件设置最大支持五个并发
			bindings = @QueueBinding(
					value = @Queue(value = "order-queue", durable = "true"),
					exchange = @Exchange(name = "order-exchange", durable = "true", type = "topic"),
					key = "order.*" //路由key
			)
	)
	@RabbitHandler//标识当有消息过来，调用这个方法
	public void onOrderMessage(@Payload Order order, @Headers Map<String, Object> headers , Channel channel) throws Exception {//我们设置了手工签收，手工签收 必须依赖于channel(消息通道)
		//消费者操作
		System.out.println("-------------收到消息，开始消费---------------");
		System.out.println("订单ID:" + order.getId());
		
		//因为我们设置的是手工签收，必须调用channel这个方法，手工确认签收
		Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
		channel.basicAck(deliveryTag, false);
	}*/
	
	
	
	/**
	 * 
	 * @param order 
	 * @param headers 一条消息分为消息体和消息头
	 * @param channel 我们设置了手工签收，手工签收 必须依赖于channel(消息通道)
	 */
	@RabbitListener(//可以利用这个注解创建队列、exchange、添加绑定路由key，创建完后发现有5个change，这是因为我们配置文件设置最大支持五个并发
			bindings = @QueueBinding(
					value = @Queue(value = "order-queue", durable = "true"),
					exchange = @Exchange(name = "order-exchange", durable = "true", type = "fanout"),//fanout分发到当前路由下符合的所有队列
					key = "order.*" //路由key
			)
	)
	@RabbitHandler//标识当有消息过来，调用这个方法
	public void onOrderMessage(@Payload Order order, @Headers Map<String, Object> headers , Channel channel) throws Exception {//我们设置了手工签收，手工签收 必须依赖于channel(消息通道)
		//消费者操作
		System.out.println("-------------收到消息，开始消费---------------");
		System.out.println("订单ID:" + order.getId());
		
		//因为我们设置的是手工签收，必须调用channel这个方法，手工确认签收
		Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
		channel.basicAck(deliveryTag, false);
	}
	
	/**
	 * 
	 * @param order
	 * @param headers 一条消息分为消息体和消息头
	 * @param channel 我们设置了手工签收，手工签收 必须依赖于channel(消息通道)
	 */
	@RabbitListener(//可以利用这个注解创建队列、exchange、添加绑定路由key，创建完后发现有5个change，这是因为我们配置文件设置最大支持五个并发
			bindings = @QueueBinding(
					value = @Queue(value = "order-queue2", durable = "true"),
					exchange = @Exchange(name = "order-exchange", durable = "true", type = "fanout"),
					key = "order.*" //路由key
			)
	)
	@RabbitHandler//标识当有消息过来，调用这个方法
	public void onOrderMessage2(@Payload Order order, @Headers Map<String, Object> headers , Channel channel) throws Exception {//我们设置了手工签收，手工签收 必须依赖于channel(消息通道)
		//消费者操作
		System.out.println("-------------收到消息，开始消费---------------");
		System.out.println("订单ID:" + order.getId());
		
		//因为我们设置的是手工签收，必须调用channel这个方法，手工确认签收
		Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
		channel.basicAck(deliveryTag, false);
	}
}
