package com.zh.topic;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Beloved
 * @date 2020/9/12 19:13
 */
@Component
public class TopicCustomer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(name = "topic",type = "topic"),
                    key = {"user.*"}
            )
    })
    public void customer1(String message) {
        System.out.println("message1 = " + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(name = "topic",type = "topic"),
                    key = {"user.#"}
            )
    })
    public void customer2(String message) {
        System.out.println("message2 = " + message);
    }
}
