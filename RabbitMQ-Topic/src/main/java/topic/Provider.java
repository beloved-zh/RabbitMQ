package topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author Beloved
 * @date 2020/9/10 22:11
 */
public class Provider {

    public static void main(String[] args) throws IOException {
        // 获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        /*
         * 将通道声明指定交换机
         *   1：交换价名称
         *   2：交换机类型：topic
         */
        String exchangeName = "topic";
        channel.exchangeDeclare(exchangeName,"topic");

        // 发送消息
        String routingKey = "user.add.aaa"; // 动态路由
        channel.basicPublish(exchangeName,routingKey,null,("这是topic模型发布的基于route key：["+routingKey+"]发送的消息").getBytes());

        // 关闭资源
        RabbitMQUtils.close(channel,connection);
    }
}
