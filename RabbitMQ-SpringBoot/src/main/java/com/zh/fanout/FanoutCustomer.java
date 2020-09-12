package com.zh.fanout;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Beloved
 * @date 2020/9/12 18:46
 */
@Component
public class FanoutCustomer {

    @RabbitListener(bindings = { // bindings：绑定交换机和队列
            @QueueBinding(
                    value = @Queue, // 不指定队列名生产临时队列
                    exchange = @Exchange(value = "logs", type = "fanout") // 绑定交换机
            )
    })
    public void customer1(String message) {
        System.out.println("message1 = " + message);
    }

    @RabbitListener(bindings = { // bindings：绑定交换机和队列
            @QueueBinding(
                    value = @Queue, // 不指定队列名生产临时队列
                    exchange = @Exchange(value = "logs", type = "fanout") // 绑定交换机
            )
    })
    public void customer2(String message) {
        System.out.println("message2 = " + message);
    }
}
