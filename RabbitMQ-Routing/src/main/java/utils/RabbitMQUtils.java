package utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author Beloved
 * @date 2020/9/10 15:48
 */
public class RabbitMQUtils {

    private static ConnectionFactory connectionFactory;

    static {
        // 重量级资源 类加载执行只执行一次
        // 创建连接MQ的连接工厂对象
        connectionFactory = new ConnectionFactory();
        // 设置连接RabbitMQ主机
        connectionFactory.setHost("192.168.245.200");
        // 设置端口
        connectionFactory.setPort(5672);
        // 设置连接那个虚拟主机
        connectionFactory.setVirtualHost("/hello");
        // 设置访问虚拟主机的用户名密码
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
    }

    // 定义提供连接对象的方法
    public static Connection getConnection(){
        try {
            return connectionFactory.newConnection();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // 关闭通道和关闭连接
    public static void close(Channel channel,Connection connection){
        try {
            if(channel != null) channel.close();
            if(connection != null) connection.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
