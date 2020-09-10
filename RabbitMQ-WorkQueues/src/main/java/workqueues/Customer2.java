package workqueues;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author Beloved
 * @date 2020/9/10 22:21
 * 消费者2
 */
public class Customer2 {

    public static void main(String[] args) throws IOException {
        // 获取连接
        Connection connection = RabbitMQUtils.getConnection();
        final Channel channel = connection.createChannel();

        // 设置每一次只能消费一个消息
        channel.basicQos(1);

        channel.queueDeclare("work",false,false,true,null);

        /*
         * 绑定对象
         * 1：队列名称
         * 2：消息自动确认
         *      true：消费者自动向RabbitMQ确认消息
         *      false：不会自动确认消息
         */
        channel.basicConsume("work",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者-2："+new String(body));
                /*
                 * 手动确认消息
                 *  1：确认队列中那个具体消息
                 *  2：是否开启多个消息同时确认
                 */
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });

    }



}
