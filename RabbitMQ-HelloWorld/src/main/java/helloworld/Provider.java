package helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.junit.Test;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Beloved
 * @date 2020/9/10 15:01
 */
public class Provider {


    /**
     * 生产消息
     */
    @Test
    public void testSendMessage() throws IOException, TimeoutException {

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

        /*
         * 通道绑定对应的消息队列
         *   1：队列名称 如果队列不存在自动创建
         *   2：用于定义队列是否需要持久化
         *      true：队列信息会持久化到磁盘，关闭重启之后队列也会存在
         *      false：在下一次重启，队列会被删除
         *   3：是否独占队列
         *      true：这个队列只能当前连接使用，其他连接不可以使用
         *      false：可以被其他连接使用
         *   4：是否在消费完成后自动删除队列
         *      true：自动删除
         *      false：不删除
         *   5：额外参数
         */
        channel.queueDeclare("hello",false,false,true,null);

        /*
         * 发布消息
         *  1：交换机名称 这里没有
         *  2：队列名称
         *  3：传递消息额外设置
         *  4：消息的具体内容
         *
         */
        channel.basicPublish("","hello", MessageProperties.TEXT_PLAIN,"Hello RabitMQ！".getBytes());

        RabbitMQUtils.close(channel,connection);
        /*channel.close();
        connection.close();*/
    }

}
