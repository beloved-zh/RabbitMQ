package com.zh.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Beloved
 * @date 2020/9/12 18:25
 */
@Component
public class WorkCustomer {

    // 消费者1
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void customer1(String message){
        System.out.println("message1 = " + message);
    }

    // 消费者2
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void customer2(String message){
        System.out.println("message2 = " + message);
    }

}
