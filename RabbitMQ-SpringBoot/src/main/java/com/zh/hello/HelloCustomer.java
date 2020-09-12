package com.zh.hello;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Beloved
 * @date 2020/9/12 18:10
 */
@Component
/*
 * @RabbitListener：消费者的监听
 *      queuesToDeclare：没有队列声明队列
 *          @Queue：队列，通过属性可以设置队列信息
 *              value：队列名称
 */
@RabbitListener(queuesToDeclare = @Queue(value = "hello"))
public class HelloCustomer {

    // 从队列中取出消息的回调方法
    @RabbitHandler
    public void customer(String message){
        System.out.println("message = " + message);
    }
}
