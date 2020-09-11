package fanout;

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

        // 通道绑定交换机
        channel.exchangeDeclare("logs","fanout");

        // 临时队列，每个消费者都有自己的临时队列，消费结束就会删除
        String queueName = channel.queueDeclare().getQueue();

        // 绑定交换机和队列
        channel.queueBind(queueName,"logs","");

        // 消费消息
        channel.basicConsume(queueName,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2：" + new String(body));
            }
        });

    }
}
