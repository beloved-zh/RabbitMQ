package helloworld;

import com.rabbitmq.client.*;
import org.junit.Test;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Beloved
 * @date 2020/9/10 15:25
 */
public class Customer {

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接MQ的连接工厂对象
        /*ConnectionFactory factory = new ConnectionFactory();
        // 设置连接RabbitMQ主机
        factory.setHost("192.168.245.200");
        // 设置端口
        factory.setPort(5672);
        // 设置连接那个虚拟主机
        factory.setVirtualHost("/hello");
        // 设置访问虚拟主机的用户名密码
        factory.setUsername("admin");
        factory.setPassword("admin");

        // 获取连接对象
        Connection connection = factory.newConnection();*/

        // 通过工具类获取连接对象
        Connection connection = RabbitMQUtils.getConnection();

        // 获取连接种通道
        Channel channel = connection.createChannel();

        // 通道绑定对象
        channel.queueDeclare("hello",false,false,true,null);

        /*
         * 消费消息
         *   1.队列名称  消费那个队列的消息
         *   2.开启消息的自动确认机制
         *   3.消费消息时的回调接口
         */
        channel.basicConsume("hello",true,new DefaultConsumer(channel){
            /**
             * @param consumerTag 标签
             * @param envelope 信封
             * @param properties 属性
             * @param body  消息队列中取出的消息
             * @throws IOException
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("new String(boby) = " + new String(body));
            }
        });

        /*
         * 如果不关闭就会一直监听消息队列
         * 不建议关闭
         * 关闭会发生，子线程还没有处理完，主线程已经关闭，消费被消费但获取不到
         */
        // channel.close();
        // connection.close();
    }
}
