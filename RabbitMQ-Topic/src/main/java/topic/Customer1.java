package topic;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author Beloved
 * @date 2020/9/10 15:25
 */
public class Customer1 {

    public static void main(String[] args) throws IOException {
        // 获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        String exchangeName = "topic";

        // 通道绑定交换机
        channel.exchangeDeclare(exchangeName,"topic");

        // 临时队列
        String queueName = channel.queueDeclare().getQueue();

        // 基于routeKey动态通配符形式绑定交换机和队列
        channel.queueBind(queueName,exchangeName,"user.*");

        // 消费消息
        channel.basicConsume(queueName,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1-*：" + new String(body));
            }
        });

    }
}
