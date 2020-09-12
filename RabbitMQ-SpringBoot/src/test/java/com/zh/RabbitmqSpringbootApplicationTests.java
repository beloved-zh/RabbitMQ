package com.zh;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitmqSpringbootApplicationTests {

    // 注入RabbitTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate;

    // topic 动态路由
    @Test
    void topic() {
        rabbitTemplate.convertAndSend("topic","user.save.aaa","user.save.aaa路由消息");
    }


    // route 路由模式
    @Test
    void route() {
        rabbitTemplate.convertAndSend("route","error","发送error的key的路由信息");
    }

    /*
     * fanout 广播
     *  1：交换机名称
     *  2：队列名
     *  3：消息
     */
    @Test
    void fanout() {
        rabbitTemplate.convertAndSend("logs","","Fanout广播消息");
    }

    // work
    @Test
    void work() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work","work模型"+i);
        }
    }

    // hello world
    @Test
    void hello() {
        /*
         * 1：队列名称
         * 2：消息
         */
        rabbitTemplate.convertAndSend("hello","hello world!");
    }

}
