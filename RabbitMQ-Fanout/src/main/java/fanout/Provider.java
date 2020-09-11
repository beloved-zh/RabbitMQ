package fanout;

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
         * 将通道声明指定交换机、没有就会自动创建
         *   1：交换价名称
         *   2：交换机类型：fanout 广播类型
         */
        channel.exchangeDeclare("logs","fanout");

        // 发送消息
        channel.basicPublish("logs","",null,"广播消息".getBytes());

        // 关闭资源
        RabbitMQUtils.close(channel,connection);
    }
}
