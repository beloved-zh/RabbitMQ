package routing;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author Beloved
 * @date 2020/9/10 15:25
 */
public class Customer2 {

    public static void main(String[] args) throws IOException {
        // 获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        String exchangeName = "logs_direct";

        // 通道绑定交换机
        channel.exchangeDeclare(exchangeName,"direct");

        // 临时队列
        String queueName = channel.queueDeclare().getQueue();

        // 基于routeKey绑定交换机和队列
        channel.queueBind(queueName,exchangeName,"info");
        channel.queueBind(queueName,exchangeName,"error");
        channel.queueBind(queueName,exchangeName,"warning");

        // 消费消息
        channel.basicConsume(queueName,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2：" + new String(body));
            }
        });

    }
}
