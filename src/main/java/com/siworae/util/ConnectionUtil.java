package com.siworae.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 获取连接
 *
 */
public class ConnectionUtil {

    public  static Connection getConnection() throws  Exception{

        ConnectionFactory factory=new ConnectionFactory();
        //设置host
        factory.setHost("127.0.0.1");
        //设置端口,默认为5672
        factory.setPort(5672);
        //设置队列名
        factory.setVirtualHost("/siworae");
        //设置登陆名
        factory.setUsername("siworae");
        //设置登陆密码
        factory.setPassword("123456");
        return factory.newConnection();

    }
}
