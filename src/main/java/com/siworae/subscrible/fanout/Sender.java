package com.siworae.subscrible.fanout;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.siworae.util.ConnectionUtil;

public class Sender {
    public static final String EXCHANGE_NAME="ex_01";
    public static void main(String[] args)  throws  Exception{
        Connection connection= ConnectionUtil.getConnection();
        Channel channel=connection.createChannel();
        /**
         * fanout:绑定到交换机上的所有队列  均能够收到交换机路由的消息
         */
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        String msg="18020911874&123@163.com";
        channel.basicPublish(EXCHANGE_NAME,"",null,msg.getBytes("utf-8"));
        channel.close();
        connection.close();
    }
}
